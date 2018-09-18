package core.configuration.authentication.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;

import resources.database.repository.SessionsRepository;

public abstract  class MasterAuthenticationResultHandler {

	protected  SessionsRepository m_sessionRepo; 
	
	protected String landingUrl; 
	
    protected RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    public MasterAuthenticationResultHandler(SessionsRepository sessionRepo) {
    	
    	m_sessionRepo = sessionRepo;
    	landingUrl = ""; 
    	redirectStrategy = new DefaultRedirectStrategy();
    }
    
    
    
    
    protected void handle(HttpServletRequest request, 
		      HttpServletResponse response)
		      throws IOException {
		  
		        redirectStrategy.sendRedirect(request, response, landingUrl);
   }
		 
	
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	            return;
	        }
	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	    }
}
