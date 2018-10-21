package core.backend.REST.nonfileasset.password.forgotten.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.general.controller.nonresponse.NonResponseController; 
import core.backend.REST.nonfileasset.password.forgotten.parameter.PasswordForgottenRequest;
import core.backend.REST.nonfileasset.password.forgotten.task.PasswordForgottenTask; 

@Controller
public class PasswordForgottenController 
		extends NonResponseController<PasswordForgottenTask, PasswordForgottenRequest>{
				
	public final static String PasswordForgottenControllerURL = "/Password/Forgotten"; 
	
	public final static String PasswordForgottenRecoveryRequestSucceededURL = "/passwordForgottenRecoverySuccessfull"; 
	
	@Autowired 
	public PasswordForgottenController(PasswordForgottenTask  task ) {
										
		super(task);  
		p_landingUrl = PasswordForgottenRecoveryRequestSucceededURL; 
	}

	
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = PasswordForgottenControllerURL, method = RequestMethod.POST)
	public void  handlePasswordForgottenRequest(
					HttpServletRequest request, 
							HttpServletResponse response,
									@RequestParam Map<String, String> passwordForgottenRequest )  
							
	{ 
		 
		PasswordForgottenRequest passwordForgottenRequestObject = PasswordForgottenRequest
																	.buildPasswordForgottenRequest(passwordForgottenRequest);
		
		
		
		
	    super.handleNonResponseRequest(request, response, passwordForgottenRequestObject );
	}
}
