package core.Test.controller.download;
 
   
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;  
import java.nio.file.Files; 
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
import resources.components.filehandler.PathManager; 
import java.io.IOException;  
import org.springframework.context.annotation.EnableAspectJAutoProxy; 
import core.TestContext.ScheduleFileUploadParam;
import core.backend.REST.fileasset.download.controller.LectureScheduleFileDownloadController;
import core.utils.names.FileNameResolver; 
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler; 

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( LectureScheduleFileDownloadController .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  LectureScheduleFileDownloadControllerTest {
  
	 private static ScheduleFileUploadParam testRequestParameter; 
	  
	 @Autowired
	 private MockMvc mockMvc;
 	 
	 @Autowired
     public PersistenceCourseScheduleJSONFileHandler handler; 
	 
	 @Autowired
	 @Qualifier("Path to Test Resources")
	 private PathManager pathManagerToLecutreAssets; 

	 private static FileNameResolver dowloadedFileNameResolver; 
 
	 
	  
     @Rule
     public TestName testName = new TestName();

     @Before
     public void setUpParameter() throws IOException {
    
		 testRequestParameter = new ScheduleFileUploadParam();	  
 		
		 
		 if(testName.getMethodName().equals("TESTA_checkIfWeCanDownloadAFileWithInCorrectCourseNameInRequestParameter")){
	   		  
			 testRequestParameter.setCourseName( "AngewandteInformatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		// testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
   		  
    	 } 
		 else if(testName.getMethodName().equals("TESTB_checkIfWeCanDownloadAFileWithInCorrectDegreeInRequestParameter")){
	   		  
				 testRequestParameter.setCourseName( "Angewandte Informatik" );
		    	 testRequestParameter.setCourseDegree( "Bachelor of Science" );
		    	 testRequestParameter.setCourseTerm( "Sommersemester" );
				 
				 
	    		// testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
	   		  
	     }
		 else if(testName.getMethodName().equals("TESTC_checkIfWeCanDownloadAFileWithInCorrectTermInRequestParameter")){
	   		  
				 testRequestParameter.setCourseName( "Angewandte Informatik" );
		    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
		    	 testRequestParameter.setCourseTerm( "Trisemester" );
				 
				 
	    		// testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
	   		  
	    	 } 
		 
		 else if(testName.getMethodName().equals("TESTD_checkIfWeCanDownloadAFileWithCorrectRequestParameter")){
   		  
			 testRequestParameter.setCourseName( "Angewandte Informatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		// testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
   		  
    	 } 
      
     }
     
     @Test
	 public void TESTA_checkIfWeCanDownloadAFileWithInCorrectCourseNameInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "AISEXLSFileAsset"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "AISEXLSFileAsset"));
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
     
     
     @Test
	 public void TESTB_checkIfWeCanDownloadAFileWithInCorrectDegreeInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "AISEXLSFileAsset"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "AISEXLSFileAsset"));
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
     
     @Test
	 public void TESTC_checkIfWeCanDownloadAFileWithInCorrectTermInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();
			 
			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "AISEXLSFileAsset"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "AISEXLSFileAsset"));
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
	 
      
     @Test
	 public void TESTD_checkIfWeCanDownloadAFileWithCorrectRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Lecture")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "AISEXLSFileAsset"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "AISEXLSFileAsset"));
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(ok)
		      .andExpect(content().bytes(expectedDownloadedContent ))
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
      
 
}
