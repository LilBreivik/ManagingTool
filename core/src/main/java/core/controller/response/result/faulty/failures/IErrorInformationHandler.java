package core.controller.response.result.faulty.failures;
 
import org.springframework.http.HttpStatus;

import core.controller.response.result.faulty.failuremessages.advice.AdviceMessage;

public interface IErrorInformationHandler {

	public void setMoreInfo(AdviceMessage moreInfo);
	
	public HttpStatus getHttpStatus();
	
	public String getErrorMessage();
	
	public String getMoreInfo();
}
