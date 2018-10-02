package core.backend.REST.fileasset.delete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import core.backend.REST.fileasset.delete.parameter.DeleteCourseScheduleFileParameter;
import core.backend.REST.fileasset.delete.task.DeleteFileTask;
import core.backend.REST.general.controller.MasterRESTController; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus;  
import org.springframework.http.HttpStatus;  

@Controller
public class CourseScheduleDeleteFileController 
										extends  MasterRESTController < DeleteCourseScheduleFileParameter , String>{

	@Autowired
	public CourseScheduleDeleteFileController(@Qualifier("provide DeleteCourseScheduleTask") DeleteFileTask deleteFileTask ) {
		super(deleteFileTask); 
	}
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Delete/Schedule/Course", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public void deleteFile(@RequestBody DeleteCourseScheduleFileParameter deleteCourseScheduleFileParam)  
	{
		  super.handleRequest(  deleteCourseScheduleFileParam);
	}
}
