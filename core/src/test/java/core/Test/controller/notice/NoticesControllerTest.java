package core.Test.controller.notice;
 
    
import java.nio.file.Files;
import java.nio.file.Paths; 
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
import org.springframework.security.test.context.support.WithUserDetails;
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
import core.TestContext.utils.LecturesListPOJO;
import core.TestContext.utils.NoticeTestParameter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.IOException; 
import org.springframework.context.annotation.EnableAspectJAutoProxy; 
import core.backend.REST.nonfileasset.notice.controller.add.AddNoticeController;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.utils.pathmanager.PathManager; 

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource("classpath:application.properties")
@WebMvcTest( AddNoticeController.class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  NoticesControllerTest {
   
	 private static NoticeTestParameter testNotice; 
	  
	 @Autowired
	 private MockMvc mockMvc;
 	  
	 
	 @Autowired
	 @Qualifier("Path to Test Resources")
	 private PathManager pathManagerToLecutreAssets; 
 
	  
     @Rule
     public TestName testName = new TestName();

      
     @Before
     public void setUpParameter() throws IOException {
    
    	 ObjectMapper mapper = new ObjectMapper();
    	 
    	 NoticesPOJO pojo = new NoticesPOJO();
    	 
		 if(testName.getMethodName().equals("TESTA_checkIfWeCanAddANewNotice")){
	   		 		  
			 LecturesListPOJO lecturesListPOJO = mapper.readValue( Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("lecturesList") ).toFile(), LecturesListPOJO.class);
			 
			 pojo.setScheduledLectures(lecturesListPOJO.getLecturesList());
			 
			 pojo.setNotice("This is a Test");
			 
			 pojo.setNoticeHeadline("This is a Headline ");
			 
			 testNotice = new NoticeTestParameter(pojo);
    	 } 
		 
		 else if(testName.getMethodName().equals("TESTC_checkIfWeCanDeleteNotice")){
		 		  
				 LecturesListPOJO lecturesListPOJO = mapper.readValue( Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat("lecturesList") ).toFile(), LecturesListPOJO.class);
				 
				 pojo.setScheduledLectures(lecturesListPOJO.getLecturesList());
				 
				 pojo.setNotice("This is a Test");
				 
				 pojo.setNoticeHeadline("This is a Headline ");
				 
				 testNotice = new NoticeTestParameter(pojo);
		} 
      
     }
    
     
     @Test
     @WithUserDetails("rudi") 
	 public void TESTA_checkIfWeCanAddANewNotice() throws Exception {
		
   
        ObjectMapper mapper = new ObjectMapper();
		  
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testNotice);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Notice/Add")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
	                           .content(jsonInString);
	                           
			 
			 String  expectedNoticeContent = new String(Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "testNotice")), "UTF-8");
			  
			  mockMvc.perform(builder)
		      .andExpect(ok) 
		      .andExpect(content().json(expectedNoticeContent))
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 } 
    
      
     @Test
     @WithMockUser(username = "RUDI", password = "root" )
	 public void TESTB_checkIfWeCanReadNotice() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		  
		 try {
			
			 String jsonInString = mapper.writeValueAsString(testNotice);
		 
			 System.out.println(jsonInString);
			 
			 ResultMatcher ok = MockMvcResultMatchers.status()
	                   .isOk();

			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Notice/Read/Specific")
	                           .contentType(MediaType.APPLICATION_JSON_VALUE);
	                           
			 
			 String  expectedNoticeContent = new String(Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "testNotice")), "UTF-8");
			  
			  mockMvc.perform(builder)
		      .andExpect(ok) 
		      .andExpect(content().json(expectedNoticeContent))
		      .andDo(MockMvcResultHandlers.print());
			  
			 
		 } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	 }
     
     
     @Test
     @WithMockUser(username = "RUDI", password = "root" )
	 public void TESTC_checkIfWeCanDeleteNotice() throws Exception {
			
    	  ObjectMapper mapper = new ObjectMapper();
		  
 		 try {
 			
 			 String jsonInString = mapper.writeValueAsString(testNotice);
 		 
 			 System.out.println(jsonInString);
 			 
 			 ResultMatcher ok = MockMvcResultMatchers.status()
 	                   .isOk();

 			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/Notice/Delete")
 	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
 	                           .content(jsonInString);
 	                           
 			 
 			 String  expectedNoticeContent = new String(Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "emptyTestNotice")), "UTF-8");
 			  
 			  mockMvc.perform(builder)
 		      .andExpect(ok) 
 		      .andExpect(content().json(expectedNoticeContent))
 		      .andDo(MockMvcResultHandlers.print());
 			  
 			 
 		 } catch (JsonProcessingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		 }
 		 
	 }
 
}
