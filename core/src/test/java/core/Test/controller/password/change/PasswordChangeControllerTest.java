package core.Test.controller.password.change;
 
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.io.IOException; 
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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; 
import com.fasterxml.jackson.databind.ObjectMapper; 
import core.TestContext.ControllerTestApplicationContext;
import core.TestContext.utils.PasswordChangeTestParameter;
import core.TestContext.utils.TestUserConfiguration;
import core.TestContext.utils.cases.FileAssetTestCase;
import core.TestContext.utils.parameter.schedule.ScheduleParamWithoutFileAsset;
import core.backend.REST.general.controller.REST.NonResponseController;
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.nonfileasset.notice.controller.add.AddNoticeController;
import core.backend.REST.nonfileasset.password.change.controller.PasswordChangeController;  
import core.configuration.authentication.utils.AuthorizationUserDetailsService;
import resources.components.elements.POJO.Password.change.PasswordChangePOJO;
import resources.database.entities.Accounts.Accounts; 
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager;
import resources.database.entities.factory.account.CoordinatorAccount; 
import resources.database.repository.ResetUrlsRepository;
import resources.utils.general.GeneralPurpose;
import resources.utils.user.AuthorizedUserAccount;
import resources.utils.user.AuthorizingUser;
 
@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@WebMvcTest(ResponseController.class)
@EnableAspectJAutoProxy(proxyTargetClass=true) 
public class PasswordChangeControllerTest 
											extends FileAssetTestCase<ScheduleParamWithoutFileAsset>{
  
	@Autowired
	private MockMvc mockMvc;
	  
	@Autowired 
	private UserAccountsManager m_UserAccountsManager; 
	 
    @Autowired 
    private  ResetUrlsRepository m_ResetUrlsRepository; 
	 
	@Autowired 
	private  TestUserConfiguration m_TestUserConfiguration;
		
	 
	@Autowired 
	@Qualifier("userDetailsService")
	private  AuthorizationUserDetailsService userDetailsService;
	   
	private static PasswordChangeTestParameter testRequestParameter;

	private static AuthorizingUser testAuthorizingUser;
	 
	private static AuthorizedUserAccount testAuthorizedAccount;
	 
	private final static String testUserName = "Anders";
	  
	 
	private final String newTestUserPassword = "toor";
	 
	private final static  String testUserEmail = "lilbreivik@gmail.com";
	 
	 
    private static AccountTypes testUserAcconutType = AccountTypes.CONTRIBUTOR; 
	 
	private static Accounts testAcount = null; 
	 
	private static Accounts m_testAccount;	
	
	private static Users m_testUser;
   
	@Rule
    public TestName testName = new TestName();

    
    
    @Before
    public void setUpParameter() throws IOException {
   
		 if(testName.getMethodName().equals("TESTA_SubTestA_tryToChangePassword")){
	  
		 	 
  			 m_testUser = m_UserAccountsManager.createUser( m_TestUserConfiguration.TEST_USER_EMAIL, m_TestUserConfiguration.TEST_USER_NAME);

  		     m_UserAccountsManager.addUser(m_testUser);
  			    	
  		     m_testAccount = m_UserAccountsManager.createAccount(m_testUser, new CoordinatorAccount(m_testUser, m_TestUserConfiguration.TEST_USER_PASSWORD));
  			     
		 
			PasswordChangePOJO pojo = new PasswordChangePOJO();
			
			pojo.setNewPassword(newTestUserPassword);
			pojo.setNewPasswordRepeated(newTestUserPassword); 
			pojo.setExistingPassword(m_TestUserConfiguration.TEST_USER_PASSWORD); 
			
			 
			testRequestParameter = new PasswordChangeTestParameter(pojo);
			 
   	     }
		 else if(testName.getMethodName().equals("TESTA_SubTestB_tryToChangePasswordWithWrongStoredPassword")){
			   
			  
			 	// password now incorrect
			 
				final String safedtestUserPassword = "root"; 
				  
				final String newTestUserPassword = "root"; 
				
				PasswordChangePOJO pojo = new PasswordChangePOJO();
				
				pojo.setNewPassword(newTestUserPassword);
				pojo.setNewPasswordRepeated(newTestUserPassword); 
				pojo.setExistingPassword(safedtestUserPassword); 
				
				 
				testRequestParameter = new PasswordChangeTestParameter(pojo);
				 
	   	   }
		 else if(testName.getMethodName().equals("TESTA_SubTestB_tryToChangePasswordWithWrongStoredPassword")){
			  
			  
			 	// password now incorrect
			 
				final String newPassword = "unequal"; 
				  
				
				PasswordChangePOJO pojo = new PasswordChangePOJO();
				
				pojo.setNewPassword(newPassword);
				pojo.setNewPasswordRepeated(newTestUserPassword); 
				pojo.setExistingPassword(newTestUserPassword); 
				
				 
				testRequestParameter = new PasswordChangeTestParameter(pojo);
				  
	   	     }
       
    }
     

     @Test  
	 public void TESTA_SubTestA_tryToChangePassword() throws Exception {
			
       ObjectMapper mapper = new ObjectMapper();
		 
       
       UsernamePasswordAuthenticationToken auth_Token = m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
       	 
       SecurityContextHolder.getContext().setAuthentication(auth_Token );
		
       
       String jsonInString = mapper.writeValueAsString (testRequestParameter );
    
	   ResultMatcher okRequest = MockMvcResultMatchers.status()
                 .isOk();
	     
	   MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(PasswordChangeController.PasswordChangeControllerURL)
			   
			   .contentType(MediaType.APPLICATION_JSON_VALUE)
                         .content(jsonInString);
       
       
		mockMvc.perform(builder)
	     	.andExpect(okRequest) 
	    	.andDo(MockMvcResultHandlers.print());
		   
	 }
    
     
     @Test  
	 public void TESTA_SubTestB_tryToChangePasswordWithWrongStoredPassword() throws Exception {
			
       ObjectMapper mapper = new ObjectMapper();
		
       UsernamePasswordAuthenticationToken auth_Token = m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
     	 
       SecurityContextHolder.getContext().setAuthentication(auth_Token );
		
       
       String jsonInString = mapper.writeValueAsString (testRequestParameter );
    
	   ResultMatcher  badRequest = MockMvcResultMatchers.status()
                 .isBadRequest();
	     
	   MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(PasswordChangeController.PasswordChangeControllerURL)
			    
			   .contentType(MediaType.APPLICATION_JSON_VALUE)
                         .content(jsonInString);
       
       
		mockMvc.perform(builder)
	     	.andExpect(badRequest) 
	    	.andDo(MockMvcResultHandlers.print());
		   
	 }
    
     
     @Test  
   	 public void TESTA_SubTestC_tryToChangePasswordWithUnequalNewPasswords() throws Exception {
   			
       ObjectMapper mapper = new ObjectMapper();
  
       UsernamePasswordAuthenticationToken auth_Token = m_TestUserConfiguration.createAuthentificationToken(m_testAccount);
     	 
       SecurityContextHolder.getContext().setAuthentication(auth_Token );
		
       
       String jsonInString = mapper.writeValueAsString (testRequestParameter );
       
   	   ResultMatcher  badRequest = MockMvcResultMatchers.status()
                    .isBadRequest();
   	     
   	   MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(PasswordChangeController.PasswordChangeControllerURL)
   			    
   			   .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(jsonInString);
          
          
   		mockMvc.perform(builder)
   	     	.andExpect(badRequest) 
   	    	.andDo(MockMvcResultHandlers.print());
   		   
   	 }
}
