package core.controller.parameter.schedule.synthesized;
  
 
import static core.utils.Constants.UploadFileName.CourseSchedule; 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty; 
import core.controller.parameter.schedule.GenericScheduleFileParam;
import core.controller.parameter.schedule.ScheduleParam;
import core.utils.names.UploadFileNameResolver; 
public class SynthesizedCourseScheduleFileParam
													extends GenericScheduleFileParam  
{
	@JsonCreator
    public SynthesizedCourseScheduleFileParam(@JsonProperty("courseName") String courseName,
											       @JsonProperty("courseDegree") String courseDegree,
											       		@JsonProperty("courseTerm") String  courseTerm) {
		super(courseName, courseDegree, courseTerm);
	
		p_scheduleFileNameResolver = (resolvedScheduleFileName) -> CourseSchedule.toString().concat( resolvedScheduleFileName );
		
		setUploadFileNameResolver(new UploadFileNameResolver((ScheduleParam) this, p_scheduleFileNameResolver));
	}
   
}