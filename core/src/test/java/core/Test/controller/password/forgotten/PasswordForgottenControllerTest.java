package core.Test.controller.password.forgotten;
 
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.io.IOException; 
import java.util.List;

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
import core.TestContext.utils.ScheduleFileUploadParam;
import core.backend.REST.nonfileasset.password.forgotten.controller.PasswordForgottenController;
import core.backend.REST.nonfileasset.password.forgotten.parameter.PasswordForgottenRequest; 
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.Accounts.ResetURLs;
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager;
import resources.database.entities.factory.account.ContributorAccount;
import resources.database.repository.AccountsRepository;
import resources.database.repository.ResetUrlsRepository;
import resources.utils.pathmanager.PathManager;

@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@TestPropertySource({"classpath:application.test.properties", "classpath:email.properties"})
@WebMvcTest(PasswordForgottenController  .class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class PasswordForgottenControllerTest {

	 private static ScheduleFileUploadParam testRequestParameter; 
	  
	 @Autowired
	 private MockMvc mockMvc;
 	  
	 @Autowired 
	 private UserAccountsManager m_UserAccountsManager; 
	 
	 
	 @Autowired 
	 private  ResetUrlsRepository m_ResetUrlsRepository; 
	 
	   

	 private static String testUserName = "Anders";
	 
	 private static  String testUserEmail = "lilbreivik@gmail.com";
	 
	 private static AccountTypes testUserAcconutType = AccountTypes.CONTRIBUTOR; 
	 
	 private static Accounts testAcount = null; 
	 
	 private static Users testUser = null; 
	 
	  
     @Rule
     public TestName testName = new TestName();

     @Before
     public void setUpParameter() throws IOException {
    
		 if(testName.getMethodName().equals("TESTA_checkIfWeCanAcquireAResetURL")){
	   	
			 Users testUser =   m_UserAccountsManager.createUser(testUserEmail, testUserName);
			 
			  
			 m_UserAccountsManager.addUser(testUser);

			 m_UserAccountsManager.createAccount(testUser, new ContributorAccount(testUser, "root"));
			 
			 testAcount =  m_UserAccountsManager.getAccountsByType(testUser, AccountTypes.CONTRIBUTOR).get(0);
			 
			 
    	 }  
        
     }
     
     @Test
 //    @WithMockUser(username = "RUDI", password = "root" )
	 public void TESTA_checkIfWeCanAcquireAResetURL() throws Exception {
			
        ObjectMapper mapper = new ObjectMapper();
		  
			 
        ResultMatcher redirect = MockMvcResultMatchers.status()
                .is3xxRedirection(); 
 
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( PasswordForgottenController.PasswordForgottenControllerURL)
                        .param(PasswordForgottenRequest.USER_E_MAIL_PARAMETER, testUserEmail)
                        .param(PasswordForgottenRequest. ACCOUNT_TYPE_PARAMETER, testUserAcconutType.toString());
                        
		   
		mockMvc.perform(builder)
	     	.andExpect(redirect) 
	    	.andDo(MockMvcResultHandlers.print());
		  
		  
		  List<ResetURLs> resetUrls =  m_ResetUrlsRepository.read();
			 
		  
		  assertThat("The Reset URL was not created", not(resetUrls.size() < 1));
		 
		  assertThat("Wrong Ammount of ResetURLs were created", not(resetUrls.size() > 1));
		   
		  ResetURLs resetURL =  resetUrls.get(0);
		  
		  assertThat("Account does not match the expected one", not(resetURL.getAccount() != testAcount));
		  
		  assertThat("restURL was not set " , not(! resetURL.getUrlvalue().equals("")));
		   
	 }
     
     
     @Test 
     public void TESTB_checkIfWeCannotAcquireAResetURLIfEMailIsUnknown() throws Exception {
    			
   
    	 ObjectMapper mapper = new ObjectMapper();
    		  
    	 ResultMatcher redirect = MockMvcResultMatchers.status()
                    .is3xxRedirection();
    	 
    	 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( PasswordForgottenController.PasswordForgottenControllerURL)
                            .param(PasswordForgottenRequest.USER_E_MAIL_PARAMETER, "drachenlord1510@gmail.com")
                            .param(PasswordForgottenRequest. ACCOUNT_TYPE_PARAMETER, testUserAcconutType.toString());
                            
    		  
    		mockMvc.perform(builder)
    	     	.andExpect(redirect) 
    	    	.andDo(MockMvcResultHandlers.print());
    		  
    		  
    		  List<ResetURLs> resetUrls =  m_ResetUrlsRepository.read();
    			 
    		   
    		  assertThat("Wrong Ammount of ResetURLs were created", not(resetUrls.size() == 1));
    		  
      }
     
     
     @Test 
     public void TESTC_SubtestA_checkIfWeCannotAcquireAResetURLIfAccountTypeIsUnknown() throws Exception {
    			
   
    	 ObjectMapper mapper = new ObjectMapper();
    		  
    	 ResultMatcher redirect = MockMvcResultMatchers.status()
                    .is3xxRedirection();
    	 
    	 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( PasswordForgottenController.PasswordForgottenControllerURL)
                            .param(PasswordForgottenRequest.USER_E_MAIL_PARAMETER, testUserEmail)
                            .param(PasswordForgottenRequest. ACCOUNT_TYPE_PARAMETER, "operator");
                            
    		  
    		mockMvc.perform(builder)
    	     	.andExpect(redirect) 
    	    	.andDo(MockMvcResultHandlers.print());
    		  
    		  
    		  List<ResetURLs> resetUrls =  m_ResetUrlsRepository.read();
    			 
    		   
    		  assertThat("Wrong Ammount of ResetURLs were created", not(resetUrls.size() == 1));
    		  
      }
     
     @Test 
     public void TESTC_SubtestB_checkIfWeCannotAcquireAResetURLIfAccountTypeIsrong() throws Exception {
    			
   
    	 ObjectMapper mapper = new ObjectMapper();
    		  
    	 ResultMatcher redirect = MockMvcResultMatchers.status()
                    .is3xxRedirection();
    	 
    	 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( PasswordForgottenController.PasswordForgottenControllerURL)
                            .param(PasswordForgottenRequest.USER_E_MAIL_PARAMETER, testUserEmail)
                            .param(PasswordForgottenRequest. ACCOUNT_TYPE_PARAMETER, AccountTypes.ADMIN.toString());
                            
    		  
    		mockMvc.perform(builder)
    	     	.andExpect(redirect) 
    	    	.andDo(MockMvcResultHandlers.print());
    		  
    		  
    		  List<ResetURLs> resetUrls =  m_ResetUrlsRepository.read();
    			 
    		   
    		  assertThat("Wrong Ammount of ResetURLs were created", not(resetUrls.size() == 1));
    		  
      }
}
