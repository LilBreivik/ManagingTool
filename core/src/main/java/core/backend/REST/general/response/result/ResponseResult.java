package core.backend.REST.general.response.result;

import org.springframework.http.HttpStatus; 
import core.backend.REST.general.response.MasterRESTResponse; 

public class ResponseResult<T> 
						extends MasterRESTResponse<T> {
	
	public ResponseResult(T body, HttpStatus status) {
		super(body, status);
	}
 

}
