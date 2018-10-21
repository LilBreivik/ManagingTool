package core.backend.NonREST.model.views;
 
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import core.backend.NonREST.model.IndexPageView;
import core.configuration.applicationinformation.ApplicationInformationConfiguration;
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.pathmanager.PathManager; 

@Component
public class ApplicationInformationView 

											extends IndexPageView{
 
	@Autowired 
	private ApplicationInformationConfiguration applicatonInformation; 
	
	@Autowired 
	@Qualifier("PathManager to ChangeLog")
	private PathManager m_pathManagerToChangeLog; 
	
	
	private final String TITLE = "Managing-Tool Information"; 
	
	private final String VERSION_ATTRIBUTE = "VERSION"; 
	
	private final String ADMINISTRATOR_ATTRIBUTE = "ADMINISTRATOR"; 
	
	private final String ADMINISTRATOREMAIL_ATTRIBUTE = "ADMINISTRATOREMAIL";
	
	private final String AUTHOR_ATTRIBUTE =  "AUTHOR"; 
	
	private final String MESSAGE_TO_AUTHOR_ATTRIBUTE = "MESSAGETOAUTHOR"; 
	
	private final String MESSAGE_TO_ADMINISTRATOR_ATTRIBUTE = "MESSAGETOADMINISTARTOR"; 
	
	private final String MESSAGE_OF_CHANGELOG = "CHANGELOGMESSAGE";
	
	private final String  AUTHOREMAIL_ATTRIBUTE = "AUTHOREMAIL"; 
	
	private final String emailToAdministrator = "mailto:" + ADMINISTRATOREMAIL_ATTRIBUTE + "?Subject=" + "Nachricht an den Administrator";
	
	private final String emailToTheAuthor = "mailto:" + AUTHOREMAIL_ATTRIBUTE + "?Subject=" + "Nachricht an den Autor";
	
	
	public ApplicationInformationView() {
		

		HEADLINE_DISPLAY = true; 
		HEADLINE = "Einstellungen";
		
		CONTENT_DISPLAY = true; 
		TEMPLATE_REPLACEMENT_EXPRESSION ="fragments/applicationinformation"; 
		
		FOOTER_DISPLAY = false;
	}
	

	@Override
	public Model buildRequiredView(Model model) {
		
		model.addAttribute(TITLE_ATTRIBUTE, 
				TITLE); 
		 
		model.addAttribute(	VERSION_ATTRIBUTE, 
				applicatonInformation.VERSION);
		 
		
		model.addAttribute(ADMINISTRATOR_ATTRIBUTE, 
				applicatonInformation.ADMINISTRATOR);
		
		

		model.addAttribute(	ADMINISTRATOREMAIL_ATTRIBUTE, 
				applicatonInformation.ADMINISTRATOR_EMAIL);
		 
		
		model.addAttribute(MESSAGE_TO_ADMINISTRATOR_ATTRIBUTE, 


				emailToAdministrator.replace(ADMINISTRATOREMAIL_ATTRIBUTE, applicatonInformation.ADMINISTRATOR_EMAIL)
										);
		  
		model.addAttribute(AUTHOR_ATTRIBUTE, 
				applicatonInformation.AUTHOR); 
		
		  
		model.addAttribute(	AUTHOREMAIL_ATTRIBUTE, 
				applicatonInformation.AUTHOR_EMAIL);
		
	 
		model.addAttribute(MESSAGE_TO_AUTHOR_ATTRIBUTE, 


				emailToTheAuthor.replace(AUTHOREMAIL_ATTRIBUTE, applicatonInformation.AUTHOR_EMAIL));
			 
		
		model.addAttribute(MESSAGE_OF_CHANGELOG,
				(new String(OrdinaryFileHandler	.readFile(m_pathManagerToChangeLog.getPathOfFile("changelog.log").toFile()), 
						Charset.forName("UTF-8"))));
		 
		model.addAttribute(HEADLINE_DISPLAY_ATTRIBUTE, HEADLINE_DISPLAY);
		model.addAttribute(HEADLINE_ATTRIBUTE, HEADLINE);
		
		model.addAttribute(CONTENT_DISPLAY_ATTRIBUTE, CONTENT_DISPLAY);
		model.addAttribute(CONTENT_ATTRIBUTE , TEMPLATE_REPLACEMENT_EXPRESSION);
		
		model.addAttribute(FOOTER_DISPLAY_ATTRIBUTE, FOOTER_DISPLAY);
		
		return model;
	}
}

 