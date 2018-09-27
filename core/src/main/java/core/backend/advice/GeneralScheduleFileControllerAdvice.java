package core.backend.advice;
  
  

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import core.backend.REST.general.response.result.faulty.failuremessages.FailureResponseFactory;
import core.backend.REST.general.response.result.faulty.failures.FileNotThereFailureResponse;
import core.backend.REST.general.response.result.faulty.failures.InternalErrorFailureResponse;
import core.backend.REST.general.response.result.faulty.failures.ParameterViolatedFailureResponse;
import resources.error.FileIsMissingError;
import resources.error.InternalError; 
import resources.error.parameter.ParameterViolationError; 

@ControllerAdvice(basePackages = "core.backend.REST")
public class GeneralScheduleFileControllerAdvice extends ResponseEntityExceptionHandler{
  
	
	  /**
	   * 
	   * 'FIXME: put a generaln Exception Handler in there 
	   * (for NullPointer etc. )
	   * */
	
	  @ExceptionHandler(ParameterViolationError .class)
	  protected  ResponseEntity<ParameterViolatedFailureResponse>  ParametersAreWrong(ParameterViolationError violatedParameter) {
		 
		  System.out.println("");
		  
		  ParameterViolatedFailureResponse response =  FailureResponseFactory.createParameterViolationErrorResponse(violatedParameter);
		       
		  return new ResponseEntity<ParameterViolatedFailureResponse>(response , response.getHttpStatus());
	  }
	  
	 
	  @ExceptionHandler(InternalError.class)
	  protected ResponseEntity<InternalErrorFailureResponse>  InternalErrorHappend(InternalError internalError) {
		   
		    InternalErrorFailureResponse response = FailureResponseFactory.createParameterInternalErrorResponse(internalError);
		     
		    return new ResponseEntity<InternalErrorFailureResponse>(response , response.getHttpStatus());
	 
	  }
	 
	  @ExceptionHandler(FileIsMissingError.class)
	  protected  ResponseEntity< FileNotThereFailureResponse> FileNotThere(FileIsMissingError fileDoesNotExist) {
		 
		  FileNotThereFailureResponse response =  FailureResponseFactory.createFileNotThereErrorResponse(fileDoesNotExist);
		       
		  return new ResponseEntity< FileNotThereFailureResponse>(response , response.getHttpStatus());
	  }
}
