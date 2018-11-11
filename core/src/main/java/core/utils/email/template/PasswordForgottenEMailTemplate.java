package core.utils.email.template;

  
import org.springframework.stereotype.Component;

import core.backend.REST.nonfileasset.password.reset.controller.PasswordResetController; 
import resources.database.entities.Accounts.ResetURLs;
 

@Component 
public class PasswordForgottenEMailTemplate 
												extends EMailTemplateImpl{
  

	private final String USERNAME_DELIMETER = "{NutzerName}"; 
	
	private final String RESETURLVALUE_DELIMETER = "{ResetURL}"; 
	
	private final String EXPIREDATEVALUE_DELIMETER = "{AblaufDatum}";
	
    private final String ADMINISTRATOR_DELIMETER = "{Administrator}";
	
	private final String EMAIL_RESET_URL_PART =  PasswordResetController.PasswordResetControllerURL + "?" + PasswordResetController.PasswordResetURL + "="; 
	
	 
	public void buildPasswordForgottenRecoveryMessage(String userName, 
														ResetURLs correspondingResetUrl) {
		 
	 
		p_SubjectText = p_emailConfiguration.PASWORD_CHANGE_SUBJECT_TEXT;
		
		StringBuilder emailResetURL = new StringBuilder();
		
		emailResetURL.append(p_emailConfiguration.SERVER_URL);
		emailResetURL.append(EMAIL_RESET_URL_PART);
		emailResetURL.append(correspondingResetUrl.getUrlvalue());
	 
		
		p_TemplateText = p_emailConfiguration.PASWORD_FORGOTTEN_MESSAGE
									.replace(USERNAME_DELIMETER, userName)
										.replace(EXPIREDATEVALUE_DELIMETER, correspondingResetUrl.getExpireAt().toString())
											.replace(RESETURLVALUE_DELIMETER, emailResetURL.toString())
												.replace(ADMINISTRATOR_DELIMETER, m_applicationInformationConfiguration.ADMINISTRATOR);
			 
		
	}
	
}
