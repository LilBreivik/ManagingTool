package core.backend.REST.nonfileasset.notice.controller.read;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.nonfileasset.notice.parameter.SpecficNoticeParameter; 
import core.backend.REST.nonfileasset.notice.task.read.ReadSpecificNoticeTask; 
import resources.components.elements.POJO.Notices.NoticesPOJO;

@Controller 
public class ReadSpecificNoticeController 	
											extends  ResponseController<SpecficNoticeParameter , NoticesPOJO, ReadSpecificNoticeTask>{
	 
	public static final String ReadSpecificNoticeURL = "/Notice/Read/Specific";

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = ReadSpecificNoticeURL, method = RequestMethod.POST, consumes="application/json")
	public SuccessResponse<NoticesPOJO> handleRequest( @RequestBody SpecficNoticeParameter  noticeParameter ) {
  
		return super.handleRequest(  noticeParameter );
	}
	
	
	/**
	 * Configuration 
	 * Area 
	 * */
	 
 
	@Override 
	@Autowired
	public void setTask( ReadSpecificNoticeTask task) {
		
		p_task = task;
	}
	 
}

 