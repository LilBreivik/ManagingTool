package core.backend.REST.general.controller.nonequest.response;
  
 
import core.backend.REST.general.controller.MasterController; 
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.general.task.response.nonrequest.AbstractResponseTaskWithoutRequestImpl; 


/**
 * General Implementation 
 * for the IRESTRequestController<Request>
 * 
 * IRESTResponseForRequestControllerImpl<Task, Request, Response> 
 * 
 * Task = Tasktype extends GeneralAbstractTaskImpl
 * Request = Requesttype 
 * 
 * */
  
public abstract class NonRequestResponseController < Response, 
													   Task extends AbstractResponseTaskWithoutRequestImpl  > 
									extends  MasterController<Task>{


      public SuccessResponse<Response> handleRequest( ) {
	 
		
    	// Secondly, we do the expected task , on the sended parameter
		p_task.workOnTask();
		
		// At last we will return the reuslt of the above task-handling procedere 
		
		return p_task.getResultsFromTask(); 
	}
	
}
