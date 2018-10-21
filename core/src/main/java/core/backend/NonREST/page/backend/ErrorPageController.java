package core.backend.NonREST.page.backend;
 
import javax.servlet.http.HttpServletRequest; 
import org.springframework.boot.autoconfigure.web.ErrorController; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 

import core.backend.NonREST.model.views.errors.ErrorPageView;
import core.backend.NonREST.page.general.IndexPageController;
 

@Controller
public class ErrorPageController 

									extends IndexPageController<ErrorPageView>
									
													implements ErrorController {
   
	@GetMapping("/error")
    public String  renderErrorPage(Model template, HttpServletRequest httpRequest) {
         
    	System.out.println();
    	
        int httpErrorCode =  (Integer) httpRequest .getAttribute("javax.servlet.error.status_code");
        
        Model errorPage = p_view.buildErrorPageModel(httpErrorCode, template);
        
        return serveTemplate(errorPage);
    }

	@Override
	public String serveTemplate(Model template) {
		
		Model errorTemplae = template;
		
		return "error";
	}

	@Override
	public String getErrorPath() {
		
		return "error";
	} 
 
}