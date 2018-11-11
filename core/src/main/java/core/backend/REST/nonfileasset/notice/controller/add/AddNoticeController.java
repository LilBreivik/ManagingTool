package core.backend.REST.nonfileasset.notice.controller.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import core.backend.REST.nonfileasset.notice.task.add.AddNoticeTask;
import notice.PersistenceNoticesPOJO; 
 
@Controller 
public class AddNoticeController 
									extends  ResponseController<NoticeParameter, PersistenceNoticesPOJO, AddNoticeTask>{
	
	

	public static final String AddNoticeURL = "/Notice/Add";

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = AddNoticeURL, method = RequestMethod.POST, consumes="application/json")
	public SuccessResponse<PersistenceNoticesPOJO> handleRequest( @RequestBody NoticeParameter noticeParameter ) {
 
		return super.handleRequest(  noticeParameter );
	}
	
	
	/**
	 * Configuration 
	 * Area 
	 * */
	

	@Override 
	@Autowired
	public void setTask( AddNoticeTask task) {
		
		p_task = task;
	}
	 
}
