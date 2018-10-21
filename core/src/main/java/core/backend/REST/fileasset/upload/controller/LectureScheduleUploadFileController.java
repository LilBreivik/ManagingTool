package core.backend.REST.fileasset.upload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.fileasset.upload.parameter.UploadLectureScheduleFileParameter;
import core.backend.REST.fileasset.upload.task.UploadFileTask; 
import core.backend.REST.general.controller.nonresponse.NonResponseController; 

@Controller 
public class LectureScheduleUploadFileController 
       extends  NonResponseController< UploadFileTask, UploadLectureScheduleFileParameter  > {

	@Autowired 
	public  LectureScheduleUploadFileController(@Qualifier("provide UploadLectureScheduleFileTask") UploadFileTask task) {
		super(task); 
	}
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Upload/Schedule/Lecture", method = RequestMethod.POST, consumes="application/json")
	protected void handleLectureScheduleFileDownloadRequest(
	@RequestBody UploadLectureScheduleFileParameter  uploadCourseScheduleFileParam ) {
	
		super.handleRequest( uploadCourseScheduleFileParam );
	}

}
 