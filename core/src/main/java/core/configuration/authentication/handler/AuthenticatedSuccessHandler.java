package core.configuration.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import core.configuration.authentication.user.AuthorizedUserAccount;
import resources.database.entities.Accounts.Sessions;
import resources.database.repository.SessionsRepository;

public class AuthenticatedSuccessHandler extends  MasterAuthenticationResultHandler  implements AuthenticationSuccessHandler {
   
	SessionsRepository sessionRepo;
	
    public AuthenticatedSuccessHandler(SessionsRepository sessionRepo) {
		 super(sessionRepo);
		 
		 landingUrl = "/ManagingTool"; 
	}

    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		  
		handle(request, response);

        clearAuthenticationAttributes(request);
	}
	
}
