package core.backend.REST.general.response.result.faulty.failures;

import org.springframework.http.HttpStatus;

import core.backend.advice.AdviceMessage;  

public class InternalErrorFailureResponse  
											implements IErrorInformationHandler{

	private String m_moreInfo; 
	
	private String m_errorMessage;
	
	public InternalErrorFailureResponse (String errorMessage) {
		
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
		
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}


	@Override
	public String getErrorMessage() {

		return m_errorMessage;
	}
 
	@Override
	public void setErrorMessage(String errorMessage) {
		m_errorMessage = errorMessage;
		
	}
 
}
