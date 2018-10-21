package core.backend.NonREST.model.views.password;
 

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import core.backend.NonREST.model.IndexPageView; 


@Component
public class PasswordForgottenRecoveryRequestSucceededView 

														extends IndexPageView{

	private final String TITLE = "Anfrage auf die Zurücksetzung eines Passwortes"; 
	
	@Override
	public Model buildRequiredView(Model model) {
		

		model.addAttribute(TITLE_ATTRIBUTE, 
				TITLE); 
		
		return model;
	}

}
