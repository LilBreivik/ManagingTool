package core.backend.REST.general.controller.nonrequest;

import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.general.request.MasterRESTRequest; 
import core.backend.REST.general.task.ITask;

public class NonRequestController <ResponseType> 
	
									extends MasterRESTController<MasterRESTRequest, ResponseType>{

	public NonRequestController() {}
	
	public NonRequestController(ITask task) {
		super(task); 
	}

	 
}
