package core.TestContext.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder; 
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import core.configuration.authentication.utils.AuthorizationUserDetailsService; 
import resources.utils.user.AuthorizedUserAccount;
import resources.utils.user.AuthorizingUser;


/**
 * Factory that shall help us to create 
 * the  UsernamePasswordAuthenticationToken 
 * needed for Unit Tests 
 * */


public class UserSecurityContextFactory 
						implements WithSecurityContextFactory<WithMockUser> {

	
	@Autowired 
	@Qualifier("userDetailsService")
	private AuthorizationUserDetailsService  m_authorizationUserDetailsService; 
	
	 
	protected String p_userName; 
	
	protected String p_userEmail; 
	
	protected String p_userPassword; 
	
	
	@Override
	public SecurityContext createSecurityContext(WithMockUser annotation) {
		
		AuthorizingUser authorizedUser =  (AuthorizingUser) m_authorizationUserDetailsService.loadUserByUsername(p_userEmail);
        
		

		/**
		* we will take the first verified account 
		* */
		
		 
		AuthorizedUserAccount authorizedAccount = new AuthorizedUserAccount(authorizedUser.getUsername(),
				authorizedUser.getPassword(), authorizedUser.getAccountsBelongToUser().get(0));
		 
		Authentication authentication =  new UsernamePasswordAuthenticationToken(authorizedAccount,   
				authorizedAccount.getPassword(), authorizedAccount.getAuthorities() ); 
		
		
		
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		
		context.setAuthentication(authentication);
		 
		return context;
	}
	
	
	
}
