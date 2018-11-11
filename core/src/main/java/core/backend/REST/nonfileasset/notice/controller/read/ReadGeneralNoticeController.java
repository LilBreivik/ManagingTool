package core.backend.REST.nonfileasset.notice.controller.read;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;  
import core.backend.REST.nonfileasset.notice.task.read.ReadGeneralNoticeTask;
import notice.PersistenceNoticesPOJO;

/**
 * @FIXME: we have to keep in ming, that 
 * we need to provide a way to read the same notices if degree and startsemester 
 * are the same
 * 
 * */


@Controller 
public class ReadGeneralNoticeController 
									extends  ResponseController<NoticeParameter, PersistenceNoticesPOJO, ReadGeneralNoticeTask >{
	 
	public static final String ReadGeneralNoticeURL = "/Notice/Read/General";

	@Override
	@ResponseStatus( HttpStatus.OK ) 
	@RequestMapping(value = ReadGeneralNoticeURL, method = RequestMethod.POST, consumes="application/json")
	public SuccessResponse<PersistenceNoticesPOJO> handleRequest( ) {
 
		return super.handleRequest();
	}
	
	
	/**
	 * Configuration 
	 * Area 
	 * */
	 

	@Override 
	@Autowired
	public void setTask(ReadGeneralNoticeTask  task) {
		
		p_task = task;
	}
	
}

