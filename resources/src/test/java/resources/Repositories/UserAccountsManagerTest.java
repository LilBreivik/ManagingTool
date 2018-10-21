package resources.Repositories;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static resources.utils.general.Constants.ScheduleRootDiectory;
import static resources.utils.general.Constants.Directory.JSONFiles;
import static resources.utils.general.Constants.Directory.Users;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager;
import resources.database.entities.factory.account.AdminAccount; 
import resources.database.entities.factory.account.CoordinatorAccount;
import resources.database.repository.AccountsRepository;
import resources.database.repository.UsersRepository;
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class UserAccountsManagerTest {

    private static Users testUsers; 
	
    @Autowired 
	@Qualifier("JSONFileHandler for NoticesJSONFile")
	public GeneralJSONFileHandler<NoticesPOJO>  noticeJSONFileHandler;
    
    
	@Autowired 
	private UserAccountsManager userAccountsManager;
	
	@Autowired 
	private UsersRepository usersRepo;
	
	@Autowired 
	private AccountsRepository accountsRepo;
	
	 
	@BeforeClass
	public static void setUp() {
		
		testUsers = new Users();
		  
		testUsers.setUserEmail("drachenlord1510@gmail.com");
		testUsers.setUserName("Rainer"); 
		
	}
	
	@Test
	public void TEST_A_Subtest_A_checkIfWeCanReadFromTheRepository(){
			
		userAccountsManager.addUser(testUsers); 
			
		List<Users> expectedUsers = usersRepo.read().stream().filter( user -> user.getUserEmail().equals("drachenlord1510@gmail.com")).collect(Collectors.toList());
			
		// the testUser shall not exist before, but shall exist after updating the database 
			
		assertThat("The User Does not exist ", not(expectedUsers.size()  == 1));	
	}
	  
	@Test
	public void TEST_A_Subtest_B_checkIfWeCanCreateAccountsForSpecifcUsers(){
		
		try {
			 
			userAccountsManager.createAccount(testUsers, new AdminAccount(testUsers, "pasWort"));
			  
			// there shall just be 1 account for the test user 
			
			List<Accounts> acc =  userAccountsManager.getAccountsByType(testUsers, AccountTypes.ADMIN);      
					 
			assertThat("The Account was not created", not(acc  != null));
		}
		catch(IndexOutOfBoundsException indexError) {
			
			assertTrue("There is the wrong ammoount of Accounts for the expected User  in the test repository", false);
			
		}		
	}
	
	@Test
	public void TEST_B_Subtest_A_checkIfWeCanVerifyAUser(){
			 
		assertThat("The Account cannot be verified", not(userAccountsManager.verifyAccount(testUsers, AccountTypes.ADMIN, "pasWort")  == true ));		
	}
	
	
	@Test
	public void TEST_C_Subtest_A_checkIfWeCanChangeAUsersEmailAdressAndCascadeTheChangesToTheAccounts(){
	
		final String newUserEmail = "drachenlord1488@gmail.com"; 
		
		userAccountsManager.changeEmailOfUser(testUsers, newUserEmail);
		
		List<Accounts> accs =  userAccountsManager.getAccountsByType(testUsers, AccountTypes.ADMIN);      
		
	    Accounts acc  =   accs.get(0);
		
	    assertThat("There user does still exist ", not( acc.getAccountOwners().getUserEmail() .equals(newUserEmail)));
	     
	}
	
	@Test
	public void TEST_C_Subtest_B_checkIfWeCanDeleteAUserAndItsAccount(){
		 
		userAccountsManager.deleteUser(testUsers);
			
		List<Accounts> accs =  userAccountsManager.getAccountsByType(testUsers, AccountTypes.ADMIN);      
			  
		assertThat("There are still accounts for a deleted user ", not(accs == null));
	
		Users user = usersRepo.read(testUsers.getUserId());
	
		assertThat("There user does still exist ", not( user  == null)); 
	}
	
	@Test
	public void TEST_D_Subtest_A_checkIfWeCanCreateAContributorAccount(){
		
		try {
			
			userAccountsManager.addUser(testUsers);
			userAccountsManager.createAccount(testUsers, new CoordinatorAccount(testUsers, "pasWort"));
			  
			// there shall just be 1 account for the test user 
			
			List<Accounts> accs =  userAccountsManager.getAccountsByType(testUsers, AccountTypes.COORDINATOR);      
			
		    Accounts acc  =   accs.get(0);
			
			assertThat("The Account was not created", not(acc  != null));
			
			PathManager pathManager = new PathManager(ScheduleRootDiectory.toString(), 
					JSONFiles.toString(), Users.toString(),  "ACC_"	+ acc.getAccountId() + "");
		
			
		
			
	
		    assertTrue("the user directory does not exist", pathManager.doesPathExist());
		    
		   
		
		}
		catch(IndexOutOfBoundsException indexError) {
			
			assertTrue("There is the wrong ammoount of Accounts for the expected User  in the test repository", false);
			
		}		
	}
	

	/*@Test
	public void TEST_D_Subtest_B_checkIfWeCanDeleteAContributorAccount(){
		
		try {
			
			
			PathManager pathManager = new PathManager(ScheduleRootDiectory.toString(), 
					JSONFiles.toString(), Users.toString(),  	acc.getAccountId() + "");
			
			Accounts acc = ((List<Accounts>) accountsRepo.read(testUsers)).get(0);
			
			userAccountsManager.deleteAccount(acc);
			    
		    assertFalse("the user directory does still exist", pathManager.doesPathExist());
		    
			userAccountsManager.deleteUser(testUsers);
			
			List<Accounts> accs  =  accountsRepo.read(testUsers);
				  
			assertThat("There are still accounts for a deleted user ", not(accs == null));
		}
		catch(IndexOutOfBoundsException indexError) {
			
			assertTrue("There is the wrong ammoount of Accounts for the expected User  in the test repository", false);
			
		}		
	}*/
	 
}


