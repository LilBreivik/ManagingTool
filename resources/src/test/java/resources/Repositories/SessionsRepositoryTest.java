package resources.Repositories;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.Accounts.Sessions;
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager;
import resources.database.entities.factory.account.CoordinatorAccount;
import resources.database.repository.AccountsRepository;
import resources.database.repository.SessionsRepository;
import testcontext.FileHandlerTestApplicationContext;



/**
 * We need to keep 
 * im mind, that we need some test 
 * users, to simulate logging-processing ... 
 * 
 * So this tests got some dependency to UserAccountManagerTest
 * */

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class SessionsRepositoryTest {
 
    private static Sessions testSessions; 
    
    private static Users testUsers;
     
    private static Accounts coordinatorAccount; 
    
    private static int accountId;
    
	@Autowired 
	private  UserAccountsManager userAccountsManager;

	@Autowired 
	private SessionsRepository sessionsRepo; 
	
	@Autowired 
	private AccountsRepository accountsRepo; 
	
    @BeforeClass
	public static void setUp() {
		
		testSessions = new Sessions();
		
		testUsers = new Users();
		  
		testUsers.setUserEmail("drachenlord1510@gmail.com");
		testUsers.setUserName("Rainer"); 
		 
		coordinatorAccount = new CoordinatorAccount(testUsers, "pasWort");
	}
    
      
    @Test
	public void TEST_A_Subtest_A_checkIfWeCanRegisterANewSession(){
	

		userAccountsManager.addUser(testUsers);
		
		coordinatorAccount = new CoordinatorAccount(testUsers, "pasWort");
		 
		userAccountsManager.createAccount(testUsers, coordinatorAccount);
		 
    	// created Session 
    	 
    	Sessions createdSession = sessionsRepo.createSession(coordinatorAccount);
    	
    	// register Session 
    	
    	 
    	
    	sessionsRepo.registerSession(createdSession);
    	
    	Sessions readSession = sessionsRepo.read(createdSession.getAccount() );
 
    	 
    	assertThat("The Id does not match the expected one ", not( createdSession.getAccount().getAccountId() ==  readSession.getAccount().getAccountId() ));	
    	
    	assertThat("The LastLogin does not match the expected one ", not( createdSession.getLastLogin() == readSession.getLastLogin() ));	
    	
    	assertThat("The LastloggedInAt does not match the expected one ", not( createdSession.getLastloggedInAt()  ==  readSession.getLastloggedInAt()));	
    	
    	assertThat("The LoggedInAt does not match the expected one ", not( createdSession.getLoggedInAt () == readSession.getLoggedInAt()));	
    	
    	assertThat("The LoggedOutAt does not match the expected one ", not( createdSession.getLoggedOutAt()  == readSession.getLoggedOutAt()));	
        
	}
    
    
    @Test
	public void TEST_A_Subtest_B_checkIfWeCanUnRegisterASession(){
	
    	 
    	Accounts account = accountsRepo.read(testUsers).get(0);
    	
    	
    	assertThat("account doe snot exist !!! ", not(  account == null ));
    	
    	// fetch session from the repository 
    	
    	Sessions readSession = sessionsRepo.read(account);
    	
     
    	assertThat("Session was not registered ", not( readSession.getLoggedInAt() != null ));	
    	
    	assertThat("Logout is not null !!! ", not( readSession.getLoggedOutAt() == null ));	
        
    	
    	// unregister Session 
    	
    	sessionsRepo.unRegisterSession(readSession);
    	 
    	
	    assertThat("Session was not unregistered ", not( readSession.getLoggedInAt() != null ));	
    	
    	assertThat("Logout is  null !!! ", not( readSession.getLoggedOutAt() == null ));	
        
	}
    
    @Test
	public void TEST_B_Subtest_A_checkIfWeCanRemoveSessions(){
    	
        Accounts account = accountsRepo.read(testUsers).get(0);
    	
    	
    	assertThat("account doe snot exist !!! ", not(  account == null ));
    	
    	// fetch session from the repository 
    	
    	Sessions readSession = sessionsRepo.read(account);
    
    	assertThat("the expected Session does not exist ", not( readSession == null ));	
    	
    	// if we delete the user, all accounts shall be deleted too, and the sessions shall be removed as well 
    	
    	userAccountsManager.deleteUser(testUsers);
  
     	// fetch session from the repository 
     	
        readSession = sessionsRepo.read(account);
     
     	assertThat("the expected Session does not exist ", not( readSession != null ));	
    }
    
    
}
