package core.configuration.authentication.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import core.backend.NonREST.page.frontend.DashboardPageController;
import core.utils.redirect.RedirectionHandler; 

@Component
public class AuthenticatedSuccessHandler
                                          implements AuthenticationSuccessHandler {
    	  
  
	@Autowired
	private RedirectionHandler  m_RedirectHandler;  
	 
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		 
		m_RedirectHandler.handleRedirection(request, response,  DashboardPageController.dashboardPageURL);
	}
	
}
