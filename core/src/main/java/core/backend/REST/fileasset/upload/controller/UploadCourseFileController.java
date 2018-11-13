package core.backend.REST.fileasset.upload.controller;
   
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.fileasset.upload.parameter.UploadCourseFileParameter; 
import core.backend.REST.fileasset.upload.task.UploadCourseScheduleFileTask; 
import core.backend.REST.general.controller.REST.NonResponseController; 


/**
 * Controller that is need to 
 * upload the course schedule files 
 * 
 * */

@Controller  
public class UploadCourseFileController 
	
    extends  NonResponseController< UploadCourseScheduleFileTask,   UploadCourseFileParameter>{

	public static final String UploadCourseFileURL = "/Upload/Schedule/Course";

	 
	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = UploadCourseFileURL, method = RequestMethod.POST, consumes="application/json")
	public void handleRequest( @RequestBody UploadCourseFileParameter  uploadCourseFileParam ) {
	      
		
		 super.handleRequest( uploadCourseFileParam );
	}
	
 
	/**
	 * Configuration 
	 * Area 
	 * */
	
	 
	@Override 
	@Autowired
	public void setTask( UploadCourseScheduleFileTask  task) {
			
		p_task = task;
	}
	
}
