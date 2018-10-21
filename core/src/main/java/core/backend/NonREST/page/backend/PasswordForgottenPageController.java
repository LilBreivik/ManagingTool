package core.backend.NonREST.page.backend;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import core.backend.NonREST.model.views.password.PasswordForgottenView;
import core.backend.NonREST.page.general.IndexPageController;

@Controller 
public class PasswordForgottenPageController 
											extends IndexPageController<PasswordForgottenView > {
  
	public static final String passwordForgottenPageURL = "/passwordForgotten"; 
	
	public static final String passwordForgottenPageTemplate = "passwordForgotten"; 
	
	public PasswordForgottenPageController() {
		
		super(passwordForgottenPageTemplate);
	} 
	 
	
	@Override
	@GetMapping(passwordForgottenPageURL)
	public String serveTemplate(Model template) {
	 
		return super.serveTemplate(template);
	}
	
}
