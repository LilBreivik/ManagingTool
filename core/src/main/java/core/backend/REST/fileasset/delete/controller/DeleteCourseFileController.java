package core.backend.REST.fileasset.delete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import core.backend.REST.fileasset.delete.parameter.DeleteCourseFileParameter;
import core.backend.REST.fileasset.delete.parameter.DeleteFileParameter;
import core.backend.REST.fileasset.delete.task.DeleteFileTask;
import core.backend.REST.general.controller.REST.NonResponseController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus;  
import org.springframework.http.HttpStatus;  

@Controller
public class DeleteCourseFileController 
	extends  NonResponseController  < DeleteFileTask , DeleteCourseFileParameter  >{
 
	
	public static final String DeleteCourseFileURL = "/Delete/Schedule/Course";
	
	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = DeleteCourseFileURL, method = RequestMethod.POST, consumes="application/json")
	public void handleRequest ( @RequestBody DeleteCourseFileParameter  restRequest ) {
	
		super.handleRequest( restRequest );
	}

 
	/**
	 * Configuration 
	 * Area 
	 * */
	

	@Override
	@Autowired
	public void setTask(@Qualifier("provide DeleteCourseScheduleTask") DeleteFileTask p_task) {
	 
		super.setTask(p_task);
	}
	
	
	/*@Autowired
	public CourseScheduleDeleteFileController(@Qualifier("provide DeleteCourseScheduleTask") DeleteFileTask deleteFileTask ) {
		super(deleteFileTask); 
	}
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Delete/Schedule/Course", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public void deleteFile(@RequestBody DeleteCourseScheduleFileParameter deleteCourseScheduleFileParam)  
	{
		  super.handleNonResponseRequest(deleteCourseScheduleFileParam);
	}*/
}
