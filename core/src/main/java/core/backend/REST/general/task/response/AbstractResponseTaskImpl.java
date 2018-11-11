package core.backend.REST.general.task.response;

import core.backend.REST.general.request.RESTRequest;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.AbstractTaskImpl;

public class AbstractResponseTaskImpl < RequestParameter extends RESTRequest, ResponseType > 
										extends AbstractTaskImpl< RequestParameter > {

	public SuccessResponse<ResponseType> getResultsFromTask() {
		 
		throw new UnsupportedOperationException("shall be inherited");
	}
}
