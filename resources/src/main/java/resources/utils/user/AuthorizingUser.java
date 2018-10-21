package resources.utils.user;

  
import java.util.List;
import java.util.stream.Collectors; 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;  
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;
import resources.utils.general.GeneralPurpose;


/*
 * Class that holdes the temporary 
 * values needed to verify an Account with a User 
 * and the givin Credentials
 * */

public class AuthorizingUser  
                          extends User  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3531439484732724601L;;
	
	private String m_userEmail; 
	
	private List<Integer> accountIds; 
	
	private   List<Accounts> m_accountsBelongToUser;
	
	public AuthorizingUser(String userName, 
								String userEmail, 
							    	 List<Accounts> accountsBelongToUser) {
	 
		
		super(userName,
					buildPassword(accountsBelongToUser),
						buildAuthorities(accountsBelongToUser));
		 
		
		
		
	    this.accountIds = buildAccountIds(accountsBelongToUser);
	    
	    this.m_accountsBelongToUser = accountsBelongToUser;
	    
		this.m_userEmail = userEmail;
	}
 

	public String getUserEmail() {
		return m_userEmail;
	}

	public List<Integer> getAccountIds() {
		return accountIds;
	}

	public List<Accounts> getAccountsBelongToUser() {
		return m_accountsBelongToUser;
	}
	
 
	private static List<? extends GrantedAuthority> buildAuthorities(List<Accounts> accountsBelongToUser) {
	
		return accountsBelongToUser.stream().map(account -> account.getAccountType()).collect(Collectors.toList());
	}
	
	private static List<Integer> buildAccountIds(List<Accounts> accountsBelongToUser) {
		
		return accountsBelongToUser.stream().map(account -> account.getAccountId()).collect(Collectors.toList());
	}
	
	
	private static String buildPassword(List<Accounts> accountsBelongToUser) {
		
		 return DelimeteredPassword.getDelimeteredPassword( GeneralPurpose.ListToArray(accountsBelongToUser.stream()
						.map(account -> account.getAccountPasswordHash()).collect(Collectors.toList())));
	}
	 
	
	public static AuthorizingUser createAuthorizingUser(Users loadedUser,  List<Accounts> accountsBelongToUser) {
		 
		
		return new AuthorizingUser(loadedUser.getUserName(), 
										loadedUser.getUserEmail(), 
											accountsBelongToUser);
	}
	 
}
