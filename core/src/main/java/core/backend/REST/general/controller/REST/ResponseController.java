package core.backend.REST.general.controller.REST;
 
import org.springframework.stereotype.Component; 
import core.backend.REST.general.request.RESTRequest;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.response.AbstractResponseTaskImpl; 


/**
 * General Implementation 
 * for  a certain contoller, that 
 * handles a response for a certain request
 * 
 * 
 * Task = Tasktype extends GeneralAbstractTaskImpl
 * Request = Requesttype
 * Response = Response
 * 
 * */

@Component  
public abstract class ResponseController < Request extends RESTRequest,
											 Response, 	
											   Task extends AbstractResponseTaskImpl<Request, Response> > {
	
	
	/**
	 * Task that is executed, 
	 * by the controller 
	 * 
	 * */ 
	
	protected Task p_task; 
	 
	
	/*
	 * Method that is need 
	 * to handle a certain Request , without 
	 * a Request Object 
	 * */
	
	
	public SuccessResponse<Response> handleRequest() {
	  	
		
		
		p_task.workOnTask();
		
		// At last we will return the reuslt of the above task-handling procedere 
		
		return   p_task.getResultsFromTask(); 
	}
	 
	/*
	 * Method that is need 
	 * to handle a certain Request , with
	 * a Request Object 
	 * 
	 * @param Request restRequest
	 * */
	 
 
	public SuccessResponse<Response> handleRequest(Request restRequest) {
	 
		
		// At first we check if the parameter is correct 
		restRequest.verifyParameter();
		
	 
		// Secondly, we do the expected task , on the sended parameter
		p_task.workOnTask(restRequest);
		
		// At last we will return the reuslt of the above task-handling procedere 
		
		return p_task.getResultsFromTask(); 
	}


	/**
	 * Setter and Getter 
	 * Area
	 * */
	
	
	public void setTask(Task p_task) {
	
		this.p_task = p_task;
	} 
}
 