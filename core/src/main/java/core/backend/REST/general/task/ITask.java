package core.backend.REST.general.task;

import core.backend.REST.general.request.MasterRESTRequest;
import core.backend.REST.general.response.MasterRESTResponse;
import core.backend.REST.general.response.result.successfully.SuccessResponse; 

public interface ITask<RequestParameter extends MasterRESTRequest,
							ResponseParameter extends SuccessResponse<?>> {

	public abstract void workOnTask();
	
	public abstract void workOnTask( RequestParameter param);
	
	public abstract ResponseParameter getResultsFromTask();
}
