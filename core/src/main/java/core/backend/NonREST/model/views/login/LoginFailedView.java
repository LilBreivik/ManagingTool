package core.backend.NonREST.model.views.login;

import org.springframework.stereotype.Component;

import core.backend.NonREST.model.LoginPageView;

@Component
public class LoginFailedView 
								extends  LoginPageView{
	 
	public LoginFailedView() {
		
		LOGIN_DISMISS = true; 
	}
}
