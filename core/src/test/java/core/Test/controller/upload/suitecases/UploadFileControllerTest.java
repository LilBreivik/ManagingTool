package core.Test.controller.upload.suitecases;
  
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Paths;  
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; 
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import core.TestContext.ControllerTestApplicationContext;
import core.TestContext.utils.TestUserConfiguration;
import core.TestContext.utils.cases.FileAssetTestCase; 
import core.TestContext.utils.parameter.schedule.ScheduleParamWithFileAsset; 
import core.backend.REST.general.controller.REST.NonResponseController;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager;
import resources.database.entities.factory.account.CoordinatorAccount;
import resources.utils.pathmanager.PathManager;  

 
@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@WebMvcTest(NonResponseController.class) 
@TestPropertySource({"classpath:Properties/application.default.development.properties", 
	"classpath:Properties/test.user.properties"})
public class UploadFileControllerTest 
										extends FileAssetTestCase<ScheduleParamWithFileAsset>{
	 
	 private static Users m_testUser;
	
	 private static Accounts m_testAccount;
	 
	 @Autowired 
	 private  TestUserConfiguration m_TestUserConfiguration;
	   
     @Autowired 
 	 private UserAccountsManager m_UserAccountsManager; 
 	 
 	 
 	 @Autowired
 	 @Qualifier("Path to Test Resources")
 	 private PathManager pathManagerToTestAssets; 
     
     
 	 @Rule
     public TestName testName = new TestName();
 	 
     @Override 
     @Before 
     public void setUpParameter() throws IOException {
    
    	  
		 testRequestParameter = new  ScheduleParamWithFileAsset ();	  
 		 
		 
		 
		 if(testName.getMethodName().equals("INITIALIZATION_TEST")){
			 
			 m_testUser = m_UserAccountsManager.createUser( m_TestUserConfiguration.TEST_USER_EMAIL, m_TestUserConfiguration.TEST_USER_NAME);

  		     m_UserAccountsManager.addUser(m_testUser);
  			    	
  		     m_testAccount = m_UserAccountsManager.createAccount(m_testUser, new CoordinatorAccount(m_testUser, m_TestUserConfiguration.TEST_USER_PASSWORD));
  			 
		 }
	 
		 
		 else if(testName.getMethodName().equals("TESTD_handleInCorrectFileRequest")){
   		   
    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("PDFFileAsset") ).toFile());
   		  
    	 }
		 else if(testName.getMethodName().equals("TESTE_handleCorrectFileRequestForCertainCourse")){
    		   
    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(p_fileAssetName) ).toFile());
   		  
    	 }
	 
     }
	 
     
     @Test 
     public void INITIALIZATION_TEST() {
    	 
    	 super.INITIALIZE_TEST_PARAMETER();
     }
      
     @Override 
     @Test 
     @WithMockUser( username = "RUDI", password = "root")
	 public void TESTA_checkInCorrectRequests() throws Exception {
	      
    	 
		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(p_fileAssetName) ).toFile());
		  
    	 super.TESTA_handleInCorrectRequestWithWrongCourseName();
    	 super.TESTB_handleInCorrectRequestWithWrongCourseDegree();
    	 super.TESTC_handleInCorrectRequestWithWrongCourseTerm();
    	 
	 }
       
     @Test 
	 public void TESTD_handleInCorrectFileRequest() throws Exception {
	       
		 ObjectMapper mapper = new ObjectMapper();
	 	 
		 for(String name : p_courseNamesAsList) {
				
			 for(String degree  : p_courseDegreesAsList) {
			
				 for(String term : p_courseTermsAsList) {
					 
					 testRequestParameter.setCourseName( name );
			    	 testRequestParameter.setCourseDegree( degree );
			    	 testRequestParameter.setCourseTerm( term );
					 

			    	 try {
			 			
			    		 
			    		  UsernamePasswordAuthenticationToken auth_Token =  m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
			    		 	
			    		  SecurityContextHolder.getContext().setAuthentication(auth_Token );
			    			
			    		 
			    		 
			    		 final int ammountOfFilesBeforeUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
				    	 
						 String jsonInString = mapper.writeValueAsString(testRequestParameter.createScheduleParam());
					 
						 System.out.println(jsonInString);
						 
						 ResultMatcher badRequest = MockMvcResultMatchers.status()
				                   .isBadRequest();

						 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( p_controllerURL)
				                           .contentType(MediaType.APPLICATION_JSON_VALUE)
				                           .content(jsonInString);
				                           
						 
						 
						  mockMvc.perform(builder)
				         .andExpect(badRequest)
				         .andDo(MockMvcResultHandlers.print());
						  
						  // if the file was corrupt, it hall be deleted  
						  
						  final int ammountOfFilesAfterUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
						 	 
						  assertThat("The Wrong File was not deleted ", not(ammountOfFilesBeforeUpload == ammountOfFilesAfterUpload));
							 
					 } catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					 }
			    	 
				 }
			 }
		 }
		  
		 
	 }
     
     
     @Test 
     public void TESTE_handleCorrectFileRequestForCertainCourse() throws Exception {
	      
		 ObjectMapper mapper = new ObjectMapper();
	 	 
		 final int ammountOfFilesBeforeTest = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
		 
		 for(String name : p_courseNamesAsList) {
				
			 for(String degree  : p_courseDegreesAsList) {
			
				 for(String term : p_courseTermsAsList) {
					 
					 testRequestParameter.setCourseName( name );
			    	 testRequestParameter.setCourseDegree( degree );
			    	 testRequestParameter.setCourseTerm( term );
					 

			    	 try {
			    		 
			    		 UsernamePasswordAuthenticationToken auth_Token =  m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
			    		 	
			    		 SecurityContextHolder.getContext().setAuthentication(auth_Token );
			    			
			 			
			    		 final int ammountOfFilesBeforeUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
				    	 
						 String jsonInString = mapper.writeValueAsString(testRequestParameter.createScheduleParam());
					 
						 System.out.println(jsonInString);
						 
						 System.out.println();
						 ResultMatcher okRequest = MockMvcResultMatchers.status()
				                   .isOk();

						 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( p_controllerURL)
				                           .contentType(MediaType.APPLICATION_JSON_VALUE)
				                           .content(jsonInString);
				                           
						 
						 
						  mockMvc.perform(builder)
				         .andExpect(okRequest)
				         .andDo(MockMvcResultHandlers.print());
						  
						  // if the file was corrupt, it hall be deleted  
						  
						  final int ammountOfFilesAfterUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
						 	 
						  assertThat("The Wrong File was not deleted ", not((ammountOfFilesBeforeUpload + 1) == (ammountOfFilesAfterUpload )));
							 
					 } catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					 }
			    	 
				 }
			 }
		 }
		  
		 
		 final int ammountOfFilesAfterTest = pathManagerToWorkingDirectory.getAllFilesOnPath().size() + 8;
		 
		 assertThat("The Wrong File was not deleted ", not(ammountOfFilesAfterTest == (ammountOfFilesBeforeTest + 8)));
			
		 
	 }
     
	 
}
 