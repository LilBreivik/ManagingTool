package core.utils.email.template;

 
import core.configuration.email.EMailConfiguration; 
import core.backend.REST.nonfileasset.password.reset.controller.PasswordResetController; 
import resources.database.entities.Accounts.ResetURLs;
 
 
public class PasswordForgottenEMailTemplate 
												extends EMailTemplateImpl{
  
	
	public PasswordForgottenEMailTemplate(EMailConfiguration eMailConfiguration) {
		super(eMailConfiguration); 
		
		p_SubjectText = p_EMailConfiguration.PASWORD_CHANGE_SUBJECT_TEXT;
	}

  

	private final String USERNAME_DELIMETER = "{NutzerName}"; 
	
	private final String RESETURLVALUE_DELIMETER = "{ResetURL}"; 
	
	private final String EXPIREDATEVALUE_DELIMETER = "{AblaufDatum}";
	
	private final String EMAIL_RESET_URL_PART =  PasswordResetController.PasswordResetControllerURL + "?" + PasswordResetController.PasswordResetURL + "="; 
	
	 
	public void buildPasswordForgottenRecoveryMessage(String userName, 
														ResetURLs correspondingResetUrl) {
		 
		StringBuilder emailResetURL = new StringBuilder();
		
		emailResetURL.append(p_EMailConfiguration.SERVER_URL);
		emailResetURL.append(EMAIL_RESET_URL_PART);
		emailResetURL.append(correspondingResetUrl.getUrlvalue());
	 
		
		p_TemplateText = p_EMailConfiguration.PASWORD_FORGOTTEN_MESSAGE
									.replace(USERNAME_DELIMETER, userName)
										.replace(EXPIREDATEVALUE_DELIMETER, correspondingResetUrl.getExpireAt().toString())
											.replace(RESETURLVALUE_DELIMETER, emailResetURL.toString());
			 
		
	}
	
}
