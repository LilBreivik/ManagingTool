package core.backend.REST.nonfileasset.synthesize.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter;
import core.backend.REST.nonfileasset.synthesize.task.GeneralSynthesizedCourseScheduleTask; 
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;

@Controller
public class GeneralSynthesizedCourseScheduleController 
							extends ResponseController<SynthesizedCourseScheduleFileParameter, 
																PersistenceCourseSchedulePOJO , 
																	GeneralSynthesizedCourseScheduleTask >{

 
	public static final String GeneralCourseScheduleSynthesizeURL = "/Synthesize/GeneralCourseSchedule";
 
	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = GeneralCourseScheduleSynthesizeURL, method = RequestMethod.GET)
	public SuccessResponse<PersistenceCourseSchedulePOJO> handleRequest(  ) {
 
		return super.handleRequest(  );
	}
	
	
	/**
	 * Configuration 
	 * Area 
	 * */
	

	@Override 
	@Autowired
	public void setTask(GeneralSynthesizedCourseScheduleTask  task) {
		
		p_task = task;
	}
	 
}
