package core.backend.REST.general.controller.nonresponse;
  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.general.request.MasterRESTRequest; 
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;
import core.utils.redirect.RedirectionHandler;


/**
 * This Controller describes , 
 * each controller, that do not return 
 * some kind of an object, but a redirection 
 * */

 
@Component
public abstract class NonResponseController <Task	extends  GeneralAbstractTaskImpl, 
												RequestType   extends  MasterRESTRequest> 

										extends MasterRESTController<  Task ,  RequestType, Object>
{
  
	@Autowired 
	private RedirectionHandler m_redirectHandler; 
	
	// if not initalized, every redirect gooes to "/"
	
	protected String p_landingUrl; 
	
	
	public NonResponseController(Task task) {

		super(task);
		 
	}

	public void handleNonResponseRequest(HttpServletRequest request, 
											HttpServletResponse response,
												RequestType  restRequest) 
	{	 
		// At first we check if the parameter is correct 
		restRequest.verifyParameter();
				
			 
		// Secondly, we do the expected task , on the sended parameter
		p_task.workOnTask(restRequest);
		 
		 
		m_redirectHandler.handleRedirection(request, response, p_landingUrl);
	}

	public void handleNonResponseRequest( RequestType  restRequest) {
	 
		// At first we check if the parameter is correct 
		restRequest.verifyParameter();
						
					 
		// Secondly, we do the expected task , on the sended parameter
		p_task.workOnTask(restRequest);
		 
	}
 
}
 