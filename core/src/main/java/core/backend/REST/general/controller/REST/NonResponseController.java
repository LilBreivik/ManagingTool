package core.backend.REST.general.controller.REST;
 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component; 
import core.backend.REST.general.request.RESTRequest;
import core.backend.REST.general.task.AbstractTaskImpl;
import core.utils.redirect.RedirectionHandler;


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

 
@Component 
public abstract class NonResponseController <Task extends AbstractTaskImpl, 
														Request extends  RESTRequest> {


	@Autowired 
	private RedirectionHandler m_redirectHandler; 
	
	protected Task p_task;

	protected  String p_landingUrl;
	
	
	public void handleRequest(HttpServletRequest request, 
								HttpServletResponse response,
								 Map<String, String> restRequest) {}

	 
	public void handleRequest(HttpServletRequest request, 
								HttpServletResponse response,
									Request restRequest) {	 
		
		// At first we check if the parameter is correct 
		restRequest.verifyParameter();
					
		// Secondly, we do the expected task , on the sended parameter
		p_task.workOnTask(restRequest);
			 
			  
		
		m_redirectHandler.handleRedirection(request, response, p_landingUrl);
	}

	public void handleRequest(HttpServletRequest request,
            HttpServletResponse response, 
               String resetURLValue) {


		m_redirectHandler.handleRedirection(request, response, resetURLValue);
    }
	 
	public void handleRequest( HttpServletResponse response,
				Request restRequest) {	 
 
		 
		// At first we check if the parameter is correct 
		restRequest.verifyParameter();
		
		// Secondly, we do the expected task , on the sended parameter
		p_task.workOnTask(restRequest);
		 
	}
	
	public void handleRequest( Request restRequest) {
		 
		
		// At first we check if the parameter is correct 
		restRequest.verifyParameter();
												 
		// Secondly, we do the expected task , on the sended parameter		
		p_task.workOnTask(restRequest);
	
	}
 
	/**
	 * Setter and Getter 
	 * Area
	 * */
	
	
	public void setTask(Task p_task) {
	
		this.p_task = p_task;
	} 
}
