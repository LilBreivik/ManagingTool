package core.utils.email.template;
 

import core.configuration.email.EMailConfiguration;

public abstract class EMailTemplateImpl 
								implements IEmailTemplate{

	
	protected String p_SubjectText; 
	
	protected String p_TemplateText;

	protected  EMailConfiguration p_EMailConfiguration;

	
	protected EMailTemplateImpl(EMailConfiguration eMailConfiguration) {
		
		p_EMailConfiguration = eMailConfiguration;
	}
	
	 
	@Override
	public String getTemplateText() {
	
		return p_TemplateText;
	}

	@Override
	public String getSubject() {
	
		return p_SubjectText;
	}

	
}
