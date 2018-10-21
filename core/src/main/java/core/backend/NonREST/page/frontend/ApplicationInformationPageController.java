package core.backend.NonREST.page.frontend;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import core.backend.NonREST.model.views.ApplicationInformationView;
import core.backend.NonREST.page.general.GeneralIndexPageController; 

@Controller
public class ApplicationInformationPageController 

									extends GeneralIndexPageController<ApplicationInformationView>{


	public static final String  applicationInformationPageURL = "/ManagingTool/ApplicationInformation"; 
	  

	@Override
	@GetMapping(applicationInformationPageURL)
	public String serveTemplate(Model template) {

		return super.serveTemplate(template);
	}

} 