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
import resources.database.entities.User.Users;
import resources.database.entities.factory.UserAccountsManager;
import resources.database.entities.factory.account.AdminAccount;
import resources.database.entities.factory.account.ContributorAccount;
import resources.database.entities.factory.account.CoordinatorAccount;
import resources.database.repository.UsersRepository;

@ContextConfiguration( classes={   DatabaseTestApplicationContext .class  })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)  
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DatabaseAccountsRepositoryTest {
 
	
	  private static Users applicationAdmin; 
	 
	  private static String applicationAdminContributorPassword = "contributor";
	  
	  private static String applicationAdminCoordinatorPassword = "coordinator";
	  
	  
	  
	  @Autowired 
	  private   UserAccountsManager userAccountManager; 
	  

	  @BeforeClass
	  public static void setUpUser() {
		  
		  
		  applicationAdmin =  UserAccountsManager.createUser("dustinbaer@gmx.de", "Dustin");
		  
	  }
	  

	  @Test
	  public void TEST_A_checkIfWeCanInitializeTheRepo() {
		   
		  assertTrue(userAccountManager != null);
	  } 
	  
	  @Test
	  public void TEST_B_checkIfWeCanAddAnContributorAccountToApplicationAdmin() {
		  	  
		  try {
			  
			  userAccountManager.createAccount(applicationAdmin,  new ContributorAccount(applicationAdmin ,  applicationAdminContributorPassword));
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
	  public void TEST_C_checkIfWeCanAddAnCoordinatorAccountToApplicationAdmin() {
		  	  
		  try {
			  
			  userAccountManager.createAccount(applicationAdmin,  new CoordinatorAccount(applicationAdmin ,  applicationAdminCoordinatorPassword));
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
