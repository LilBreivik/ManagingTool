package core.backend.REST.nonfileasset.notice.controller.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.parameter.SpecficNoticeReadRequestParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import notice.PersistenceNoticesPOJO;

@Controller
public class DeleteNoticeController 
										extends MasterRESTController<NoticeTask ,NoticeParameter, PersistenceNoticesPOJO>{
  
	@Autowired 
	public DeleteNoticeController (@Qualifier("provide DeleteNoticeTask") NoticeTask task) {
		super(task); 
	}

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Notice/Delete", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	protected  SuccessResponse<PersistenceNoticesPOJO> handleNoticeAddition(@RequestBody  SpecficNoticeReadRequestParameter deleteNoticeRequest )  
	{
		System.out.println();
		
		return  super.handleRequest( deleteNoticeRequest);
	}
}

