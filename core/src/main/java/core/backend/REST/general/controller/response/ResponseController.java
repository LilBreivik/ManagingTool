package core.backend.REST.general.controller.response;

import org.springframework.stereotype.Component;

import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.general.request.MasterRESTRequest;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;

/**
 * This Controller describes , 
 * each controller, that does return 
 * some kind of an object
 * 
 * Works like a namespace 
 * */

@Component
public abstract class ResponseController 	<Task	extends  GeneralAbstractTaskImpl, 
										RequestType   extends  MasterRESTRequest, 
											ResponseType> 

							extends MasterRESTController<  Task ,  RequestType, ResponseType>{



	public  ResponseController (Task task) {

		super(task);	 
	}


}
