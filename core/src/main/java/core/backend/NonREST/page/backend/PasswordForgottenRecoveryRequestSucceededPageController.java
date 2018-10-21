package core.backend.NonREST.page.backend;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import core.backend.NonREST.model.views.password.PasswordForgottenRecoveryRequestSucceededView;
import core.backend.NonREST.page.general.IndexPageController;
import core.backend.REST.nonfileasset.password.forgotten.controller.PasswordForgottenController;

@Controller
public class PasswordForgottenRecoveryRequestSucceededPageController 

																extends  IndexPageController<PasswordForgottenRecoveryRequestSucceededView> {

	 
	public static final String  passwordForgottenRecoveryRequestSucceededPageTemplate ="passwordForgottenRecoveryRequestSucceeded"; 
	
	 
	public PasswordForgottenRecoveryRequestSucceededPageController( ) {
		super(passwordForgottenRecoveryRequestSucceededPageTemplate); 
	}

	@Override
	@GetMapping(PasswordForgottenController.PasswordForgottenRecoveryRequestSucceededURL)
	public String serveTemplate(Model template) {
		 
		return super.serveTemplate(template);
	}
}
 