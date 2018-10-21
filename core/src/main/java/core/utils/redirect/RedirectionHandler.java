package core.utils.redirect;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;

import resources.error.InternalError;

@Component
public class RedirectionHandler 
								extends DefaultRedirectStrategy{

	
	public void handleRedirection(HttpServletRequest request, 
										      HttpServletResponse response, 
										      	String landingUrl )  {
		
		try {
			
			sendRedirect(request, response, landingUrl);
			
			clearAuthenticationAttributes(request);
		} catch (IOException e) {
			
			throw new InternalError("Cannot redirect to " + landingUrl);
		}
		 
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
	     
	    	HttpSession session = request.getSession(false);
		    
	    	if (session != null) {
	    		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		    }
	}

	public void handleRedirection(HttpServletRequest request, HttpServletResponse response) {
		
       try {
			
			sendRedirect(request, response, "");
			
			clearAuthenticationAttributes(request);
		} catch (IOException e) {
			
			throw new InternalError("Cannot redirect to base URL");
		}
		 
	}
 
}
