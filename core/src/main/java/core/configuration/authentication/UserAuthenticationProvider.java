package core.configuration.authentication;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

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

import core.configuration.authentication.user.AuthorizationUserDetailsService;
import core.configuration.authentication.user.AuthorizedUserAccount;
import core.configuration.authentication.user.AuthorizingUser; 
import core.configuration.authentication.utils.DelimeteredPassword;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Sessions;
import resources.database.repository.SessionsRepository;
import resources.utils.general.GeneralPurpose; 

@Service("userAuthenticationProvider")
public class UserAuthenticationProvider  implements AuthenticationProvider {

	private String m_userName; 
	
	private String m_userPassword; 
	
	private List<String> m_accountPasswords; 

	@Autowired 
	@Qualifier("userDetailsService")
	private AuthorizationUserDetailsService  m_authorizationUserDetailsService; 
	
	@Autowired
	private SessionsRepository m_sessionRepository;  
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	 try {
			  m_userName = authentication.getName();
			  
		 	  m_userPassword = (String) authentication.getCredentials();
			   
			  AuthorizingUser authorizingUser =  (AuthorizingUser)  m_authorizationUserDetailsService.loadUserByUsername(m_userName);
			    
		      String delimeterdPasswordsForAccounts = authorizingUser.getPassword();
		      
		        
			  // keep in mind to remove delimeter 
			  m_accountPasswords = DelimeteredPassword.translatedDelimeteredPassword(delimeterdPasswordsForAccounts);
			 
			  for(String accountPassword : m_accountPasswords) {
				  
				  if(Accounts.validateCredentials(m_userPassword, accountPassword.getBytes(StandardCharsets.UTF_8))) {
					   
					  GrantedAuthority authorityOfAccount =   GeneralPurpose.CollectionToList(authorizingUser.getAuthorities()).get(m_accountPasswords.indexOf(accountPassword));
					  
					  int authorizedAccountId = GeneralPurpose.CollectionToList(authorizingUser.getAccountIds()).get(m_accountPasswords.indexOf(accountPassword));
				 	    
					  AuthorizedUserAccount authorizedAccount = new AuthorizedUserAccount(authorizingUser.getUsername(), accountPassword, authorizingUser.getLoginName(), authorityOfAccount, authorizedAccountId);
					   
					  Sessions newSession = new Sessions(authorizedAccountId);
						
					  newSession.handleLogin();
						
					  m_sessionRepository.saveOrUpdate(newSession);
					  
					  
					  return new UsernamePasswordAuthenticationToken(authorizedAccount,   m_userPassword, Arrays.asList( authorityOfAccount) );
				   }
			  } 
			  
			  throw  new BadCredentialsException("Login Data invalid");
		}
		catch(UsernameNotFoundException userNameDoesNotExist) {
			
			throw  new BadCredentialsException("User Does Not Exist");
		} 
	}
	
	 
	@Override
	public boolean supports(Class<?> authentication) {
		
		 return authentication.equals(
		          UsernamePasswordAuthenticationToken.class);
	} 

}
