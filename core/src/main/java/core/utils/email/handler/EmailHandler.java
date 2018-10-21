package core.utils.email.handler;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import core.utils.email.template.EMailTemplateImpl;

@Component
public class EmailHandler{

	
	@Autowired
    private  JavaMailSender emailSender;

	
	
	public void sendMessage(String recipientEMail, 
											EMailTemplateImpl template) {
		
		
		try {
			
			SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo(recipientEMail); 
	        message.setSubject(template.getSubject()); 
	        message.setText(template.getTemplateText());
	        emailSender.send(message);
		}
		catch( MailSendException  sendEmailFailed) {
			
			sendEmailFailed.printStackTrace();
			// @FIXME: log if the server cannot send message
		}
		
		catch(MailParseException cannotProcessEMail) {
			
			cannotProcessEMail.printStackTrace();
		}
		
		catch( MailAuthenticationException cannotAuthorizeToSMTPServer ) {
			
			cannotAuthorizeToSMTPServer.printStackTrace();	
		}
		
		
		catch( MailException cannotAuthorizeToSMTPServer ) {
			
			cannotAuthorizeToSMTPServer.printStackTrace();
		}
		
	
	}
	
	
}
