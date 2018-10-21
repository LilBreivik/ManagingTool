
package core.backend.NonREST.page.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model; 

import core.backend.NonREST.model.IndexPageView;

@Component
public abstract class IndexPageController<View extends IndexPageView> 
							
										extends  PageControllerImpl  {
	
	protected String p_templateURL; 
	
	@Autowired
	protected View p_view; 

	public IndexPageController() {}
	
	public IndexPageController(String templateURL) {
		
		p_templateURL = templateURL; 
	}
	  
	@Override
	public String serveTemplate(Model template) {
		
		p_view.buildRequiredView(template);
		
		return p_templateURL; 
	}
	 
}

