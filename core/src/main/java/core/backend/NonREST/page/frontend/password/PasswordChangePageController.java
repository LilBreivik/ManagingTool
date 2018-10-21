package core.backend.NonREST.page.frontend.password;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import core.backend.NonREST.model.views.password.PasswordChangeView;
import core.backend.NonREST.page.general.GeneralIndexPageController;

@Controller
public class PasswordChangePageController 

									extends  GeneralIndexPageController<PasswordChangeView>{
	
	public static final String  passwordChangePageURL = "/ManagingTool/PasswordChange"; 
  
 
	@Override
	@GetMapping(passwordChangePageURL)
	public String serveTemplate(Model template) {
	 
		return super.serveTemplate(template);
	}
	

} 