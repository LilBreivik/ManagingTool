package core.backend.NonREST.page.general;

import org.springframework.ui.Model;

public interface IPageController {

	public String serveTemplate();
	
	public String serveTemplate(Model template);
}
