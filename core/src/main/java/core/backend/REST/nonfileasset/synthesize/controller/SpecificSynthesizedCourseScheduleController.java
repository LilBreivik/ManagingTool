package core.backend.REST.nonfileasset.synthesize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter; 
import core.backend.REST.nonfileasset.synthesize.task.SynthesizedTask;
import resources.components.elements.POJO.Course.CoursePOJO;

import org.springframework.http.HttpStatus;

@Controller
public class SpecificSynthesizedCourseScheduleController 
							extends MasterRESTController<SynthesizedTask, SynthesizedCourseScheduleFileParameter, CoursePOJO>{

	@Autowired
	public SpecificSynthesizedCourseScheduleController(@Qualifier("provide SpecificSynthesizedCourseScheduleTask") SynthesizedTask task) {
		super(task);
	 
	}

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Synthesize/SpecificCourseSchedule", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	protected SuccessResponse<CoursePOJO> handleRequest(
			@RequestBody SynthesizedCourseScheduleFileParameter restRequest) {
  
		
		return super.handleRequest(restRequest);
	}
}
