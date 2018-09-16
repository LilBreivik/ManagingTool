package core.configuration.authentication.user;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import core.configuration.authentication.utils.DelimeteredPassword;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;
import resources.utils.general.GeneralPurpose;


/*
 * Class that holdes the temporary 
 * values needed to verify an Account with a User 
 * and the givin Credentials
 * */

public class AuthorizingUser  extends User  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3531439484732724601L;;
	
	private String loginName; 
	
	private List<Integer> accountIds; 
	
	public AuthorizingUser(String userName, String loginName,  String buildedPassword,
											Collection<? extends GrantedAuthority> buildedAuthorities, List<Integer> buildedAccountIds) {
		super(userName, buildedPassword, buildedAuthorities);
		
	    setAccountIds(buildedAccountIds);
		setLoginName(loginName);
	}
 
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
		 
		return  new AuthorizingUser(loadedUser.getUserName(), 
										loadedUser.getLoginName(), 
												buildPassword(accountsBelongToUser), 
													buildAuthorities(accountsBelongToUser), 
														buildAccountIds(accountsBelongToUser));
	}
	 
	
	
	 

	public List<Integer> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<Integer> accountIds) {
		this.accountIds = accountIds;
	}
}
