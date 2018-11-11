package core.backend.REST.nonfileasset.synthesize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter;
import core.backend.REST.nonfileasset.synthesize.task.SpecificSynthesizedCourseScheduleTask;
import core.backend.REST.nonfileasset.synthesize.task.SynthesizedTask;
import resources.components.elements.POJO.Course.CoursePOJO; 

import org.springframework.http.HttpStatus;

@Controller
public class SpecificSynthesizedCourseScheduleController 
							extends ResponseController< SynthesizedCourseScheduleFileParameter, CoursePOJO, SpecificSynthesizedCourseScheduleTask>{

	
	public static final String SpecificCourseScheduleSynthesizeURL = "/Synthesize/SpecificCourseSchedule";

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = SpecificCourseScheduleSynthesizeURL, method = RequestMethod.POST, consumes="application/json")
	public SuccessResponse<CoursePOJO> handleRequest( @RequestBody SynthesizedCourseScheduleFileParameter synthesizedCourseScheduleFileParameter ) {
 
		return super.handleRequest( synthesizedCourseScheduleFileParameter );
	} 
	
	
	/**
	 * Configuration 
	 * Area 
	 * */
	 

	@Override 
	@Autowired
	public void setTask(SpecificSynthesizedCourseScheduleTask  task) {
		
		p_task = task;
	}
	
	 
}
