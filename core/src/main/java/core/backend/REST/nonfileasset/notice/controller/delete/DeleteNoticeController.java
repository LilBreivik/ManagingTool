package core.backend.REST.nonfileasset.notice.controller.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import core.backend.REST.fileasset.delete.task.DeleteFileTask;
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter; 
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import core.backend.REST.nonfileasset.notice.task.delete.DeleteNoticeTask;
import notice.PersistenceNoticesPOJO;

@Controller
public class DeleteNoticeController 
										extends  ResponseController<NoticeParameter, PersistenceNoticesPOJO,  DeleteNoticeTask >{
	
	

	public static final String DeleteNoticeURL = "/Notice/Delete";

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = DeleteNoticeURL, method = RequestMethod.POST, consumes="application/json")
	public SuccessResponse<PersistenceNoticesPOJO> handleRequest( @RequestBody NoticeParameter noticeParameter ) {
 
		return super.handleRequest(  noticeParameter );
	}
	
	
	/**
	 * Configuration 
	 * Area 
	 * */
	 

	@Override 
	@Autowired
	public void setTask( DeleteNoticeTask  task) {
		
		p_task = task;
	}
	
 
}

