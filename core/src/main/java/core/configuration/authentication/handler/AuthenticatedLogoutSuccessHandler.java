package core.configuration.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import core.configuration.authentication.user.AuthorizedUserAccount;
import resources.database.entities.Accounts.Sessions;
import resources.database.repository.SessionsRepository;

public class AuthenticatedLogoutSuccessHandler  extends  MasterAuthenticationResultHandler  implements LogoutSuccessHandler{

	public AuthenticatedLogoutSuccessHandler(SessionsRepository sessionRepo) {
		super(sessionRepo); 
		
		landingUrl = "/login";
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		AuthorizedUserAccount account  =  (AuthorizedUserAccount)  authentication.getPrincipal();
		
		Sessions newSession = p_sessionRepo.read(account.getLoggedInAccountId());
		
		newSession.handleLogout();
		
		p_sessionRepo.unRegisterSession(newSession);
		
		handle(request, response);
		
        clearAuthenticationAttributes(request);
	}

}
