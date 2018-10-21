package core.configuration.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EMailConfiguration {

	@Value("${spring.mail.host}")
	private String MAIL_HOST;
	  
	@Value("${spring.mail.port}")
    private String MAIL_PORT;
	  
	@Value("${spring.mail.username}")
	private String MAIL_USERNAME ;
	  
	@Value("${spring.mail.password}")
	private String MAIL_PASSWORD;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String MAIL_SMTP_AUTH;
	  
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String MAIL_SMTP_STARTTLS_ENABLE;
	  
	@Value("${spring.mail.smtp.socketFactory.class}")
	private String MAIL_SMTP_SOCKET_CLASS;

	@Value("${mail.properties.subject.text.passoword.change}")
	public String PASWORD_CHANGE_SUBJECT_TEXT;
	
	@Value("${mail.properties.forgotten.message}")
	public String PASWORD_FORGOTTEN_MESSAGE;
	
	@Value("${spring.server.url}")
	public String SERVER_URL;

	@Value("${mail.properties.reset.message}")
	public  String PASWORD_RESET_MESSAGE;
	
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(MAIL_HOST);
	    mailSender.setPort(Integer.parseInt(MAIL_PORT));
	     
	    mailSender.setUsername(MAIL_USERNAME);
	    mailSender.setPassword( MAIL_PASSWORD);
	     
	    Properties props = mailSender.getJavaMailProperties();
		
	     
		props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
		props.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
		
		props.put("mail.smtp.socketFactory.class",MAIL_SMTP_SOCKET_CLASS);
		
		props.put("mail.smtp.host", MAIL_HOST);
		props.put("mail.smtp.port", MAIL_PORT);
	    
	    
	    mailSender.setJavaMailProperties(props);
	    
	    
	    return mailSender;
	} 
}
