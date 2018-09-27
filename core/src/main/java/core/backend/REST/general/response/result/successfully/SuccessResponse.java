package core.backend.REST.general.response.result.successfully;

import org.springframework.http.HttpStatus;

import core.backend.REST.general.response.result.ResponseResult; 

public class SuccessResponse<T> 
								extends ResponseResult<T> {

	public SuccessResponse(T body) {
		super(body, HttpStatus.OK);
	}

	 
}
