package core.backend.REST.nonfileasset.synthesize.parameter;
 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;  
import core.backend.REST.general.request.schedule.file.RESTScheduleFileRequest;
import core.provider.NameResolverProvider;
import resources.components.elements.POJO.Course.CoursePOJO;
 

public class SynthesizedCourseScheduleFileParameter 
	                                           extends   RESTScheduleFileRequest <CoursePOJO >{
	
	@JsonCreator
	public SynthesizedCourseScheduleFileParameter (@JsonProperty("course") CoursePOJO  coursePOJO) {
							super(coursePOJO);
		 
        setFileNameResolver(NameResolverProvider.provideNameResolverForCourseScheduleFile()); 
	}
}