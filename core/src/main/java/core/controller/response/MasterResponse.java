package core.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MasterResponse<T> 
						extends ResponseEntity<T>  {

	protected T p_responseBody; 
	
	protected HttpStatus p_errorCode;
	
	public MasterResponse(T body, HttpStatus status) {
		super(body, status);
		 
		p_responseBody = body; 
		p_errorCode = status; 
	}
 

}
