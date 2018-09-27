package core.backend.REST.fileasset.upload.controller;
  
import core.backend.REST.fileasset.upload.parameter.UploadFileParameter;
import core.backend.REST.fileasset.upload.task.UploadFileTask;
import core.backend.REST.general.controller.MasterRESTController; 

public abstract class UploadScheduleFileController< Request extends UploadFileParameter, ResponseType>
											extends MasterRESTController< Request,   ResponseType>   {
 
	public UploadScheduleFileController(UploadFileTask task) {
		super(task); 
	}
 
}
