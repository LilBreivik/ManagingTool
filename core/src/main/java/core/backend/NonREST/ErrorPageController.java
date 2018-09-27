package core.backend.NonREST;
 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 

import core.backend.NonREST.model.views.errors.ErrorPageView;
 

@Controller
public class ErrorPageController extends AbstractPageController implements ErrorController {
 
	@Autowired 
	private ErrorPageView errorPageView; 

	 
	@GetMapping("/error")
    public String  renderErrorPage(Model template, HttpServletRequest httpRequest) {
         
    	System.out.println();
    	
        int httpErrorCode =  (Integer) httpRequest .getAttribute("javax.servlet.error.status_code");
        
        Model errorPage = errorPageView.buildErrorPageModel(httpErrorCode, template);
        
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