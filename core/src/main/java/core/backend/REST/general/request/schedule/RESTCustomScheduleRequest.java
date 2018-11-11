package core.backend.REST.general.request.schedule;
 
 
import core.backend.utils.upload.ParamVerifierJob;
import resources.components.elements.POJO.Course.CoursePOJO; 
import resources.error.parameter.ParameterViolationError;

public abstract class RESTCustomScheduleRequest<ScheduleRequest extends CoursePOJO> 

												extends RESTScheduleRequest<ScheduleRequest>{

	public  RESTCustomScheduleRequest() {}
	 
	// verifying method , that is need to validate the custom parameter 
	protected ParamVerifierJob p_verifierJob;
 
	public  RESTCustomScheduleRequest(ScheduleRequest pojo) {
		super(pojo);
	}
  
	@Override
	public void verifyParameter() throws ParameterViolationError {
		
		// at first we will verify the schedule dependent parameter 
		
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
	}
}
