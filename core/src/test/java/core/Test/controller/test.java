package core.Test.controller;
 
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
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

import core.TestContext.ControllerTestApplicationContext; 
import core.TestContext.ScheduleFileUploadParam; 
import core.controller.upload.schedule.CourseScheduleFileUploadController;
import core.utils.names.UploadFileNameResolver; 
import resources.utils.names.INameResolver; 

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( CourseScheduleFileUploadController .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class test {



	 private static  ScheduleFileUploadParam correctParameter;
	 private static  ScheduleFileUploadParam inCorrectParameter; 
	 
	 private static INameResolver nameResolver; 
	 
	 private static UploadFileNameResolver dowloadedFileNameResolver; 
 
	 
	 @Autowired
	 private MockMvc mockMvc;
   

     @Rule
     public TestName testName = new TestName();

	private String m_courseName;

	private String m_courseDegree;

	private String m_courseTerm;
 
	 private File m_fileAsset; 
	 
     @Before 
     public  void setUpParameter() throws IOException {
    	 m_courseName = "Angewandte Informatik"; 
     	m_courseTerm = "Sommersemester"; 
 	    m_courseDegree = "Bachelor Of Science"; 	
 			 
 	    setFileAsset("LectureScheduleAISEBa");

    	 correctParameter = new  ScheduleFileUploadParam();	 
		inCorrectParameter = new  ScheduleFileUploadParam();
 		
		 
    	 if(testName.getMethodName().equals("TESTA_handleCorrectRequest")){
    		   
        	 correctParameter.setCourseName( m_courseName );
        	 correctParameter.setCourseDegree( m_courseDegree );
        	 correctParameter.setCourseTerm( m_courseTerm);
        	 correctParameter.setScheduleFile(m_fileAsset);
 
    	 }
    	 else  if(testName.getMethodName().equals("TESTB_handleInCorrectRequest")){
    		 
        	inCorrectParameter.setCourseName("AngewandteInformatik");
        	inCorrectParameter.setCourseDegree("Bachelor Of Science");
        	inCorrectParameter.setCourseTerm("Sommersemester");
      
    	 }
    	 
         else if(testName.getMethodName().equals("TESTC_handleInCorrectRequestWithXLSFileAsset")){
    		   
        	 
    		inCorrectParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/XLSFileAsset").toFile());
   		  
    	 }
    	 else if(testName.getMethodName().equals("TESTD_handleInCorrectRequestWithPDFFileAsset")){
    		 
    		inCorrectParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/PDFFileAsset").toFile());
   		  
    	 }
    	 else if(testName.getMethodName().equals("TESTE_handleInCorrectRequestWithWrongFileAndParameter")){
    		 
    		inCorrectParameter.setCourseDegree("BachelorOfScience");
    		inCorrectParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/PDFFileAsset").toFile());
   		  
    	 }
    	 else if(testName.getMethodName().equals("TESTF_handleInCorrectRequestIfAValidFileWasSentToAWrongController")){
    		 
    		inCorrectParameter.setCourseDegree("Bachelor Of Science"); 
    		inCorrectParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/CourseSchedule").toFile());
   		  
    	 }
		 
		  
     }
      
     private  void setFileAsset(String fileName) {
    	   	
      	m_fileAsset = Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(fileName) ).toFile();
    } 
     
     @Test
	 public void TESTA_handleCorrectRequest() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try { 
			 
			 String jsonInString = mapper.writeValueAsString(correctParameter);
	 		 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(ok)
	         .andDo(MockMvcResultHandlers.print());
			 
			  
			  System.out.println();
			  
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
	
	 @Test
	 public void TESTB_handleInCorrectRequest() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(inCorrectParameter);
		 
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(badRequest)
	         .andDo(MockMvcResultHandlers.print());
			  
			  
			  System.out.println();
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		  
	 }
	 
	 @Test
	 public void TESTC_handleInCorrectRequestWithXLSFileAsset() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(inCorrectParameter);
		 
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(badRequest)
	         .andDo(MockMvcResultHandlers.print());
			 
			  System.out.println();
			  
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		  
	 }
	 
	 @Test
	 public void TESTD_handleInCorrectRequestWithPDFFileAsset() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(inCorrectParameter);
		 
			 
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
		  
	 }
	 
	 
	 @Test
	 public void TESTE_handleInCorrectRequestWithWrongFileAndParameter() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(inCorrectParameter);
		 
			 
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
		  
	 }
	 
}
