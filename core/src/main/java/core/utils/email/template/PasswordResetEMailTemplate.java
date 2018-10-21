package core.utils.email.template;
 
import core.configuration.email.EMailConfiguration; 

public class PasswordResetEMailTemplate 
									extends EMailTemplateImpl{

    private final String USERNAME_DELIMETER = "{NutzerName}"; 
 
    private final String NEWPASSWORD_DELIMETER = "{NeuesPasswort}"; 
  	
	public PasswordResetEMailTemplate (EMailConfiguration eMailConfiguration) {
	
		super(eMailConfiguration); 
		
		p_SubjectText = p_EMailConfiguration.PASWORD_CHANGE_SUBJECT_TEXT;
	}



   
	public void buildPasswordResetMessage(String userName, 
														String  newPassword) {
 
		
		p_TemplateText = p_EMailConfiguration.PASWORD_RESET_MESSAGE
												.replace(USERNAME_DELIMETER, userName)
													.replace(NEWPASSWORD_DELIMETER, newPassword);
	
	
	}

}