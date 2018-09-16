package core.Test.authentication;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters; 
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths; 
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule; 
import org.junit.rules.TestName; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;  
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; 
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import core.TestContext.ControllerTestApplicationContext;
import core.TestContext.DatabaseTestApplicationContext;
import core.TestContext.ScheduleFileUploadParam;
import core.TestContext.TestUser;
import core.controller.pages.LoginPageController;
import core.controller.upload.schedule.CourseScheduleFileUploadController; 
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler;
import resources.database.entities.factory.UserAccountsManager;
import core.Test.controller.test;
import core.TestContext.AuthenticationTestApplicationContext; 

@ContextConfiguration( classes={  AuthenticationTestApplicationContext .class  })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)  
@WebMvcTest(  LoginPageController .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class UserLoginTest {

	@Autowired
	private MockMvc mockMvc;
 
	private static TestUser validAdmin; 
	
	private static TestUser invalidAdmin; 
	
	private static String correctAdminPassword = "root";
	
	private static String invalidAdminPassword = "notroot"; 
	
	private static String correctUserName = ")MZ7!N80"; 
	
	private static String wrongUserName = "Rainer";
	
	@Autowired 
	private   UserAccountsManager userAccountManager; 
	
	@BeforeClass
	public static void setUpUser() {
		  
		validAdmin = new TestUser(correctUserName, correctAdminPassword);
		  
		invalidAdmin = new TestUser(wrongUserName, invalidAdminPassword);
		  
	}
	
	
	@Test
	public void testUserLogin() throws Exception {
		
		  RequestBuilder requestBuilder =  MockMvcRequestBuilders.post("/perform_login")
				  								.param("loginname", invalidAdmin.getUserName())
				  								.param("password", invalidAdmin.getUserPassword());
		
		  ResultMatcher goodRequest = MockMvcResultMatchers.status()
                  .isOk();
		  
		  
		  
		  mockMvc.perform(requestBuilder)
		  			.andDo(MockMvcResultHandlers.print())
		  			.andExpect(goodRequest);
		
	}
	
	
	
	
}
