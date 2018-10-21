package core.backend.REST.general.controller;
 

import core.backend.REST.general.request.MasterRESTRequest; 
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.ITask;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;

public abstract class MasterRESTController<
								Task extends GeneralAbstractTaskImpl, 
											Request extends  MasterRESTRequest, 
													ResponseType>	 {

	protected Task p_task; 
	
    public MasterRESTController() {}
	
	public MasterRESTController(Task task) {
		 
		p_task = task; 
	} 
	
	
	/**
	 * The mthod  handleRequest() 
	 * describes the Request Processing Pipeline ....
	 * 
	 * @param void, handle Request without a param, sent from Client (expected for GET requests)
	 * @param  MasterRESTRequest, request sent from Client (expected fror POST requests)
	 * 
	 * */
 
 
	protected SuccessResponse<ResponseType> handleRequest() {
	  	
		p_task.workOnTask();
		
		// At last we will return the reuslt of the above task-handling procedere 
		
		return p_task.getResultsFromTask(); 
	}
	 
	 
	protected SuccessResponse<ResponseType> handleRequest(Request restRequest) {
	 
		
		// At first we check if the parameter is correct 
		restRequest.verifyParameter();
		
	 
		// Secondly, we do the expected task , on the sended parameter
		p_task.workOnTask(restRequest);
		
		// At last we will return the reuslt of the above task-handling procedere 
		
		return p_task.getResultsFromTask(); 
	}
	
	
	
	protected SuccessResponse<ResponseType> handleResponse() {
	  	
		// At last we will return the reuslt of the above task-handling procedere 
		
		return p_task.getResultsFromTask(); 
	}
	
}
