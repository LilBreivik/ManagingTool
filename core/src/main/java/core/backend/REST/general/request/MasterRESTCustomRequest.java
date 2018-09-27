package core.backend.REST.general.request;
 
import core.backend.utils.upload.ParamVerifierJob;
import resources.error.InternalError;
import resources.error.parameter.ParameterViolationError;

public abstract class MasterRESTCustomRequest extends MasterRESTRequest{

	
	// verifying method , that is need to validate the custom parameter 
	protected ParamVerifierJob p_verifierJob;
	
	public MasterRESTCustomRequest() {
		super();
	 
	}
	
	public MasterRESTCustomRequest(String courseName, String courseDegree, String courseTerm) {
		super(courseName, courseDegree, courseTerm);
	 
	}

	
	@Override
	public void verifyParameter() throws ParameterViolationError {
		
		
		super.verifyParameter();
		
		/* here we check 
    	 * the additonal verifying parameters
    	 *
    	 *
    	 * the p_verifierJob needs to be defined, 
    	 * if custom parameters shall be checked 
    	 */
    	if(p_verifierJob != null) {
    		
    		p_verifierJob.verifyCustomParameter();
    	}
    	else {
    		
    		throw new InternalError("Keine Verifizierungsmethode festgelegt");
    	}
	}
}
