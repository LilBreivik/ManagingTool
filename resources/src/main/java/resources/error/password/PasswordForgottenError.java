package resources.error.password;
 

public class PasswordForgottenError 

								extends PasswordResetError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3587441401868665728L;
 

	private static final String passwordForgottenErrorRedirectURL = "/passwordForgottenRecoverySuccessfull"; 
	 
	public  PasswordForgottenError() {
		super( passwordForgottenErrorRedirectURL);
	}
	   
}
