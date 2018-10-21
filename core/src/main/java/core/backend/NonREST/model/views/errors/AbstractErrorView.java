package core.backend.NonREST.model.views.errors;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import core.backend.NonREST.model.IndexPageView; 
import core.configuration.applicationinformation.ApplicationInformationConfiguration;
 
@Component
public abstract class AbstractErrorView

									extends  IndexPageView {
	
	protected final String ERROREMAIL =  "ERRORMESSAGEEMAIL";
	
    protected final String ERRORCODE_ATTRIBUTE = "ERRORCODE";
	 
    protected String ERRORCODE_VALUE; 
    
    @Autowired 
	private ApplicationInformationConfiguration applicatonInformation; 
    
    
    @Override
	public Model buildRequiredView(Model model) {
		
		model.addAttribute(ERRORCODE_ATTRIBUTE, ERRORCODE_VALUE);
		
		
		model.addAttribute( ERROREMAIL,  "mailto:" + applicatonInformation.ADMINISTRATOR_EMAIL + "?Subject=" + "Meldung eines Fehlers");
		
		return model;
	}
}
