package core.Test.controller.upload;
 
  

import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
import core.backend.REST.fileasset.upload.controller.CourseScheduleUploadFileController;
import core.provider.FileNameProvider;
import core.utils.names.FileNameResolver;
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler;
import resources.database.repository.FilesRepository; 

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest(CourseScheduleUploadFileController  .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  CourseScheduleFileUploadControllerTest {
  
	 private static ScheduleFileUploadParam testRequestParameter; 
	   
	 @Autowired
	 private MockMvc mockMvc;
 
	 @Autowired 
	 private FilesRepository filesRepo; 
	 
	 @Autowired
     public PersistenceCourseScheduleJSONFileHandler handler; 
	 

  
     @Rule
     public TestName testName = new TestName();

     @Before
     public void setUpParameter() throws IOException {
    
		 testRequestParameter = new ScheduleFileUploadParam();	  
 		
		 if(testName.getMethodName().equals("TESTA_handleInCorrectRequestWithWrongCourseName")){
   		  
			 testRequestParameter.setCourseName( "Angewandnformatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
   		  
    	 }
		 else if(testName.getMethodName().equals("TESTB_handleInCorrectRequestWithWrongCourseDegree")){
	   		  
				 testRequestParameter.setCourseName( "Angewandte Informatik" );
		    	 testRequestParameter.setCourseDegree( "BachelorofScience" );
		    	 testRequestParameter.setCourseTerm( "Sommersemester" );
				 
				 
	    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
	   		  
	     }
		 else if(testName.getMethodName().equals("TESTC_handleInCorrectRequestWithWrongCourseTerm")){
	   		  
			 testRequestParameter.setCourseName( "Angewandte Informatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Trimester" );
			 
			 
    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
   		  
         }	
		 else if(testName.getMethodName().equals("TESTD_handleInCorrectFileRequest")){
   		  
			 testRequestParameter.setCourseName( "Angewandte Informatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("PDFFileAsset") ).toFile());
   		  
    	 }
		 else if(testName.getMethodName().equals("TESTE_handleCorrectFileRequestForAISEBa")){
    		  
			 testRequestParameter.setCourseName( "Angewandte Informatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
   		  
    	 }
		  
		 else if(testName.getMethodName().equals("TESTF_handleCorrectFileRequestForLectureScheduleWiInfBA")){
   		  
			 testRequestParameter.setCourseName( "Wirtschaftsinformatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleWiInfBA") ).toFile());
   		  
    	 }
     }
      
	 
     @Test
     @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTA_handleInCorrectRequestWithWrongCourseName() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleFileParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(badRequest)
	         .andDo(MockMvcResultHandlers.print());
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		System.out.println(); 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
     
	 
     @Test
	 @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTB_handleInCorrectRequestWithWrongCourseDegree() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleFileParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(badRequest)
	         .andDo(MockMvcResultHandlers.print());
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		System.out.println(); 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
     
	 
	 @Test
	 @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTC_handleInCorrectRequestWithWrongCourseTerm() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleFileParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(badRequest)
	         .andDo(MockMvcResultHandlers.print());
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		System.out.println(); 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
     
     
    
	 @Test 
	 @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTD_handleInCorrectFileRequest() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleFileParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
			 .andExpect(badRequest)
	         .andDo(MockMvcResultHandlers.print());
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
	
     
     @Test
     @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTE_handleCorrectFileRequestForAISEBa() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 

	     FileParameter uploadedTestParameter = new FileParameter(testRequestParameter.createCourseScheduleParam().getCourse());
	        
	     FileNameResolver resolvedFileName =  FileNameProvider.provideFileNameResolverForCourseScheduleFile(uploadedTestParameter);
	         
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleFileParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                            
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(ok)
	         .andDo(MockMvcResultHandlers.print());
			  
			  
			  final String uploadedFileNameToCheck = resolvedFileName.getResolvedFileName().toString();

			  resources.database.entities.File.Files uploadedFile = filesRepo.read(uploadedFileNameToCheck);
		 
		      assertThat("The file was not recognized as deleted", uploadedFile.isFileUploaded(), is(true));
	 
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 System.out.println();
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
     
     
     @Test
     @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTF_handleCorrectFileRequestForLectureScheduleWiInfBA() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 FileParameter uploadedTestParameter = new FileParameter(testRequestParameter.createCourseScheduleParam().getCourse());
	        
		 FileNameResolver resolvedFileName =  FileNameProvider.provideFileNameResolverForCourseScheduleFile(uploadedTestParameter);
		         
		 
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleFileParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                            
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(ok)
	         .andDo(MockMvcResultHandlers.print());
			 
			  final String uploadedFileNameToCheck = resolvedFileName.getResolvedFileName().toString();

			  resources.database.entities.File.Files uploadedFile = filesRepo.read(uploadedFileNameToCheck);
		 
		      assertThat("The file was not recognized as deleted", uploadedFile.isFileUploaded(), is(true));
	 
			 
			  
			  
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 System.out.println();
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
 
}
