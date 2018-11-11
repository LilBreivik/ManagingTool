package core.utils.email.template;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import core.configuration.applicationinformation.ApplicationInformationConfiguration;
import core.configuration.email.EMailConfiguration;


@Configuration
public  class EMailTemplateImpl 
								implements IEmailTemplate{

	protected String p_SubjectText; 
	
	protected String p_TemplateText;

	 
	@Autowired
	protected  EMailConfiguration p_emailConfiguration; 
	
	@Autowired
	protected  ApplicationInformationConfiguration m_applicationInformationConfiguration; 
	 
	 
	@Override
	public String getTemplateText() {
	
		return p_TemplateText;
	}

	@Override
	public String getSubject() {
	
		return p_SubjectText;
	}

	
}
