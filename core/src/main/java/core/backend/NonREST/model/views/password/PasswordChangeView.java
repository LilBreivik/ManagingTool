package core.backend.NonREST.model.views.password;

import org.springframework.stereotype.Component; 

import core.backend.NonREST.model.IndexPageView;
 
@Component
public class PasswordChangeView
						extends   IndexPageView{

	
	public PasswordChangeView() {
		
		HEADLINE_DISPLAY = true; 
		HEADLINE = "Einstellungen";
		
		CONTENT_DISPLAY = true; 
		TEMPLATE_REPLACEMENT_EXPRESSION ="fragments/passwordchangemanager"; 
		
		FOOTER_DISPLAY = false;
	}
	 

}
