package core.configuration.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import core.configuration.authentication.user.AuthorizedUserAccount;
import resources.database.entities.Accounts.Sessions;
import resources.database.repository.SessionsRepository;

public class AuthenticatedFailureHandler extends  MasterAuthenticationResultHandler   implements  AuthenticationFailureHandler {

	
    public AuthenticatedFailureHandler(SessionsRepository sessionRepo) {
		super(sessionRepo); 
	}

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		 
		handle(request, response);
		
        clearAuthenticationAttributes(request);
	}

	 
}
