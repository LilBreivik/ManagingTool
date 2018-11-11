package core.backend.REST.fileasset.delete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import core.backend.REST.fileasset.delete.parameter.DeleteFileParameter;
import core.backend.REST.fileasset.delete.parameter.DeleteLectureFileParameter;
import core.backend.REST.fileasset.delete.task.DeleteFileTask; 
import core.backend.REST.general.controller.REST.NonResponseController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus;  
import org.springframework.http.HttpStatus;  

@Controller
public class DeleteLectureFileController 
   extends  NonResponseController  < DeleteFileTask, DeleteLectureFileParameter >{

	
	public static final String DeleteLectureFileURL = "/Delete/Schedule/Lecture";
	
	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value =DeleteLectureFileURL, method = RequestMethod.POST, consumes="application/json")
	public void handleRequest ( @RequestBody DeleteLectureFileParameter  restRequest ) {
	 
		super.handleRequest( restRequest );
	}

 
	/**
	 * Configuration 
	 * Area 
	 * */ 
	

	@Override
	@Autowired
	public void setTask(@Qualifier("provide DeleteLectureScheduleTask") DeleteFileTask  task) {
	
		p_task = task;
	}
 
}
