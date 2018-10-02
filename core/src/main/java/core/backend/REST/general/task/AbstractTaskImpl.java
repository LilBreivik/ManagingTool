package core.backend.REST.general.task;

import org.apache.poi.ss.formula.eval.NotImplementedException;

import core.backend.REST.general.request.MasterRESTRequest;
import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import core.backend.REST.general.response.result.successfully.SuccessResponse;

public abstract class AbstractTaskImpl<RequestParameter extends  MasterRESTRequest, ResponseType> 
											implements ITask<RequestParameter, SuccessResponse<ResponseType>> {
  
	@Override
	public SuccessResponse<ResponseType> getResultsFromTask() {
		 
		throw new NotImplementedException("shall be inherited");
	}
	
	@Override
	public void workOnTask() {

		throw new NotImplementedException("not used yet");
	}
	
	@Override
	public void workOnTask(RequestParameter param){
	
		throw new NotImplementedException("not used yet");
	}
	
}
