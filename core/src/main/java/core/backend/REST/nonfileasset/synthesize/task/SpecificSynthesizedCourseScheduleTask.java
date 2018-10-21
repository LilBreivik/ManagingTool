package core.backend.REST.nonfileasset.synthesize.task;
 

import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter;
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.components.filesynthesizer.LectureScheduleSynthesizer;

public class SpecificSynthesizedCourseScheduleTask 
							 				extends SynthesizedTask<CoursePOJO> {
 
	private LectureScheduleSynthesizer m_synthesizer;
	 
	
	public SpecificSynthesizedCourseScheduleTask(LectureScheduleSynthesizer synthesizer) {
		
		m_synthesizer = synthesizer;
	}
 
	@Override
	public void workOnTask(SynthesizedCourseScheduleFileParameter parameter) 
	{	
	    this.response =  (CoursePOJO) m_synthesizer.synthesizeAssets(parameter.getFileNameResolver().getResolvedFileName()); 
	}

}
