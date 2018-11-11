package core.backend.REST.general.task;

import core.backend.REST.general.request.RESTRequest; 

/**
 * 
 * General Implementation 
 * of an ITask 
 * 
 * AbstractTaskImpl< RequestParameter > 
 * 
 * 
 * 
 * */


public abstract class AbstractTaskImpl< RequestParameter extends RESTRequest> 
												implements ITask<RequestParameter > {
  
	@Override
	public void workOnTask() {

		throw new UnsupportedOperationException("not used yet");
	}
	
	@Override
	public void workOnTask(RequestParameter param){
	
		throw new UnsupportedOperationException("not used yet");
	}
}
