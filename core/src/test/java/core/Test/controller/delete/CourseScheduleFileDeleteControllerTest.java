package core.Test.controller.delete;
 
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat; 
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
import core.TestContext.utils.FileParameter;
import core.TestContext.utils.ScheduleFileUploadParam;

import java.io.IOException;  
import org.springframework.context.annotation.EnableAspectJAutoProxy; 
import core.backend.REST.fileasset.download.controller.LectureScheduleFileDownloadController;
import core.provider.FileNameProvider;
import core.utils.names.FileNameResolver;  
import resources.database.repository.FilesRepository;
import resources.utils.pathmanager.PathManager; 

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( LectureScheduleFileDownloadController .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  CourseScheduleFileDeleteControllerTest {
  
	 private static ScheduleFileUploadParam testRequestParameter; 
	  
	 @Autowired
	 private MockMvc mockMvc;
 	  
	 
	 @Autowired 
	 private FilesRepository filesRepo; 
	 
	 @Autowired
	 @Qualifier("Path to Test Resources")
	 private PathManager pathManagerToLecutreAssets; 

	 private static FileNameResolver dowloadedFileNameResolver; 
 
	 
	  
     @Rule
     public TestName testName = new TestName();

     @Before
     public void setUpParameter() throws IOException {
    
		 testRequestParameter = new ScheduleFileUploadParam();	  
 		
		 
		 if(testName.getMethodName().equals("TESTA_checkIfWeCanDeleteAFileWithInCorrectCourseNameInRequestParameter")){
	   		  
			 testRequestParameter.setCourseName( "AngewandteInformatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
    	 } 
		 else if(testName.getMethodName().equals("TESTB_checkIfWeCanDeleteAFileWithInCorrectDegreeInRequestParameter")){
	   		  
				 testRequestParameter.setCourseName( "Angewandte Informatik" );
		    	 testRequestParameter.setCourseDegree( "Bachelor of Science" );
		    	 testRequestParameter.setCourseTerm( "Sommersemester" );
				 
			   
	     }
		 else if(testName.getMethodName().equals("TESTC_checkIfWeCanDeleteAFileWithInCorrectTermInRequestParameter")){
	   		  
				 testRequestParameter.setCourseName( "Angewandte Informatik" );
		    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
		    	 testRequestParameter.setCourseTerm( "Trisemester" );
				 
				 
	     } 
		 
    	 else if(testName.getMethodName().equals("TESTE_checkIfWeCanDeleteLectureScheduleAISEBa")){
    		   		  
    	  	 testRequestParameter.setCourseName( "Angewandte Informatik" );
    		 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
        	 testRequestParameter.setCourseTerm( "Sommersemester" );
    				 
    		  
    	 } 		
    	 else if(testName.getMethodName().equals("TESTF_checkIfWeCanDeleteLectureScheduleWiInfBA")){
	   		  
    	  	 testRequestParameter.setCourseName( "Wirtschaftsinformatik" );
    		 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
        	 testRequestParameter.setCourseTerm( "Sommersemester" );
    				 
    		  
    	 } 	
     }
     

     @Test
     @WithMockUser(username = "RUDI", password = "root" )
	 public void TESTA_checkIfWeCanDeleteAFileWithInCorrectCourseNameInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Delete/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			  
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
     
     

     @Test
     @WithMockUser(username = "RUDI", password = "root" )
	 public void TESTB_checkIfWeCanDeleteAFileWithInCorrectDegreeInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Delete/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
     

     @Test
     @WithMockUser(username = "RUDI", password = "root" )
	 public void TESTC_checkIfWeCanDeleteAFileWithInCorrectTermInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();
			 
			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Delete/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			  
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
	 
      
 
     @Test
     @WithMockUser(username = "RUDI", password = "root" )
	 public void TESTE_checkIfWeCanDeleteLectureScheduleAISEBa() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
        
        FileParameter deleteTestParameter = new FileParameter(testRequestParameter.createCourseScheduleParam().getCourse());
        
        FileNameResolver resolvedFileName =  FileNameProvider.provideFileNameResolverForCourseScheduleFile(deleteTestParameter);
         
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok  = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Delete/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		  
			 
			  mockMvc.perform(builder)
		      .andExpect( ok) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
			  final String deletedFileNameToCheck = resolvedFileName.getResolvedFileName().toString();

			  resources.database.entities.File.Files deletedFile = filesRepo.read(deletedFileNameToCheck);
		 
		      assertThat("The file was not recognized as deleted", deletedFile.isFiledeleted(), is(true));
	 
			  
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
 
     @Test
     @WithMockUser(username = "RUDI", password = "root" )
	 public void TESTF_checkIfWeCanDeleteLectureScheduleWiInfBA() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
        
        FileParameter deleteTestParameter = new FileParameter(testRequestParameter.createCourseScheduleParam().getCourse());
        
        FileNameResolver resolvedFileName =  FileNameProvider.provideFileNameResolverForCourseScheduleFile(deleteTestParameter);
         
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok  = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Delete/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		  
			 
			  mockMvc.perform(builder)
		      .andExpect( ok) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
			  final String deletedFileNameToCheck = resolvedFileName.getResolvedFileName().toString();

			  resources.database.entities.File.Files deletedFile = filesRepo.read(deletedFileNameToCheck);
		 
		      assertThat("The file was not recognized as deleted", deletedFile.isFiledeleted(), is(true));
	 
			  
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
}
