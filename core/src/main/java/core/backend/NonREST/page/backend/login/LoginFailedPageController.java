package core.backend.NonREST.page.backend.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import core.backend.NonREST.model.views.login.LoginFailedView;  

@Controller
public class LoginFailedPageController 
										extends GeneralLoginPageController< LoginFailedView >  {
	 
	 public static final String loginFialePageURL = "/loginFailed"; 
	
	@Override 
	@GetMapping(loginFialePageURL )
	public String serveTemplate(Model template) { 
		

		return super.serveTemplate(template);
	}
	
 
}
 