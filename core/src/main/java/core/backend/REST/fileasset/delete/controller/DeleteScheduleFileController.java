package core.backend.REST.fileasset.delete.controller;

import core.backend.REST.fileasset.delete.parameter.request.DeleteFileParameter; 
import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.general.task.ITask;

public  abstract class DeleteScheduleFileController < Request extends DeleteFileParameter, ResponseType>
															extends MasterRESTController< Request,   ResponseType>  {

	public DeleteScheduleFileController(ITask task) {
		super(task); 
	}

}
