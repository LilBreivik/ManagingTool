package core.utils.email.template;
 
import org.springframework.stereotype.Component;

@Component
public class PasswordResetEMailTemplate 
									extends EMailTemplateImpl{

    private final String USERNAME_DELIMETER = "{NutzerName}"; 
 
    private final String NEWPASSWORD_DELIMETER = "{NeuesPasswort}"; 
  	   
    private final String ADMINISTRATOR_DELIMETER = "{Administrator}";
    
	public void buildPasswordResetMessage(String userName, 
												String  newPassword) {
 
		p_SubjectText = p_emailConfiguration.PASWORD_CHANGE_SUBJECT_TEXT;
		
		p_TemplateText = p_emailConfiguration.PASWORD_RESET_MESSAGE
												.replace(USERNAME_DELIMETER, userName)
													.replace(NEWPASSWORD_DELIMETER, newPassword)
														.replace(ADMINISTRATOR_DELIMETER, m_applicationInformationConfiguration.ADMINISTRATOR);
	}

}