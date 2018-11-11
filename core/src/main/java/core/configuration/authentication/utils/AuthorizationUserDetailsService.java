package core.configuration.authentication.utils;
 

import static com.google.common.collect.MoreCollectors.onlyElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.stereotype.Service;

import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;
import resources.database.repository.AccountsRepository;
import resources.database.repository.UsersRepository;
import resources.error.InternalError;
import resources.utils.user.AuthorizingUser;

@Service("userDetailsService")
public class AuthorizationUserDetailsService  implements UserDetailsService{

	@Autowired
	UsersRepository userRepo;
	
	@Autowired 
	AccountsRepository accountsRepo; 
	 
	 
	@Override
	public UserDetails loadUserByUsername(String userEmail) {
       
		try {
	
			Users loadedUser = userRepo.read().stream()
						.filter(user -> user.getUserEmail().equals(userEmail)).collect(onlyElement());
			
			 
			List<Accounts> accountsBelongToUser = accountsRepo.read().stream().filter(account -> account.getAccountOwners().equals(loadedUser))
					.collect(Collectors.toList());
			 
			return AuthorizingUser.createAuthorizingUser(loadedUser,  accountsBelongToUser);
		}
		
		catch(IllegalArgumentException | NoSuchElementException cannotAccessCertainUser) 
		{
			  throw new InternalError("Internal Server Error");
		}
 
    }

}
