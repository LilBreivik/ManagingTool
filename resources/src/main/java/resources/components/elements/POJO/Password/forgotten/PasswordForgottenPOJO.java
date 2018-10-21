package resources.components.elements.POJO.Password.forgotten;

import resources.database.entities.Accounts.Accounts.AccountTypes;

public class PasswordForgottenPOJO {

	private String userEmail;
	
	private AccountTypes associatedUserAccount;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public AccountTypes getAssociatedUserAccount() {
		return associatedUserAccount;
	}

	public void setAssociatedUserAccount(AccountTypes associatedUserAccount) {
		this.associatedUserAccount = associatedUserAccount;
	}
}
