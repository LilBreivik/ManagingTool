package core.controller.parameter.delete;

import static core.utils.Constants.UploadFileName.AllLecturesSchedule;
import static core.utils.Constants.UploadFileName.LectureSchedule; 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.controller.parameter.GenericScheduleFileParam;
import core.controller.parameter.ScheduleParam;
import core.utils.names.UploadFileNameResolver;

public class DeleteLectureFileParam 
									extends  GenericScheduleFileParam 
{

	@JsonCreator
	public DeleteLectureFileParam (@JsonProperty("courseName") String courseName,
								       @JsonProperty("courseDegree") String courseDegree,
								        	@JsonProperty("courseTerm") String  courseTerm) { 
								
			super(  courseName, courseDegree,  courseTerm);
	
			p_scheduleFileNameResolver = (resolvedScheduleFileName) -> LectureSchedule.toString().concat( resolvedScheduleFileName );
			
			 
			setUploadFileNameResolver(new UploadFileNameResolver((ScheduleParam) this, p_scheduleFileNameResolver));
			
			setTargetFileName(AllLecturesSchedule.toString());
	
	
	}

}

