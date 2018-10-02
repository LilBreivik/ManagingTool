package core.backend.REST.fileasset.upload.controller;
   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.fileasset.upload.parameter.UploadCourseScheduleFileParameter;
import core.backend.REST.fileasset.upload.task.UploadFileTask;
import core.backend.REST.general.controller.MasterRESTController; 

@Controller 
public class CourseScheduleUploadFileController 
											extends   MasterRESTController< UploadCourseScheduleFileParameter  , String>{

	@Autowired
	public CourseScheduleUploadFileController(@Qualifier("provide UploadCourseScheduleFileTask") UploadFileTask task) {
		super(task); 
	}

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Upload/Schedule/Course", method = RequestMethod.POST, consumes="application/json")
	protected void handleCourseScheduleFileDownloadRequest(
			@RequestBody UploadCourseScheduleFileParameter  uploadCourseScheduleFileParam ) {
	     
		 super.handleRequest( uploadCourseScheduleFileParam );
	}
	
}
