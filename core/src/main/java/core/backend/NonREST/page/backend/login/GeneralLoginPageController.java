package core.backend.NonREST.page.backend.login;

import core.backend.NonREST.model.LoginPageView; 
import core.backend.NonREST.page.general.IndexPageController;

public abstract class GeneralLoginPageController <loginView extends LoginPageView>

												extends IndexPageController< loginView > {
 

	public static final String basePageURL = "/"; 
													
    public static final String loginPageURL = "/login"; 
													
    public static final String loginPageTemplate = "login"; 	
    
    
    public GeneralLoginPageController() {
    	
    	super(loginPageTemplate);
    }
    
    @Override
    public String serveTemplate() {
 
    	return loginPageURL;
    }
}
