package core.backend.REST.nonfileasset.synthesize.task;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter;
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.components.filesynthesizer.LectureScheduleSynthesizer;

@Component
public class SpecificSynthesizedCourseScheduleTask 
							 				extends SynthesizedTask<CoursePOJO> {
 
	@Autowired 
	private LectureScheduleSynthesizer m_synthesizer;
	 

	@Override 
	public void workOnTask(SynthesizedCourseScheduleFileParameter param) {
		
		final String resolvedFileName = param.getFileNameResolver().getResolvedFileName();
		 
		this.response =  (CoursePOJO) m_synthesizer.synthesizeAssets(resolvedFileName );
		
	}

}
