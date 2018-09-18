package core.configuration.authentication.user;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthorizedUserAccount extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginName; 
	
	private int loggedInAccountId; 
	
	
	public AuthorizedUserAccount(String username, String password, 
			String loginName,  GrantedAuthority authorities, int authorizedAccountId) {
		super(username, password, Arrays.asList(authorities) );
		
		setLoggedInAccountId(authorizedAccountId);
		setLoginName(loginName);
		
	}
		 
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getLoggedInAccountId() {
		return loggedInAccountId;
	}

	public void setLoggedInAccountId(int loggedInAccountId) {
		this.loggedInAccountId = loggedInAccountId;
	}

		
}