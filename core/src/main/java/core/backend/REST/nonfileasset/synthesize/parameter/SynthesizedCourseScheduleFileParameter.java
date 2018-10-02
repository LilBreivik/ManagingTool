package core.backend.REST.nonfileasset.synthesize.parameter;
 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import core.provider.FileNameProvider;
import resources.components.elements.POJO.Schedule.CoursePOJO;
 

public class SynthesizedCourseScheduleFileParameter 
	                                           extends  RESTScheduleRequest 
	{
		@JsonCreator
		public SynthesizedCourseScheduleFileParameter (@JsonProperty("course") CoursePOJO  coursePOJO) {
							super(coursePOJO);
		
		setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
	}

}