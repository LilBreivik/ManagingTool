package core.Test.controller.notice.suitecases;
  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.File;
import java.io.IOException; 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; 
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import core.TestContext.ControllerTestApplicationContext; 
import core.TestContext.utils.NoticeTestParameter;
import core.TestContext.utils.TestUserConfiguration;
import core.TestContext.utils.cases.FileAssetTestCase;
import core.TestContext.utils.parameter.schedule.ScheduleParamWithoutFileAsset;
import core.backend.REST.general.controller.REST.NonResponseController;
import core.backend.REST.nonfileasset.notice.controller.add.AddNoticeController;
import core.backend.REST.nonfileasset.notice.controller.delete.DeleteNoticeController;
import core.backend.REST.nonfileasset.notice.controller.read.ReadGeneralNoticeController;
import core.backend.REST.nonfileasset.notice.controller.read.ReadSpecificNoticeController; 
import notice.NoticeStatusPOJO;
import notice.PersistenceNoticesPOJO;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager; 
import resources.database.entities.factory.account.CoordinatorAccount; 
import resources.utils.pathmanager.PathManager;


@ContextConfiguration( classes={ ControllerTestApplicationContext.class }) 
@WebMvcTest(NonResponseController.class) 
@EnableAspectJAutoProxy(proxyTargetClass=true)  
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource({"classpath:Properties/application.default.development.properties", 
"classpath:Properties/test.user.properties"})
public class NoticeControllerTest 
											extends FileAssetTestCase<ScheduleParamWithoutFileAsset>{
  
	private static NoticeTestParameter testNotice; 
	 
	private static Users m_testUser;
	
	private static Accounts m_testAccount;
	 
	private static File  m_file = Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("testNotice") ).toFile();
	
	private static NoticesPOJO m_noticePojo;
	
	@Autowired 
	private  TestUserConfiguration m_TestUserConfiguration;
	
	@Autowired
	private MockMvc mockMvc;
   
	@Autowired 
	private UserAccountsManager m_UserAccountsManager; 
	 
	 
	@Autowired
	@Qualifier("Path to Test Resources")
	private PathManager pathManagerToTestAssets; 
	
	 
    @Rule
    public TestName testName = new TestName();
 
    
  	@Before
  	@Override
  	public  void setUpParameter() throws IOException {
  		
         ObjectMapper mapper = new ObjectMapper();
     	 
         NoticesPOJO pojo = new NoticesPOJO();
     	   
       	 m_noticePojo = mapper.readValue(m_file , NoticesPOJO .class);
  	 		
  		 testNotice = new NoticeTestParameter( m_noticePojo);
       	
  		 if(testName.getMethodName().equals("TESTA_checkIfWeCanAddANewNotice")){
  	   	  
  			 	 
  			 m_testUser = m_UserAccountsManager.createUser( m_TestUserConfiguration.TEST_USER_EMAIL, m_TestUserConfiguration.TEST_USER_NAME);

  		     m_UserAccountsManager.addUser(m_testUser);
  			    	
  		     m_testAccount = m_UserAccountsManager.createAccount(m_testUser, new CoordinatorAccount(m_testUser, m_TestUserConfiguration.TEST_USER_PASSWORD));
  			    	  
     	  }  
  	}
     
	@Test 
	public void TESTA_checkIfWeCanAddANewNotice() throws Exception {
		

        ObjectMapper mapper = new ObjectMapper();
       
        UsernamePasswordAuthenticationToken auth_Token = m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
        	 
        SecurityContextHolder.getContext().setAuthentication(auth_Token );
		
        try {
			
			 String jsonInString = mapper.writeValueAsString(testNotice);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(AddNoticeController.AddNoticeURL)
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                       	  
		 	 NoticeStatusPOJO noticeStatusPOJO = new NoticeStatusPOJO();
			 
			 noticeStatusPOJO.setNoticeStatus(testNotice.getNotice().getNoticeHeadline());
			 
			 PersistenceNoticesPOJO expectedNoticeStatusPOJO = new PersistenceNoticesPOJO(Arrays.asList( noticeStatusPOJO));
			  
			  mockMvc.perform(builder)
		      .andExpect(ok) 
		      .andExpect(content().json(mapper.writeValueAsString(expectedNoticeStatusPOJO)) )
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		  
	}
	
	
	
	
	@Test  
	public void TESTB_checkIfWeCanReadNotice() throws Exception {
			
       ObjectMapper mapper = new ObjectMapper();
       
       UsernamePasswordAuthenticationToken auth_Token =  m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
	 	
	   SecurityContextHolder.getContext().setAuthentication(auth_Token );
		  
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testNotice);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(ReadSpecificNoticeController.ReadSpecificNoticeURL)
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
			                   .content(jsonInString);
            	  
			 
			 String  expectedNoticeContent = new String(Files.readAllBytes(pathManagerToTestAssets.getPathOfFile( "testNotice")), "UTF-8");
			  
			  mockMvc.perform(builder)
		      .andExpect(ok) 
		      .andExpect(content().json(expectedNoticeContent))
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		  
	 }
	 
	
	 @Test  
	 public void TESTE_checkIfWeCanDeleteNotice() throws Exception {
		
		 
	   	ObjectMapper mapper = new ObjectMapper();
		
		UsernamePasswordAuthenticationToken auth_Token =  m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
		 	
		SecurityContextHolder.getContext().setAuthentication(auth_Token );
		  
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testNotice);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( DeleteNoticeController.DeleteNoticeURL)
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	          
          	  
			 NoticeStatusPOJO noticeStatusPOJO = new NoticeStatusPOJO();
		 
			 noticeStatusPOJO.setNoticeStatus("");
			   
		     final List<NoticeStatusPOJO> noticesStatus  =  new ArrayList<>();
				
			 PersistenceNoticesPOJO persistentNotices = new PersistenceNoticesPOJO(noticesStatus);
		 
			   System.out.println("------ "  +  mapper.writeValueAsString(persistentNotices));
			  mockMvc.perform(builder)
		      .andExpect(ok) 
		      .andExpect(content().json(mapper.writeValueAsString(persistentNotices)))
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	
		
		 auth_Token =  m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
		 
		 SecurityContextHolder.getContext().setAuthentication(auth_Token );
		    
	     m_UserAccountsManager.deleteUser(m_testUser);
	 }
	 
	 @Test  
	 public void TESTC_checkIfWeCanReadNotices() throws Exception {
		
		 
	   	ObjectMapper mapper = new ObjectMapper();
		
		UsernamePasswordAuthenticationToken auth_Token =  m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
		 	
		SecurityContextHolder.getContext().setAuthentication(auth_Token );
		  
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testNotice);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();
 
			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( ReadGeneralNoticeController.ReadGeneralNoticeURL)
	                           .contentType(MediaType.APPLICATION_JSON_VALUE);
	                        //   .content(jsonInString);
	          
          	  
			 NoticeStatusPOJO noticeStatusPOJO = new NoticeStatusPOJO();
		 
			 noticeStatusPOJO.setNoticeStatus("");
			   
		     final List<NoticeStatusPOJO> noticesStatus  =  new ArrayList<>();
				
		     
			 String  expectedNoticeContent = new String(Files.readAllBytes(pathManagerToTestAssets.getPathOfFile( "testNoticeStatus")), "UTF-8");
			 
		 
			   System.out.println("------ "  +  expectedNoticeContent);
			  mockMvc.perform(builder)
		      .andExpect(ok) 
		      .andExpect(content().json( expectedNoticeContent))
		      .andDo(MockMvcResultHandlers.print());
			  
			  System.out.println();
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	
		 
	 }
   
	 
}
