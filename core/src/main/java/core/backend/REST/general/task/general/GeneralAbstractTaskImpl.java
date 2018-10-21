package core.backend.REST.general.task.general;

import core.backend.REST.general.request.MasterRESTRequest; 
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.ITask; 

public abstract class GeneralAbstractTaskImpl< RequestParameter extends  MasterRESTRequest,
														ResponseType> 
											implements ITask<RequestParameter, SuccessResponse<ResponseType>> {
  
	 
	@Override
	public SuccessResponse<ResponseType> getResultsFromTask() {
		 
		throw new UnsupportedOperationException("shall be inherited");
	}
	
	@Override
	public void workOnTask() {

		throw new UnsupportedOperationException("not used yet");
	}
	
	@Override
	public void workOnTask(RequestParameter param){
	
		throw new UnsupportedOperationException("not used yet");
	}
	
}
