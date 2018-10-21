package core.backend.NonREST.model;

import org.springframework.ui.Model;

public abstract class LoginPageView 
									extends IndexPageView{

	protected final String LOGIN_DISMISS_ATTRIBUTE = "LOGIN_DISMISS";
	

	protected boolean LOGIN_DISMISS = false; 
	
	@Override
	public  Model buildRequiredView(Model model) {
		
		model.addAttribute(LOGIN_DISMISS_ATTRIBUTE, LOGIN_DISMISS); 
		
		return model;
	}
}
