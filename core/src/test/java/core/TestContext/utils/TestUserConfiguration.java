package core.TestContext.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import core.configuration.authentication.utils.UserAuthenticationProvider;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;

@Configuration
public class TestUserConfiguration {
	
	@Value("${test.user.email}")
	public String  TEST_USER_EMAIL; 
	
	@Value("${test.user.name}")
	public String  TEST_USER_NAME;
	
	@Value("${test.user.password}")
	public String TEST_USER_PASSWORD;
	
	@Autowired 
	@Qualifier("userAuthenticationProvider")
	private UserAuthenticationProvider m_userAuthenticationProvider;
	
	public UsernamePasswordAuthenticationToken createAuthentificationToken(Accounts account) {
		 
		UsernamePasswordAuthenticationToken auth_Token = m_userAuthenticationProvider.createAutheticationToken(TEST_USER_NAME, TEST_USER_PASSWORD, account);
	 	
		SecurityContextHolder.getContext().setAuthentication(auth_Token );
		
		return auth_Token;  
	}
}
