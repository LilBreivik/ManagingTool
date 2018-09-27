package core.backend.REST.nonfileasset.synthesize.controller;
  
 
import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter;
import core.backend.REST.nonfileasset.synthesize.task.SynthesizedTask;

public class SynthesizedScheduleController< Request extends SynthesizedCourseScheduleFileParameter, ResponseType>
                                                extends MasterRESTController< Request,   ResponseType>  {
 
	public SynthesizedScheduleController(SynthesizedTask task) {
		super(task); 
	}
  
}
