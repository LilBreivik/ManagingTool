package core.backend.NonREST.page.frontend.password;
  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import core.backend.NonREST.model.views.password.PasswordResetSucceededView; 
import core.backend.NonREST.page.general.IndexPageController;
import core.backend.REST.nonfileasset.password.reset.controller.PasswordResetController; 

@Controller
public class PasswordResetSucceededPageController 

								extends  IndexPageController<PasswordResetSucceededView> {


	public static final String  passwordResetSucceededPageTemplate = "passwordResetSuccessfull"; 
	
  
	public final static String PasswordResetSucceededURL = "/passwordResetSuccessfull"; 
	 
	 
	public PasswordResetSucceededPageController ( ) {
		
		p_templateURL = passwordResetSucceededPageTemplate; 
		 
	} 
	
 
	@Override
	@GetMapping(PasswordResetController.PasswordResetSucceededURL)
	public String serveTemplate(Model template) {
		 
		return super.serveTemplate(template);
	}
}
 