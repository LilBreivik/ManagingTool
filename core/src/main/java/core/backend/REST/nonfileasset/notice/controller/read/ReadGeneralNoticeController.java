package core.backend.REST.nonfileasset.notice.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter; 
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import resources.components.elements.POJO.Notice.PersistenceNoticesPOJO;

/**
 * @FIXME: we have to keep in ming, that 
 * we need to provide a way to read the same notices if degree and startsemester 
 * are the same
 * 
 * */


@Controller
public class ReadGeneralNoticeController extends MasterRESTController<NoticeParameter, PersistenceNoticesPOJO>{
	 
		@Autowired 
		public ReadGeneralNoticeController(@Qualifier("provide ReadGeneralNoticeTask") NoticeTask  task) {
			super(task); 
		}

		@ResponseStatus( HttpStatus.OK )
		@RequestMapping(value = "/Notice/Read/General", method = RequestMethod.POST, consumes="application/json", produces="application/json")
		protected  SuccessResponse<PersistenceNoticesPOJO> handleNoticeAddition()  
		{ 
			
			return super.handleResponse();
		}
	}

