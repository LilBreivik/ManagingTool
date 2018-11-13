package core.backend.REST.general.task.response.nonrequest;

import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.general.AbstractTaskWithoutRequestImpl;

public class AbstractResponseTaskWithoutRequestImpl <  ResponseType >  
										extends AbstractTaskWithoutRequestImpl {

	
	public SuccessResponse<ResponseType> getResultsFromTask() {
		 
		throw new UnsupportedOperationException("shall be inherited");
	}
	 
} 
 