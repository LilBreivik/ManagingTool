package core.backend.REST.general.request;

import core.backend.utils.upload.ParamVerifierJob;


public abstract class MasterRESTRequest<Request> {

	protected  Request p_request; 
	
	public MasterRESTRequest(Request request) {
		
		p_request = request; 
	} 

	// verifying method , that is need to validate the custom parameter 
	protected ParamVerifierJob p_verifierJob;
	
	protected final String parameterErrorMessageInvalidParameter = "Die Request-Parameter sind falsch.";
	
	public abstract  void verifyParameter() ;

	public Request getRequest() {
		return p_request;
	}

	public void setRequest(Request p_request) {
		this.p_request = p_request;
	}
	 
}
 