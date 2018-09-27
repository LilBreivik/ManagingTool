package core.backend.REST.general.response.result.faulty;

import org.springframework.http.HttpStatus;

import core.backend.REST.general.response.result.ResponseResult;  

public abstract class FailureResponse<IErrorInformationHandler >
						extends ResponseResult<IErrorInformationHandler > {
 
	public FailureResponse(IErrorInformationHandler responseBody , HttpStatus errorStatus) {
		super(responseBody, errorStatus);
	}
}
