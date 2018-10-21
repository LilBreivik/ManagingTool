package core.backend.NonREST.model.views.password;
 

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import core.backend.NonREST.model.IndexPageView; 


@Component
public class PasswordResetSucceededView  
										extends IndexPageView{

	private final String TITLE = "Das Passwort wurde erfolgreich zurückgesetzt"; 
	
	@Override
	public Model buildRequiredView(Model model) {
		

		model.addAttribute(TITLE_ATTRIBUTE, 
				TITLE); 
		
		return model;
	}

}
