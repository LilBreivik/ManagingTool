package core.backend.NonREST.page.general;

import org.springframework.ui.Model;

public abstract  class PageControllerImpl 

								implements IPageController {
 

	@Override
	public String serveTemplate() {
	
		throw new  UnsupportedOperationException("shall be implemented in the inherited class");
	}
	
	@Override
	public String serveTemplate(Model template) {
		
		throw new  UnsupportedOperationException("shall be implemented in the inherited class");
	}
}
