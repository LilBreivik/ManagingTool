package resources.error.password;

import org.springframework.http.HttpHeaders;

import resources.error.MasterError;

public class PasswordResetError 
								extends MasterError{
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -5732667428636903569L;

	private  HttpHeaders m_passwordResetFailureResponse;
 
	private final String m_redirectURL = "/error";
	 
	private final String locationHeader = "Location";
	
	private final static String passwordResetProcessErrorMessage = "Password Reset-Process failed"; 
	
	public PasswordResetError() {
		super(passwordResetProcessErrorMessage);
		
		m_passwordResetFailureResponse = new HttpHeaders();
		
		m_passwordResetFailureResponse.set( locationHeader, m_redirectURL);
		
	}
			 
	protected PasswordResetError(String redirectURL  ) {
		super(passwordResetProcessErrorMessage);
	
		m_passwordResetFailureResponse = new HttpHeaders();
		
		m_passwordResetFailureResponse.set( locationHeader, redirectURL);
 
	}

	public HttpHeaders getPasswordResetFailureResponse() {
		return m_passwordResetFailureResponse;
	}

 
 
}
