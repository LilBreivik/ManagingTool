package core.backend.REST.fileasset.upload.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.fileasset.upload.parameter.UploadLectureFileParameter; 
import core.backend.REST.fileasset.upload.task.UploadLectureScheduleFileTask;
import core.backend.REST.general.controller.REST.NonResponseController; 

@Controller 
public class UploadLectureFileController 

       extends NonResponseController<  UploadLectureScheduleFileTask , UploadLectureFileParameter  > {
  
	public static final String UploadLectureFileURL = "/Upload/Schedule/Lecture";
	
	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = UploadLectureFileURL, method = RequestMethod.POST, consumes="application/json")
	public void handleRequest ( @RequestBody UploadLectureFileParameter uploadCourseScheduleFileParam ) {
	
		super.handleRequest( uploadCourseScheduleFileParam );
	}

 
	/**
	 * Configuration 
	 * Area 
	 * */
	 
	
	@Override   
	@Autowired
	public void setTask( UploadLectureScheduleFileTask  task) {
			
		p_task = task;
	}
	
}
 