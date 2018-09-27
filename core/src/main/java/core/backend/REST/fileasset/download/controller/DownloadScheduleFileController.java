package core.backend.REST.fileasset.download.controller;
 
import core.backend.REST.fileasset.download.parameter.request.DownloadFileParameter;
import core.backend.REST.fileasset.download.task.DownloadFileTask;
import core.backend.REST.general.controller.MasterRESTController; 

public abstract class DownloadScheduleFileController< Request extends DownloadFileParameter, ResponseType>
											extends MasterRESTController< Request,   ResponseType>   {
 
	public DownloadScheduleFileController(DownloadFileTask task) {
		super(task); 
	}
 
}
