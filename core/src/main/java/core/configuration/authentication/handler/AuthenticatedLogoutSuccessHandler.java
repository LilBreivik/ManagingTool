package core.configuration.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component; 
import core.utils.redirect.RedirectionHandler; 
import resources.database.entities.Accounts.Sessions; 
import resources.database.repository.SessionsRepository;
import resources.utils.user.AuthorizedUserAccount;

@Component
public class AuthenticatedLogoutSuccessHandler  
									implements LogoutSuccessHandler {
 
	private final String landingUrl = "/login";
	  
	@Autowired 
	private SessionsRepository m_sessionRepo;
	 
	
	@Autowired
	private RedirectionHandler  m_RedirectHandler; 
		
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		  
		AuthorizedUserAccount authorizedAccount  =  (AuthorizedUserAccount)  authentication.getPrincipal();
		
		Sessions newSession = m_sessionRepo.createSession(authorizedAccount.getAccount()); 
		 
		m_sessionRepo.unRegisterSession(newSession);
		
		m_RedirectHandler.handleRedirection(request, response, landingUrl);
		 
	}

}
