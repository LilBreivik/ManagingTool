package core.Test.controller;
 
 
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
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
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler; 

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( CourseScheduleFileUploadController .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  LectureScheduleFileUploadControllerTest {
 
	  
	 private static ScheduleFileUploadParam correctParameter;
	
	 private static ScheduleFileUploadParam  inCorrectParameter;
	 
	 private static ScheduleFileUploadParam  inCorrectFileAsset;
	 
	 private String m_courseName; 
	 private String m_courseTerm; 
	 private String m_courseDegree; 
	 private String m_lectureFileName;
	 private String m_otherLectureFileName;
	 
	 @Autowired
	 private MockMvc mockMvc;
 
	 
	 @Autowired
     public PersistenceCourseScheduleJSONFileHandler handler; 
	 

 
	 public LectureScheduleFileUploadControllerTest(String courseName, 
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
	 
	 
	 
	 
	 
     @Rule
     public TestName testName = new TestName();

     @Before
     public void setUpParameter() throws IOException {
    
		 correctParameter = new  ScheduleFileUploadParam();	 
		 inCorrectParameter = new  ScheduleFileUploadParam();
 		
		 correctParameter.setCourseName( m_courseName );
    	 correctParameter.setCourseDegree( m_courseDegree );
    	 correctParameter.setCourseTerm( m_courseTerm);
		 
		 if(testName.getMethodName().equals("TESTA_handleCorrectRequest")){
    		  
    		 correctParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(m_lectureFileName) ).toFile());
   		  
    	 }
		 
		 else if(testName.getMethodName().equals("TESTB_handleAnotherXLSFile")){
    		 
			 
    		 correctParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(m_otherLectureFileName )).toFile());
    	 }
    	 else if(testName.getMethodName().equals("TESTC_checkIfCoursesDoNotGetCountedTwice")){
    		 
    		 correctParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(m_otherLectureFileName )).toFile());
    	 }
		  
      
     }
      
	     
	 
    
     @Test
	 public void TESTA_handleCorrectRequest() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(correctParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(ok)
	         .andDo(MockMvcResultHandlers.print());
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
	
	 
     @Test
	 public void TESTB_handleAnotherXLSFile() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(correctParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			
			  mockMvc.perform(builder)
	         .andExpect(ok)
	         .andDo(MockMvcResultHandlers.print());
			
			  
			  
			  
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 
		 System.out.println("ddd");
		 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
     
     @Test
	 public void TESTC_checkIfCoursesDoNotGetCountedTwice() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(correctParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Upload/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 
			  mockMvc.perform(builder)
	         .andExpect(ok)
	         .andDo(MockMvcResultHandlers.print());
			   
			  assertEquals("The files differ!", 
					    FileUtils.readFileToString(Paths.get(System.getProperty("user.dir"), "/ScheduleFiles/JSONFiles/AllLecturesSchedule").toFile(), "utf-8"), 
					    FileUtils.readFileToString(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/AllLecturesScheduleAfterhandleAnotherXLSFile").toFile(), "utf-8"));
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
}
