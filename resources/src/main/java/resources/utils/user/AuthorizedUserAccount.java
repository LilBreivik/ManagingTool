package resources.utils.user;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import resources.database.entities.Accounts.Accounts;

public class AuthorizedUserAccount 
									extends User{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	private Accounts m_account;  
	
	public AuthorizedUserAccount(String username, String password,  
		  Accounts account	, GrantedAuthority authorities) {
		super(username, password, Arrays.asList(authorities) );
	 
		this.m_account =  account;
	}
  

	public Accounts getAccount() {
		return m_account;
	}

	public int getLoggedInAccountId() {
		 
		return m_account.getAccountId();
	}
 
}