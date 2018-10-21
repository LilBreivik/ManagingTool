package core.configuration.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import core.utils.redirect.RedirectionHandler; 


@Component
public class AuthenticatedFailureHandler 
						implements  AuthenticationFailureHandler {

	

	@Autowired
	private RedirectionHandler  m_RedirectHandler; 
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		  
		 
			m_RedirectHandler.handleRedirection(request, response);
	}

	 
}
