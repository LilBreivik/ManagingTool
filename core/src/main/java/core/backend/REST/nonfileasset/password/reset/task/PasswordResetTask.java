package core.backend.REST.nonfileasset.password.reset.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; 
import core.backend.REST.general.task.AbstractTaskImpl; 
import core.backend.REST.nonfileasset.password.reset.parameter.PasswordResetRequest; 
import core.utils.email.handler.EmailHandler; 
import core.utils.email.template.PasswordResetEMailTemplate;
import resources.database.entities.Accounts.Accounts; 
import resources.database.entities.factory.UserAccountsManager;
import resources.database.repository.ResetUrlsRepository;

@Component
public class PasswordResetTask 
                                extends  AbstractTaskImpl<PasswordResetRequest>{

	@Autowired 
	private UserAccountsManager m_UserAccountsManager; 
	
	@Autowired
	private ResetUrlsRepository m_ResetUrlsRepository;
	
	@Autowired 
	private EmailHandler m_EmailHandler; 
	
	@Autowired 
	private PasswordResetEMailTemplate m_PasswordResetEmail; 
	  
	 
	@Override
	public void workOnTask( PasswordResetRequest  param) {
		   
		final String newPasswordSource = UUID.randomUUID().toString().replace("-", "");
			
		final String newPassword = newPasswordSource.substring(0, Math.min(5, newPasswordSource.length() / 2));
		
		
		Accounts accountThatGetsUpadated = param.getVerifiedResetURL().getAccount();
		
		m_ResetUrlsRepository.delete(param.getVerifiedResetURL());
		
		accountThatGetsUpadated.createCredentials(newPassword);
		
		m_UserAccountsManager.updateAccount(accountThatGetsUpadated);
		 
		/**
		 * Prepare sending email 
		 * */
		
		final String recipientName =  accountThatGetsUpadated.getAccountOwners().getUserName();
		 
		final String recipientEMail =  accountThatGetsUpadated.getAccountOwners().getUserEmail();		
				 
		m_PasswordResetEmail.buildPasswordResetMessage ( recipientName, newPassword);
		
		/**
		 * Send Email
		 * */
		
		m_EmailHandler.sendMessage(recipientEMail, m_PasswordResetEmail);
		 
	}
}
