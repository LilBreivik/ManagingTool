package core.controller.response.result;

import org.springframework.http.HttpStatus;

import core.controller.response.MasterResponse;

public class ResponseResult<T> 
						extends MasterResponse<T> {
	
	public ResponseResult(T body, HttpStatus status) {
		super(body, status);
	}
 

}
