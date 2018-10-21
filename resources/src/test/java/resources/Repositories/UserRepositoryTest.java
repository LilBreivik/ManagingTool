package resources.Repositories;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
 
import java.util.List;
import java.util.stream.Collectors;

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

import resources.database.entities.User.Users;
import resources.database.repository.UsersRepository;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
 
public class UserRepositoryTest {

	private static Users testUsers; 
	
	@Autowired 
	private UsersRepository usersRepo;
	
	

	@BeforeClass
	public static void setUp() {
		
		 testUsers = new Users();
		 
		 testUsers.setUserEmail("drachenlord1510@gmail.com");
	  	 testUsers.setUserName("Rainer"); 
	}
 
	@Test
	public void TEST_A_Subtest_A_checkIfWeCanCreateANewUserToTheRepository(){
		
		usersRepo.create(testUsers);
		
		List<Users> expectedUsers = usersRepo.read().stream().filter( user -> user.getUserEmail().equals("drachenlord1510@gmail.com")).collect(Collectors.toList());
			
		// the testUser shall not exist before, but shall exist after updating the database 
			
		assertThat("The User Does not exist ", not(expectedUsers.size()  == 1));	
	}
	
	
	@Test
	public void TEST_A_Subtest_B_checkIfWeCanUpdateNewUsersToTheRepository(){
			
		final String expectedUserEmailToCreateFreshlyToTheRepository = "drachenlord1488@gmail.com";
		
		Users UserstateToUpdate = usersRepo.read(testUsers.getUserId());
		
		UserstateToUpdate.setUserEmail(expectedUserEmailToCreateFreshlyToTheRepository); // different  email
		UserstateToUpdate.setUserName("Reiner"); // different user name
				  
		usersRepo.update(UserstateToUpdate);
		
		Users readUpdatedUsers = usersRepo.read( testUsers.getUserId());
		
		assertThat("The Deleted State does match after Update ", not(readUpdatedUsers .getUserEmail().equals(UserstateToUpdate.getUserEmail() )));
		 
	} 
	
		
	@Test
	public void TEST_A_Subtest_C_checkIfWeCanDeleteUsersViaAMatchingObjectToTheRepository(){
		
		// first we need to access the expected file 
		
		Users readExpectedUser = usersRepo.read( testUsers.getUserId());
		  
		usersRepo.delete(readExpectedUser);
		
		Users readUpdatedUser = usersRepo.read(testUsers.getUserId() );
		
		assertThat("The expected File does exist ", not(readUpdatedUser == null));
	}
	
	
	@Test
	public void TEST_A_Subtest_D_checkIfWeCanDeleteUsersViaAMatchingObjectToTheRepository(){
		
		final String expectedUserEmailToCreateFreshlyToTheRepository = "drachenlord1510@gmail.com"; 
		
		Users userToBeCreated = new Users();
			 	 
		
		userToBeCreated.setUserEmail(expectedUserEmailToCreateFreshlyToTheRepository);
		
		userToBeCreated.setUserName("Rainer");
		
		usersRepo.create(userToBeCreated);
		
		usersRepo.delete(userToBeCreated);
		
		Users readUpdatedUser = usersRepo.read( userToBeCreated.getUserId() );
		
		assertThat("The expected File does exist ", not(readUpdatedUser == null));
	}
	
}
