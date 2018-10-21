package core.backend.NonREST.page.backend.login;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView; 
import core.backend.NonREST.model.views.login.LoginView;  
 

@Controller
public class LoginPageController  
									extends GeneralLoginPageController< LoginView > {
   
	
	@GetMapping(basePageURL)
	public ModelAndView serveTemplate(ModelMap model) {
		  
		return  new ModelAndView("redirect:" + loginPageURL, model);
	}
	 
	@Override
	@GetMapping(loginPageURL)
	public String serveTemplate() {
	 
		return super.serveTemplate();
	}
} 