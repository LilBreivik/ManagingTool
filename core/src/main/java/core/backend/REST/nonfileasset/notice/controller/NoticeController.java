package core.backend.REST.nonfileasset.notice.controller;
 
import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.nonfileasset.notice.parameter.request.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;

public class NoticeController< Request extends NoticeParameter, ResponseType>
										extends MasterRESTController< Request,   ResponseType>   {
 
	
	public NoticeController(NoticeTask task) {
		super(task); 
	}
}
   