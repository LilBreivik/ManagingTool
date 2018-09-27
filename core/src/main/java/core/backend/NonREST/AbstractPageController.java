package core.backend.NonREST;

import org.springframework.ui.Model;
 

public abstract class AbstractPageController {
	
	public abstract String serveTemplate(Model template);
}
