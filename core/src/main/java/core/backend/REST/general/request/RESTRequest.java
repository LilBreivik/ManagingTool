package core.backend.REST.general.request;
 

/**
 * Class that 
 * 
 * describes a common Rest Request Object 
 * 
 * RESTRequest<Request>
 * 
 * Request = Requesttype of Object 
 * */

public abstract class RESTRequest<Request> {

	protected final String parameterErrorMessageInvalidParameter = "Die Request-Parameter sind falsch.";
	
	private   Request p_request; 
 
	
	public RESTRequest() {}
	
	public RESTRequest(Request request) {
		
		p_request = request; 
	}
	
	public abstract  void verifyParameter() ;

	public Request getRequest() {
	
		return p_request;
	}

	 
}
 