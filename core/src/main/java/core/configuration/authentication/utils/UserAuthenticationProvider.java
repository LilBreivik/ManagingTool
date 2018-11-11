package core.configuration.authentication.utils;


import static com.google.common.collect.MoreCollectors.onlyElement;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Sessions;
import resources.database.entities.User.Users;
import resources.database.repository.AccountsRepository;
import resources.database.repository.SessionsRepository;
import resources.database.repository.UsersRepository;
import resources.utils.general.GeneralPurpose;
import resources.utils.user.AuthorizedUserAccount;
import resources.utils.user.AuthorizingUser;
import resources.utils.user.DelimeteredPassword; 

@Service("userAuthenticationProvider")
public class UserAuthenticationProvider 
										implements AuthenticationProvider {

	@Autowired
	private SessionsRepository m_sessionRepository;  
	
	@Autowired 
	private AccountsRepository m_accountsRepository; 
	  
	@Autowired
	private UsersRepository m_usersRepository; 
	 
	
	public UsernamePasswordAuthenticationToken createAutheticationToken(String userName, String userPassword, 
									Accounts account ) {
		

		AuthorizedUserAccount authorizedAccount = new AuthorizedUserAccount(userName,
				userPassword, account);
		
		return new UsernamePasswordAuthenticationToken(authorizedAccount,   
									authorizedAccount.getPassword(),
							authorizedAccount.getAuthorities() );
		
	}
	
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	
		
		try {
		 	  // here email adress 
		 
			  final String m_userName = authentication.getName();
			  
		 	  final String m_userPassword = (String) authentication.getCredentials();
			  
		 	  Users userFromRepository  = 	 m_usersRepository.read().stream()
		 	  			.filter(user -> user.getUserEmail().equals(m_userName))
		 	  			   .collect(onlyElement());

				
		 	  List<Accounts> accountsBelongingToTheUser = m_accountsRepository.read(userFromRepository);
				
				
				/**
				* We cannot distinguish, 
				* between the login attempts to different accounts, 
				* so we will take any account with the same password, and email adress 
				* */
				
				
		 	  List<Accounts> verifiedAccountsToTheUser =  accountsBelongingToTheUser.stream()
									 					.filter(account -> Accounts.validateCredentials(m_userPassword, 
									 							account.getAccountPasswordHash()))
									 					.collect(Collectors.toList());
				

		 	  if(verifiedAccountsToTheUser.size() > 0) {
				
				
					/**
					* we will take the first verified account 
					* */
					
					
					final Accounts verifiedAccount =  verifiedAccountsToTheUser.get(0);
					
					Sessions newSession =  m_sessionRepository.createSession(verifiedAccount);
					
					m_sessionRepository.registerSession(newSession);
					 
					return createAutheticationToken(m_userName, m_userPassword, verifiedAccount);
					 
				}
				else {
				
				
					throw  new BadCredentialsException("Login Data invalid");
				}
					
		}		
		catch(NoSuchElementException userDoesNotExist) {
		
			throw  new BadCredentialsException("User Does Not Exist"); 		
		}
	}
	
	 
	@Override
	public boolean supports(Class<?> authentication) {
		
		 return authentication.equals(
		          UsernamePasswordAuthenticationToken.class);
	} 

}
