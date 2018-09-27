package core.Test.controller.upload;
 
  

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
import core.TestContext.ScheduleFileUploadParam;
import core.backend.REST.fileasset.upload.controller.CourseScheduleUploadFileController;
import core.backend.REST.general.request.MasteRESTFileRequest;
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler; 

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
     public PersistenceCourseScheduleJSONFileHandler handler; 
	 

 
	/* public LectureScheduleFileUploadControllerTest(String courseName, 
			 											String courseTerm, 
			 												String courseDegree, 
			 													String lectureFileName, 
			 														String otherLectureFileName) {
		 m_courseName = courseName; 
		 m_courseTerm = courseTerm; 
		 m_courseDegree = courseDegree; 	
		 m_lectureFileName = lectureFileName;
		 m_otherLectureFileName = otherLectureFileName;
	 }
	 */
	  
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
		 else if(testName.getMethodName().equals("TESTE_handleCorrectFileRequest")){
    		  
			 testRequestParameter.setCourseName( "Angewandte Informatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		 testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
   		  
    	 }
		 
	/*	 else if(testName.getMethodName().equals("TESTB_handleAnotherXLSFile")){
    		 
			 
    		 correctParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(m_otherLectureFileName )).toFile());
    	 }
    	 else if(testName.getMethodName().equals("TESTC_checkIfCoursesDoNotGetCountedTwice")){
    		 
    		 correctParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(m_otherLectureFileName )).toFile());
    	 }
		  */
      
     }
      
	 
     @Test
	 public void TESTA_handleInCorrectRequestWithWrongCourseName() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
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
	 public void TESTB_handleInCorrectRequestWithWrongCourseDegree() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
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
	 public void TESTC_handleInCorrectRequestWithWrongCourseTerm() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
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
	 public void TESTD_handleInCorrectFileRequest() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
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
	 public void TESTE_handleCorrectFileRequest() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(ok)
	         .andDo(MockMvcResultHandlers.print());
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 System.out.println();
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
 
}
