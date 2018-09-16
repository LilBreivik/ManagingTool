package core.controller.response.result.faulty.failures;

import org.springframework.http.HttpStatus;

import core.controller.response.result.faulty.failuremessages.advice.AdviceMessage;

 
public class FileNotThereFailureResponse
						implements IErrorInformationHandler{

	private String m_moreInfo; 

	private String m_errorMessage;

	public FileNotThereFailureResponse (String errorMessage) {

		m_errorMessage = errorMessage;
	}


	
	@Override
	public String getMoreInfo() {
	
		return m_moreInfo;
	}


	@Override
	public void setMoreInfo(AdviceMessage moreInfo) {
	
		m_moreInfo = moreInfo.toString(); 
	}
	
	
	@Override
	public HttpStatus getHttpStatus() {
	
	
		return HttpStatus.NOT_FOUND;
	}
	
	
	@Override
	public String getErrorMessage() {
	
	
		return m_errorMessage;
	}


}
