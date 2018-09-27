package core.backend.REST.nonfileasset.synthesize.parameter;
 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.backend.REST.general.request.MasterRESTRequest;
import core.provider.FileNameProvider;
 

public class SynthesizedCourseScheduleFileParameter 
	                                           extends MasterRESTRequest  
	{
		@JsonCreator
		public SynthesizedCourseScheduleFileParameter (@JsonProperty("courseName") String courseName,
												        	@JsonProperty("courseDegree") String courseDegree,
													         	@JsonProperty("courseTerm") String  courseTerm) {
		super(courseName, courseDegree, courseTerm);
		
		setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
	}

}