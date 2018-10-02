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
import core.TestContext.utils.ScheduleFileUploadParam;
import resources.components.filehandler.PathManager; 
import java.io.IOException;  
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import core.backend.REST.fileasset.download.controller.LectureScheduleFileDownloadController;
import core.utils.names.FileNameResolver; 
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler; 

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( LectureScheduleFileDownloadController .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  CourseScheduleFileDownloadControllerTest {
   
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
		 
		 else if(testName.getMethodName().equals("TESTD_checkIfWeCanDownloadLectureScheduleAISEBaWithCorrectRequestParameter")){
   		  
			 testRequestParameter.setCourseName( "Angewandte Informatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		// testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
   		  
    	 } 
		 
		 // TESTE_checkIfWeCanDownloadLectureScheduleWiInfBAWithCorrectRequestParameter
      
		 else if(testName.getMethodName().equals("TESTE_checkIfWeCanDownloadLectureScheduleWiInfBAWithCorrectRequestParameter")){
	   		  
			 testRequestParameter.setCourseName( "Wirtschaftsinformatik" );
	    	 testRequestParameter.setCourseDegree( "Bachelor Of Science" );
	    	 testRequestParameter.setCourseTerm( "Sommersemester" );
			 
			 
    		// testRequestParameter.setScheduleFile(Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("LectureScheduleAISEBa") ).toFile());
   		  
    	 } 
     }
     
     @Test
     @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTA_checkIfWeCanDownloadAFileWithInCorrectCourseNameInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam() );
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
     
     
	 @Test
     @WithMockUser(username = "DUSTIN79", password = "root" ) 
	 public void TESTB_checkIfWeCanDownloadAFileWithInCorrectDegreeInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam() );
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
     
	 @Test
     @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTC_checkIfWeCanDownloadAFileWithInCorrectTermInRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam() );
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher badRequest = MockMvcResultMatchers.status()
	                   .isBadRequest();
			 
			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
			  mockMvc.perform(builder)
		      .andExpect(badRequest) 
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
	  
      
     @Test
     @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTD_checkIfWeCanDownloadLectureScheduleAISEBaWithCorrectRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
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
     @WithMockUser(username = "DUSTIN79", password = "root" )
	 public void TESTE_checkIfWeCanDownloadLectureScheduleWiInfBAWithCorrectRequestParameter() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		 
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createCourseScheduleParam());
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Download/Schedule/Course")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
		 
			 System.out.println(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleAISEBa"));
			 
			 
			 byte[] expectedDownloadedContent = Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "LectureScheduleWiInfBA"));
			 
			 
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
