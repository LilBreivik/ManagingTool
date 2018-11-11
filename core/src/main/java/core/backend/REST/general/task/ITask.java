package core.backend.REST.general.task;

import core.backend.REST.general.request.RESTRequest;

/**
 * 
 * General Interface 
 * that describes a certain task
 * 
 * */

public interface ITask<Request extends RESTRequest > {

	// common method , to describe a certain task 
	
	public abstract void workOnTask();
	
	/**
	 * 
	 * common method , to describe a certain task 
	 * 
	 * @param RESTRequest<Request>, param that is needed by the certain task
	 * */
	
	public abstract void workOnTask(Request param);

	 
}
