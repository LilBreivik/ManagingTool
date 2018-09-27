package core.backend.REST.general.response.result.faulty.failures;
 
import org.springframework.http.HttpStatus;

import core.backend.advice.AdviceMessage; 

public interface IErrorInformationHandler {

	public void setMoreInfo(AdviceMessage moreInfo);
	
	public HttpStatus getHttpStatus();
	
	public String getErrorMessage();
	
	public String getMoreInfo();

	public void  setErrorMessage(String errorMessage);
}
