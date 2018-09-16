package core.configuration.authentication.user;
 
import java.util.List; 
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service;

import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;
import resources.database.repository.AccountsRepository;
import resources.database.repository.UsersRepository;
import resources.error.InternalError;

@Service("userDetailsService")
public class AuthorizationUserDetailsService  implements UserDetailsService{

	@Autowired
	UsersRepository userRepo;
	
	@Autowired 
	AccountsRepository accountsRepo; 
	
	@Override
    public UserDetails loadUserByUsername(String username) {
      
		try {
			 
			Users loadedUser = userRepo.read().stream()
						 					.filter(user -> user.getLoginName().equals(username)).collect(Collectors.toList()).get(0);
				 
			List<Accounts> accountsBelongToUser = accountsRepo.read().stream().filter(account -> account.getAccountOwners().equals(loadedUser))
					.collect(Collectors.toList());
			
			return AuthorizingUser.createAuthorizingUser(loadedUser,  accountsBelongToUser);
		}
		catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Internal Server Error");
			  }
			  
			  else {
				  
				  throw new UsernameNotFoundException(username);
				  
			  }
		  } 
		
		 
        
    }

}
