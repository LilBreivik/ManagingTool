package core.configuration.authentication;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import core.configuration.authentication.handler.AuthenticatedFailureHandler;
import core.configuration.authentication.handler.AuthenticatedLogoutSuccessHandler;
import core.configuration.authentication.handler.AuthenticatedSuccessHandler;
import resources.database.repository.SessionsRepository; 

@Configuration
@EnableWebSecurity
public class AuthorizationConfiguration extends WebSecurityConfigurerAdapter {
 
	@Autowired 
	@Qualifier("userAuthenticationProvider")
	private UserAuthenticationProvider m_userAuthenticationProvider;
	 
	@Autowired
	private SessionsRepository m_sessionRepository; 
	
	@Bean
	public AuthenticatedSuccessHandler  initializeSuccessHandler(){
	
		return new AuthenticatedSuccessHandler (m_sessionRepository);
	    
	}
	 
	@Bean
	public AuthenticatedFailureHandler  initializeFailureHandler(){
	
		return new AuthenticatedFailureHandler (m_sessionRepository);
	}
	
	
	@Bean
	public AuthenticatedLogoutSuccessHandler  initializeLogoutSuccessHandler(){
	
		return new AuthenticatedLogoutSuccessHandler(m_sessionRepository);
	}
	
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     
		auth.authenticationProvider(m_userAuthenticationProvider);
	}
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	        
		  String[] resources = new String[]{
				  "/vendors/**", "/login" ,"/",   "/loginFailed",  "/build/**","/js/**","/src/**", "/images/**", "/error"
	        };
		   
		   
		  http
		      .csrf().disable(); //We don't need CSRF for this example
	        
		  http.authorizeRequests()
	          .antMatchers(resources).permitAll()
	          .anyRequest().authenticated()
	          .and()
	          .formLogin()
	          .successHandler(initializeSuccessHandler())
	          .loginPage("/login")
	          .loginProcessingUrl("/perform_login")
	          .failureHandler(initializeFailureHandler())
	          .defaultSuccessUrl("/ManagingTool")
	          .failureUrl("/loginFailed")
	            .usernameParameter("loginname")
				.passwordParameter("password");
	          
	          http.logout()
	          	.logoutUrl("/perform_logout")  
	          	.logoutSuccessHandler(initializeLogoutSuccessHandler())
	          	.invalidateHttpSession( true )
	            .deleteCookies("JSESSIONID"); 
		  
	    }

	   
}