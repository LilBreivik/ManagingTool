package core.backend.REST.nonfileasset.password.change.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.password.change.parameter.PasswordChangeParameter;
import core.backend.REST.nonfileasset.password.change.parameter.response.PasswordChangeResponsePOJO;
import core.backend.REST.nonfileasset.password.change.task.PasswordChangeTask; 
 
 
@Controller
public class PasswordChangeController 
								extends ResponseController<PasswordChangeParameter, 
															  PasswordChangeResponsePOJO,
																PasswordChangeTask>{
 	
	
	public final static String PasswordChangeControllerURL = "/Password/Change"; 
	
	 
	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = PasswordChangeControllerURL, method = RequestMethod.POST, 
				consumes="application/json", produces="application/json")
	public SuccessResponse<PasswordChangeResponsePOJO> handleRequest(@RequestBody  PasswordChangeParameter restRequest) {
	  
		
		return super.handleRequest(restRequest);
	}
	
	
  
	@Override
	@Autowired
	public void setTask(PasswordChangeTask task) {
	 
	    p_task =  task; 
	}
	
	
}