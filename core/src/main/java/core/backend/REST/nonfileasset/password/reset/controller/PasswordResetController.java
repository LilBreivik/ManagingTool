package core.backend.REST.nonfileasset.password.reset.controller;
 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import core.backend.REST.general.controller.REST.NonResponseController;
import core.backend.REST.nonfileasset.password.forgotten.task.PasswordForgottenTask;
import core.backend.REST.nonfileasset.password.reset.parameter.PasswordResetRequest;
import core.backend.REST.nonfileasset.password.reset.task.PasswordResetTask;

@Controller
public class  PasswordResetController
                                        extends NonResponseController<PasswordResetTask, PasswordResetRequest >{
	
 
	public final static String PasswordResetControllerURL = "/Password/Reset"; 
	
	public final static String PasswordResetURL =  "RESET_URL_VALUE";
	 
	public final static String PasswordResetSucceededURL = "/passwordResetSuccessfull"; 
	 
	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = PasswordResetControllerURL , method = RequestMethod.GET)
	public void handleRequest(HttpServletRequest request,
								HttpServletResponse response,
										@RequestParam(value = PasswordResetURL) String resetURLValue ) {
		 
		PasswordResetRequest passwordForgottenRequestObject = PasswordResetRequest
					.buildPasswordResetRequest( resetURLValue);
		
		
		super.handleRequest(request, response, passwordForgottenRequestObject);
	}
	
	
	
	@Override
	@Autowired
	public void setTask(PasswordResetTask task) {
	 
	    p_task =  task;
		p_landingUrl = PasswordResetSucceededURL; 
	}
	 
}
