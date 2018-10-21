package core.backend.REST.nonfileasset.password.forgotten.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;   
import core.backend.REST.general.task.nonresponse.NonResponseAbstractTaskImpl;
import core.backend.REST.nonfileasset.password.forgotten.parameter.PasswordForgottenRequest;
import core.configuration.email.EMailConfiguration;
import core.utils.email.handler.EmailHandler;
import core.utils.email.template.PasswordForgottenEMailTemplate;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.ResetURLs;
import resources.database.repository.ResetUrlsRepository;

@Component
public class PasswordForgottenTask  
							extends NonResponseAbstractTaskImpl<PasswordForgottenRequest >{

	@Autowired
	private ResetUrlsRepository m_ResetUrlsRepository; 

	@Autowired 
	private EmailHandler m_EmailHandler; 
	
	@Autowired 
	private EMailConfiguration m_EMailConfiguration;
	
	 
    @Override
	public void workOnTask(PasswordForgottenRequest  param) {
		  
		// at first we try to set 
		// the needed URL, where a certain user shall 
		// reset its password 
		
		Accounts accountThatShallBeChanged =  (Accounts) param.getVerifiedParameters().get(PasswordForgottenRequest.USER_ACCOUNT_PARAMETER);
		
		m_ResetUrlsRepository.initializeResetURL(accountThatShallBeChanged);
		
		final ResetURLs correspondingResetUrl = m_ResetUrlsRepository.read(accountThatShallBeChanged);

		/**
		 * Prepare sending email 
		 * */
		
		final String recipientEMail =  accountThatShallBeChanged.getAccountOwners().getUserEmail();
		
		final String recipientName = accountThatShallBeChanged.getAccountOwners().getUserName();
		
		final PasswordForgottenEMailTemplate passwordForgottenEmail = new PasswordForgottenEMailTemplate( m_EMailConfiguration);
		
		 
		passwordForgottenEmail.buildPasswordForgottenRecoveryMessage( recipientName, correspondingResetUrl);
		
		/**
		 * Send Email
		 * */
		
		m_EmailHandler.sendMessage(recipientEMail, passwordForgottenEmail);
	}
 
}
