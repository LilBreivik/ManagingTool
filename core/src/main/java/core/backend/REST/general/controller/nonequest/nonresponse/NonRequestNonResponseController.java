package core.backend.REST.general.controller.nonequest.nonresponse;
 
import core.backend.REST.general.controller.MasterController;
import core.backend.REST.general.task.response.nonrequest.AbstractResponseTaskWithoutRequestImpl; 

 
public abstract class NonRequestNonResponseController <  Task extends   AbstractResponseTaskWithoutRequestImpl> 
															extends  MasterController<Task> {

 
	public void  handleRequest() {
	  	
		p_task.workOnTask();
		
		// At last we will return the reuslt of the above task-handling procedere 
		
		p_task.getResultsFromTask(); 
	}

}
