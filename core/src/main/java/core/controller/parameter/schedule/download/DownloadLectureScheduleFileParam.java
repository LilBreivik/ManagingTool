package core.controller.parameter.schedule.download;
  
import static core.utils.Constants.UploadFileName.LectureSchedule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.controller.parameter.schedule.GenericScheduleFileParam;
import core.controller.parameter.schedule.ScheduleParam;
import core.controller.parameter.schedule.synthesized.SynthesizedCourseScheduleFileParam;
import core.utils.names.UploadFileNameResolver;  

public class  DownloadLectureScheduleFileParam 
											extends  GenericScheduleFileParam 
{

	@JsonCreator
	public DownloadLectureScheduleFileParam(@JsonProperty("courseName") String courseName,
										       @JsonProperty("courseDegree") String courseDegree,
									        	   @JsonProperty("courseTerm") String  courseTerm) {
		
		super(courseName, courseDegree, courseTerm);
		
		p_scheduleFileNameResolver = (resolvedScheduleFileName) -> LectureSchedule.toString().concat( resolvedScheduleFileName );
		
		setUploadFileNameResolver(new UploadFileNameResolver((ScheduleParam) this, p_scheduleFileNameResolver ));
		
		
	}
    
   
}

 							    
 
   
