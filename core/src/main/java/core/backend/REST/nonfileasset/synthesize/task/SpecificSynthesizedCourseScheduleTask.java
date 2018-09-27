package core.backend.REST.nonfileasset.synthesize.task;
 

import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter;
import resources.components.elements.POJO.Schedule.Lectures.CoursePOJO;
import resources.components.filehandler.filesynthesizer.LectureScheduleSynthesizer;

public class SpecificSynthesizedCourseScheduleTask 
							 				extends SynthesizedTask<CoursePOJO> {

	public SpecificSynthesizedCourseScheduleTask(LectureScheduleSynthesizer synthesizer) {
		super( synthesizer); 
	}

	
	@Override
	public void workOnTask(SynthesizedCourseScheduleFileParameter parameter) 
	{	
	    this.response =  (CoursePOJO) p_synthesizer.synthesizeAssets(parameter.getFileNameResolver().getResolvedFileName()); 
	}

}
