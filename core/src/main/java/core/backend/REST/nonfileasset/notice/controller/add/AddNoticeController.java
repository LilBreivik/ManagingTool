package core.backend.REST.nonfileasset.notice.controller.add;

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
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import resources.components.elements.POJO.Notice.PersistenceNoticesPOJO;
 
@Controller
public class AddNoticeController 
									extends MasterRESTController<NoticeParameter, PersistenceNoticesPOJO>{
	@Autowired 
	public AddNoticeController(@Qualifier("provide AddNoticeTask") NoticeTask  task) {
		super(task); 
	}

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Notice/Add", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	protected  SuccessResponse<PersistenceNoticesPOJO> handleNoticeAddition(@RequestBody  NoticeParameter addNoticeRequest )  
	{
		 
		return  super.handleRequest(addNoticeRequest );
	}
}
