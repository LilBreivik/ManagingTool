package core.Test.database.Test;
     
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import core.Test.database.TestContext.DatabaseTestApplicationContext;
import resources.components.filehandler.PathManager;
import resources.configuration.PathManagerProvider;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager;
import resources.database.entities.factory.account.AdminAccount; 
import resources.database.entities.factory.account.CoordinatorAccount;
import resources.database.repository.AccountsRepository;
import resources.database.repository.UsersRepository;
import resources.error.InternalError; 
  
   
@ContextConfiguration( classes={ DatabaseTestApplicationContext .class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)   
public class  DatabaseUserRepositoryTest {
 	  
	  @Autowired 
	  private UsersRepository usersRepo;
   
	  @Autowired 
	  private  AccountsRepository accountsRepo; 

	  @Autowired 
	  private UserAccountsManager userAccountManager; 
	  
	  private static final String testUser1Name = "Ramona Winkler"; 
	  
	  private static final String testUser1Email = "ramona1510@gmail.com"; 
	   
	  private static final String testUser2Name = "Herr Mueller"; 
	  
	  private static final String testUser2Email = "mueller1510@gmail.com"; 
	  
	  private static final String adminAccountPw = "root"; 
	  
	  private static final String coordinatorAccountPw = "coordinatorAccountPw";
	  
	  private static final String testUser3Email = "mueller@polizeimittelfranken.de";  
	  
	  
	  @Test
	  public void TEST_A_checkIfWeCanInitializeTheRepo() {
		   
		  assertTrue(usersRepo != null);
	  } 
	  
	  @Test
	  public void TEST_B_checkIfWeCanAddNewUsers() {
		  	  
		  try {
		
			  
			  Users user = userAccountManager.createUser(testUser1Email, testUser1Name);
			  
			  usersRepo.create(user);
			  
		  }
		  
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  assertTrue(true);
			  }
			  
			  else { 
				  
				  assertTrue(false);
			  }
		  }
		 		  
	  }
	 
	  @Test
	  public void TEST_C_checkIfWeCanPreventDoubleValues() {
		  
		  
		  try {
			  
			  Users user = userAccountManager.createUser(testUser1Email, testUser1Name);
			  
			  usersRepo.create(user);
			  
			  assertTrue(false); 
			  
		  }
		  
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  assertTrue(true);
			  }
			  
			  else {
				  
				  assertTrue(false);
			  }
		  }
	  }
	   
	  @Test
	  public void TEST_D_checkIfWeCanREADFromNewUsers() {
		  
		  Users user = userAccountManager.createUser(testUser2Email, testUser2Name);
		   
		  usersRepo.create(user);
		  
		  
          List<Users> usersList = usersRepo.read();
		  
		  usersList.forEach(userInList -> System.out.println(userInList.getUserName()));
		  
		  assertTrue(usersList.size() == 2);
		 
	  }
	  
	  @Test
	  public void TEST_E_checkIfWeCanDeleteTestUser1() {
			
		  try {
			  
			  Users user = userAccountManager.createUser(testUser1Email, testUser1Name);
			  
			  userAccountManager.deleteUser(user); 
			  
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 		
	}
	   
	  @Test
	  public void TEST_F_checkIfWeCanGiveTestUser2_An_Admin_Account() {
			
		  try {
			   
			  Users user = usersRepo.read(testUser2Email);
			   
			  userAccountManager.createAccount(user, new AdminAccount(user, adminAccountPw));
			 
			  Accounts acc = accountsRepo.read(user).get(0);
			  
			  assertThat("account does not beong to the expected user", acc.getAccountOwners().equals(user), is(true));
			  
			 
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 		
	}
 
	  @Test
	  public void TEST_G_checkIfWeCanVerifyAnAccount() {
			
		  try {
			  
			  
			  Users user = userAccountManager.createUser(testUser2Email, testUser2Name);
			   
			  assertThat("the password cannot be verified", 
					  		userAccountManager.verifyAccount(user, AccountTypes.ADMIN , adminAccountPw), 
					  			is(true));
			  
			 
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 		
	}
	  
	  
	  
	  @Test
	  public void TEST_H_checkIfWeCanChnageTheUSEREMAIL() {
			
		  try {
			    
			   
			  userAccountManager.changeEmailUserAccount(testUser2Email, testUser3Email);
			 
			  Users user = usersRepo.read(testUser3Email);
			  
			  Accounts acc = accountsRepo.read(user).get(0);
			  
			  assertThat("account does not beong to the expected user", acc.getAccountOwners().equals(user), is(true));
			  
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 		
	}
 
 
	  @Test
	  public void TEST_I_checkIfWeChangeAdminAccountToCoordinator_Account_For_TestUser2() {
			
		  try {
			  
			  Users user = usersRepo.read(testUser3Email);
			   
			  userAccountManager.updateAccount(user, new CoordinatorAccount(user, coordinatorAccountPw));
			   	 
			  Accounts acc = accountsRepo.read(user).get(0);
			  
			  assertThat("account does not beong to the expected user", acc.getAccountOwners().equals(user), is(true));
			 
			  PathManager UserDirectoryPathManager = PathManagerProvider.configurePathManagerToNoticesForUserCreation(user);
			  
			  assertThat("user direcory does not exist", UserDirectoryPathManager.getPathToOperateOn().toFile().exists(), is(true));
		  
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 		
	} 
	  
	  
	  
	  @Test
	  public void TEST_J_checkIfWeCanDeleteContributorAccountOfTestUser2() {
			
		  try {
			   
			  
			  Users user = usersRepo.read(testUser3Email);
			   
			  userAccountManager.deleteAccount(user, AccountTypes.COORDINATOR);
			   
				 
			  List<Accounts> acc = accountsRepo.read(user);
			  
			  assertThat("there is still a coordinator account that shall be deleted", acc.size(), is(0));
			 
			  PathManager UserDirectoryPathManager = PathManagerProvider.configurePathManagerToNoticesForUserCreation(user);
			 
			  assertThat("user direcory does still exist ", UserDirectoryPathManager.getPathToOperateOn().getFileName().toFile().exists(), is(false));
		  
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 		
	} 
	  
	  
	  @Test
	  public void TEST_K_checkIfWeCanGiveTestUser2_An_Admin_Coordinator() {
			
		  try {
			   
			  
			  Users user = usersRepo.read(testUser3Email);
			   
			  userAccountManager.createAccount(user, new CoordinatorAccount(user,  coordinatorAccountPw ));
			 
			  Accounts acc = accountsRepo.read(user).get(0);
			  
			  assertThat("account does not beong to the expected user", acc.getAccountOwners().equals(user), is(true));
			  
			  PathManager UserDirectoryPathManager = PathManagerProvider.configurePathManagerToNoticesForUserCreation(user);
			  
			  System.out.println(UserDirectoryPathManager.getPathToOperateOn().getFileName().toFile().toString());
			  
			  
			  System.out.println( UserDirectoryPathManager.getPathToOperateOn().toFile().exists());
			  
			  assertThat("user direcory does not exist",  UserDirectoryPathManager.getPathToOperateOn().toFile().exists(), is(true));
		  
			  
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 		
	}
	  
	  
	  @Test
	  public void TEST_L_checkIfWeDeleteTestUser2() {
			
		  Users user =  null;
		  
		  try {
			   
			  
			  user = usersRepo.read(testUser3Email);
			   
			  usersRepo.delete(user.getUserEmail()); 
			  
			  assertThat("User does still exist", usersRepo.read(testUser3Email) == null, is(true));
			   
			  PathManager UserDirectoryPathManager = PathManagerProvider.configurePathManagerToNoticesForUserCreation(user);
					  
			  System.out.println(UserDirectoryPathManager.getPathToOperateOn().getFileName().toFile().toString());
			  
			  System.out.println(UserDirectoryPathManager.getPathToOperateOn().toFile().exists());
			
			  
			  // we have to think about a way to remove user directoires , if a user was removed , before 
			  
			  assertThat("user direcory does still exist ", UserDirectoryPathManager.getPathToOperateOn().toFile().exists(), is(false));
				  
			  
		  }
		  catch(Exception databaseException) {
			   
			
			 
				  throw new InternalError("Internal Server Error");
			 
		  } 		
	}  
	  
	  
	  @Test
	  public void TEST_M_checkIfWeCanAddAnAdminAccount() {
		  	  
		  try {
		
			  
			  Users user = userAccountManager.createUser("dustinbaer@gmx.de", "dustin");
			  
			  usersRepo.create(user);
			  
			  userAccountManager.createAccount(user, new AdminAccount(user, "root"));
			  
		  }
		  
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  assertTrue(true);
			  }
			  
			  else { 
				  
				  assertTrue(false);
			  }
		  }
		 		  
	  }
	  
}
