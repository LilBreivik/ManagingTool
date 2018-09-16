package core.controller.pages;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.controller.pages.model.views.errors.ErrorPageView;

@Controller
public class ErrorPageController extends AbstractPageController{
 
	@Autowired 
	private ErrorPageView errorPageView; 
	
    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public String  renderErrorPage(Model template, HttpServletRequest httpRequest) {
          
        int httpErrorCode =  (Integer) httpRequest .getAttribute("javax.servlet.error.status_code");
        
        Model errorPage = errorPageView.buildErrorPageModel(httpErrorCode, template);
        
        return serveTemplate(errorPage);
    }

	@Override
	public String serveTemplate(Model template) {
		
		Model errorTemplae = template;
		
		return "error";
	}
}