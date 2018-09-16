package core.Test.controller;
 
   
import java.io.File;
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
import core.controller.upload.schedule.CourseScheduleFileUploadController; 
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler;; 
 
@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( CourseScheduleFileUploadController .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  CourseScheduleFileUploadControllerTest {
 
	 private String m_courseName; 
	 private String m_courseTerm; 
	 private String m_courseDegree; 
	
	 private String m_wrongCourseName; 
	 private String m_wrongCourseTerm; 
	 private String m_wrongCourseDegree; 
	 
	 private File m_fileAsset; 
	 
	 private static  ScheduleFileUploadParam correctParameter;
	 private static  ScheduleFileUploadParam inCorrectParameter; 

	 
	 public CourseScheduleFileUploadControllerTest(String courseName, 
			 											String courseTerm, 
			 												String courseDegree, 
			 													String courseFileName) {
		 m_courseName = courseName; 
		 m_courseTerm = courseTerm; 
		 m_courseDegree = courseDegree; 	
		 
		 setFileAsset(courseFileName); 
			 												
	 }
	 
	 
	 @Autowired
	 private MockMvc mockMvc;
 
	 
	 @Autowired
     public PersistenceCourseScheduleJSONFileHandler handler; 
	  
     
     @Rule
     public TestName testName = new TestName();

     @Before
     public void setUpParameter() throws IOException {
    
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
    
    
    public String getM_wrongCourseName() {
		return m_wrongCourseName;
	}


	public void setM_wrongCourseName(String m_wrongCourseName) {
		this.m_wrongCourseName = m_wrongCourseName;
	}


	public String getM_wrongCourseTerm() {
		return m_wrongCourseTerm;
	}


	public void setM_wrongCourseTerm(String m_wrongCourseTerm) {
		this.m_wrongCourseTerm = m_wrongCourseTerm;
	}


	public String getM_wrongCourseDegree() {
		return m_wrongCourseDegree;
	}


	public void setM_wrongCourseDegree(String m_wrongCourseDegree) {
		this.m_wrongCourseDegree = m_wrongCourseDegree;
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
