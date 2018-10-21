package core.configuration.authentication.utils;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import core.backend.NonREST.page.frontend.DashboardPageController;
import core.configuration.authentication.handler.AuthenticatedFailureHandler;
import core.configuration.authentication.handler.AuthenticatedLogoutSuccessHandler;
import core.configuration.authentication.handler.AuthenticatedSuccessHandler; 

@Configuration
@EnableWebSecurity
public class AuthorizationConfiguration extends WebSecurityConfigurerAdapter {
 
	@Autowired 
	@Qualifier("userAuthenticationProvider")
	private UserAuthenticationProvider m_userAuthenticationProvider;
	 
	@Autowired 
	private AuthenticatedSuccessHandler m_authenticatedSuccessHandler;
	
	@Autowired 
	private AuthenticatedFailureHandler m_authenticatedFailureHandler; 
	
	@Autowired 
	private  AuthenticatedLogoutSuccessHandler  m_authenticatedLogoutSuccessHandler;
	
	
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     
		auth.authenticationProvider(m_userAuthenticationProvider);
	}
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	        
		  // we have to define the paths, that do not need Authentication
			
		
		  // the paths, that describe the static resources 
		  String[] resources = new String[]{
				  "/vendors/**",  "/build/**","/js/**","/src/**", "/images/**"
	      };
		  
		  // the paths, that handle non authen, Backend Requests
		  String[] nonAuthenticationRequired = new String[] {
				 
				  "/passwordForgottenRecoverySuccessfull", "/passwordResetSuccessfull",
				  "/passwordForgotten",  "/Password/Forgotten", "/loginFailed", "/error", "/",  "/login", "/Password/Reset"
		  };
		  
		   
		  http
		      .csrf().disable(); //We don't need CSRF for this example
	        
		  http.authorizeRequests()
	          .antMatchers(resources).permitAll()
	          .antMatchers(nonAuthenticationRequired).permitAll()
	          .anyRequest().authenticated()
	          .and()
	          .formLogin()
	          .successHandler(m_authenticatedSuccessHandler)
	          .loginPage("/login")
	          .loginProcessingUrl("/perform_login")
	          .failureHandler(m_authenticatedFailureHandler)
	          .defaultSuccessUrl(DashboardPageController.dashboardPageURL)
	          .failureUrl("/loginFailed")
	            .usernameParameter("email")
				.passwordParameter("password");
	          
	          http.logout()
	          	.logoutUrl("/perform_logout")  
	          	.logoutSuccessHandler(m_authenticatedLogoutSuccessHandler)
	          	.invalidateHttpSession( true )
	            .deleteCookies("JSESSIONID"); 
		  
	    }

	   
}