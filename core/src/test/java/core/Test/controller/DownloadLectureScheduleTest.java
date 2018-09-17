package core.Test.controller;

import static core.utils.Constants.UploadFileName.LectureSchedule; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
import java.io.File; 
import java.nio.file.Files; 
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
import core.controller.parameter.ScheduleParam;
import core.controller.upload.schedule.CourseScheduleFileUploadController; 
import core.utils.names.UploadFileNameResolver;
import resources.components.filehandler.PathManager;
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.names.INameResolver; 

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( CourseScheduleFileUploadController .class)
public class DownloadLectureScheduleTest {


	 private static ScheduleParam correctParameter;
	
	 private static ScheduleParam  inCorrectParameter;
	 
	 private static INameResolver nameResolver; 
	 
	 private static UploadFileNameResolver dowloadedFileNameResolver; 
	 
	 @Autowired
	 private MockMvc mockMvc;
  
	 @Autowired
	 @Qualifier("PathManager to Lecture Assets")
	 private PathManager pathManagerToLecutreAssets; 
	 
     @BeforeClass
	 public static void buildCorrectRequest() {
		 
    	 correctParameter = new ScheduleParam ();	
    	 
    	 inCorrectParameter = new ScheduleParam ();	
    	 
	 }
	 
     @Rule
     public TestName testName = new TestName();

     
     @Before
     public void setUpRequestParameter() {
      
    	 if(testName.getMethodName().equals("TESTA_checkIfWeCanDownloadAFileWithCorrectRequestParameter")){
    		 
        	 correctParameter.setCourseName("Angewandte Informatik");
        	 correctParameter.setCourseDegree("Bachelor Of Science");
        	 correctParameter.setCourseTerm("Wintersemester");
    		
        	 nameResolver = (resolvedScheduleFileName) -> LectureSchedule.toString().concat( resolvedScheduleFileName );
				
        	 dowloadedFileNameResolver = new UploadFileNameResolver(correctParameter, nameResolver);
				
			 
    	 }
    	 else if(testName.getMethodName().equals("TESTB_checkIfWeCannotDownloadAFileWithInCorrectRequestParameter")){
    		 
    		 	
    		 inCorrectParameter.setCourseName("AngewandteInformatik");
    		 inCorrectParameter.setCourseDegree("Bachelor Of Science");
    		 inCorrectParameter.setCourseTerm("Wintersemester");
			
    	 }
    	 /*
    	 else if(testName.getMethodName().equals("TESTC_checkIfCoursesDoNotGetCountedTwice")){
    			
    	 }*/
      
     }
	
	 @Test
	 public void TESTA_checkIfWeCanDownloadAFileWithCorrectRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(correctParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( dowloadedFileNameResolver.getResolvedUploadedFileName()));
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(ok)
		      .andExpect(content().bytes(expectedDownloadedContent ))
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
	 
	 @Test
	 public void TESTB_checkIfWeCannotDownloadAFileWithInCorrectRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(inCorrectParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
		 
			  mockMvc.perform(builder)
		      .andExpect(badRequest ) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
	 
	 @Test
	 public void TESTC_checkIfWeCannotDownloadAFileWithCorrectRequestParameterButWithissingFile() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(correctParameter);
		 
			 System.out.println(jsonInString);
			  
			 OrdinaryFileHandler.moveFile(pathManagerToLecutreAssets.getPathToOperateOn().toFile(), 
					 (new File(dowloadedFileNameResolver.getResolvedUploadedFileName().toString().concat("bak")).toPath()));
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
		 
			  mockMvc.perform(builder)
		      .andExpect(badRequest ) 
		      .andDo(MockMvcResultHandlers.print());
			 
			  
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
}
