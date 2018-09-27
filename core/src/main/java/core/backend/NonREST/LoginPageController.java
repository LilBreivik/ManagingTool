package core.backend.NonREST;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
 

@Controller
public class LoginPageController {

	@GetMapping("/")
	public ModelAndView serveLoginTemplateToRoot(ModelMap model) {
		 
	 
		return  new ModelAndView("redirect:/login", model);
	}
	
	@GetMapping("/login")
	public String serveLoginTemplate(Model template) {
		 
		return "login";
	}
	
	@GetMapping("/loginFailed")
	public String serveLoginFailedTemplate( Model template) { 
		
		return "loginfailed";
	}
}
