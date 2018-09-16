package core.Test.database;

import static org.junit.Assert.assertTrue;
 

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import core.TestContext.DatabaseTestApplicationContext;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager;
import resources.database.entities.factory.account.AdminAccount;
import resources.database.entities.factory.account.ContributorAccount; 

@ContextConfiguration( classes={   DatabaseTestApplicationContext .class  })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)  
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  DatabaseUserRepositoryTest {
	 
	  private static Users drachenlord;
		
	  private static Users admin; 
	
	  private static String drachenLordPasswordAdmin = "admin"; 
	 
	  private static String drachenLordPasswordContributor = "contributor"; 
	  
	  private static String accountPassword = "root";
	  
	  @Autowired 
	  private   UserAccountsManager userAccountManager; 
	  
 
	  @BeforeClass
	  public static void setUpUser() {
		  
		  drachenlord = UserAccountsManager.createUser("drachenlord1510@gmail.com", "Rainer");
		  
		  admin =  UserAccountsManager.createUser("meddl@gmx.de", "Dustin");
		  
	  }
	  
 
	  @Test
	  public void TEST_A_checkIfWeCanInitializeTheRepo() {
		   
		  assertTrue(userAccountManager != null);
	  } 
	  
	 
	  @Test
	  public void TEST_B_checkIfWeCanAddDrachenSchwesta() {
		  	  
		  try {
			   
			  userAccountManager.addUser(drachenlord);
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
	  public void TEST_C_checkIfWeCanAddAnAdminAcountToDrachenSchwesta() {
		  	  
		  try {
			  
			  userAccountManager.createAccount(drachenlord,  new AdminAccount(drachenlord ,  drachenLordPasswordAdmin));
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
	  public void TEST_D_checkIfWeCanAddAnContributorAcountToDrachenSchwesta() {
		  	  
		  try {
			  
			  
			  userAccountManager.createAccount(drachenlord,  new ContributorAccount(drachenlord ,  drachenLordPasswordContributor));
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
	  public void TEST_E_checkIfWeDeleteBothAccountsIfWeDeleteDrachenSchwesta() {
		  	  
		  try {
			  
			 
			  userAccountManager.deleteUser(drachenlord);
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
	  public void TEST_F_checkIfWeDCanCreateAnAdminAccount() {
		  	  
		  try {
			  
			  userAccountManager.addUser(admin);
			  
			  userAccountManager.createAccount(admin, new AdminAccount(admin, accountPassword));
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
	  public void TEST_G_checkIfWeDCanVerifyAnAccountPassword() {
		    assertTrue(userAccountManager.verifyAccount(admin, AccountTypes.ADMIN, accountPassword));		  
	  }
	  
	  @Test
	  public void TEST_H_changeAdminAccount() {
		  	  
		  try {
			
			  userAccountManager.changeEmailUserAccount( "meddl@gmx.de" , "dustinbaer@gmx.de");    
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