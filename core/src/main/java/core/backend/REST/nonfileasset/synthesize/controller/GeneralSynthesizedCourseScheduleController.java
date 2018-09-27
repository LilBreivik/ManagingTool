package core.backend.REST.nonfileasset.synthesize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter;
import core.backend.REST.nonfileasset.synthesize.task.SynthesizedTask;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;

@Controller
public class GeneralSynthesizedCourseScheduleController 
							extends SynthesizedScheduleController<SynthesizedCourseScheduleFileParameter,PersistenceCourseSchedulePOJO >{

	@Autowired
	public GeneralSynthesizedCourseScheduleController(@Qualifier("provide GeneralSynthesizedCourseScheduleTask") SynthesizedTask task) {
		super(task);
	 
	}

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Synthesize/GeneralCourseSchedule", method = RequestMethod.GET, produces="application/json")
	protected SuccessResponse<PersistenceCourseSchedulePOJO> handleRequest( ) {
 
		return super.handleRequest();
	}
}
