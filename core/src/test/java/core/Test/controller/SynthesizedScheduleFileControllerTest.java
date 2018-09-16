package core.Test.controller;
   
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.File;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
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
import core.controller.parameter.schedule.ScheduleParam;
import core.controller.upload.schedule.CourseScheduleFileUploadController;
import resources.components.elements.POJO.Schedule.Lectures.CoursePOJO;
import resources.components.filehandler.PathManager;
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler;
import resources.components.filehandler.filesynthesizer.LectureScheduleSynthesizer;  

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( CourseScheduleFileUploadController .class)
public class  SynthesizedScheduleFileControllerTest {
 	  
	 private static ScheduleParam  requestParameter;
	
	 @Autowired
	 private MockMvc mockMvc;
  
	 
	 @Qualifier("PathManager to JSONFiles") 
	 @Autowired
	 private PathManager pathManager;
	  
	 @Autowired
 	 private LectureScheduleSynthesizer m_synthesizer;
		
	 @Autowired
     public PersistenceCourseScheduleJSONFileHandler handler; 
	 

     @BeforeClass
	 public static void buildCorrectRequest() {
		 
    	 requestParameter = new ScheduleParam  ();
		 
    	 requestParameter.setCourseName("Angewandte Informatik");
    	 requestParameter.setCourseDegree("Bachelor Of Science");
    	 requestParameter.setCourseTerm("Wintersemester");
    	
	 } 
     
     @Rule
     public TestName testName = new TestName();

     @Before
     public void setUpWrongFileAsset() {
      
    	 
    	 if(testName.getMethodName().equals("TESTB_handleCorrectRequest")){
    		 
    		File correctFile = Paths.get( pathManager.getPathToOperateOn().toString(), "AllLecturesSchedule").toFile();
    		   
    		File wrongFile = Paths.get( pathManager.getPathToOperateOn().toString(), "AllLecturesScheduleOriginal").toFile();

    		 
    		wrongFile.renameTo(correctFile); 
    		 
     		//requestParameter.setCourseName("MathematikInformatik");
     	 }
    	 
         else if(testName.getMethodName().equals("TESTC_handleWrongCoureNameRequest")){
    		 
    		requestParameter.setCourseName("MathematikInformatik");
    	 } 
     }
     
    
     //@Test
	 public void TESTA_handleMissingAllLecturesSchedule() throws Exception {
	    
    	File correctFile = Paths.get( pathManager.getPathToOperateOn().toString(), "AllLecturesSchedule").toFile();
   
		File wrongFile = Paths.get( pathManager.getPathToOperateOn().toString(), "AllLecturesScheduleOriginal").toFile();

	  
		if(correctFile.renameTo(wrongFile)){
		
			assertTrue(true);
		}else{
			
			assertTrue(false);
			System.exit(0);
		}
		
	 }
     
     
     //@Test
	 public void TESTB_handleCorrectRequest() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(requestParameter);
		  
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Synthesize")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                  
			  
			 // Here we read the expected json String as a File From the disk 
			 
			 CoursePOJO pojo = (CoursePOJO) m_synthesizer.synthesizeAssets("CourseScheduleAngewandteinformatikBachelorofscienceWintersemester");
		
			 // we will ue it to compre the json, that we receive from the server with the json that we are expecting
			 String jsonStringToCompare = mapper.writeValueAsString(pojo);
				
			   
			  mockMvc.perform(builder)
	         .andExpect(ok)
	         .andExpect(content().json(jsonStringToCompare))
	         .andDo(MockMvcResultHandlers.print());
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 
		/// mockMvc.perform(post("/Upload/Schedule/Course").buildRequest(servletContext))
	 }
	
	 
     //@Test
	 public void TESTC_handleWrongCoureNameRequest() throws Exception {
	      
	 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(requestParameter);
		 
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Synthesize")
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
