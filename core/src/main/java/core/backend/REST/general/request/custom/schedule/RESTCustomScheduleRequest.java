package core.backend.REST.general.request.custom.schedule;
 
 
import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.error.InternalError;
import resources.error.parameter.ParameterViolationError;

public abstract class RESTCustomScheduleRequest<ScheduleRequest extends CoursePOJO> 

												extends RESTScheduleRequest<ScheduleRequest>{

	 
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
    	else {
    		
    		throw new InternalError("Keine Verifizierungsmethode festgelegt");
    	}
	}
}
