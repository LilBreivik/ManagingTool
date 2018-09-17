package core.controller.parameter.delete;
  
    
import static core.utils.Constants.UploadFileName.AllLecturesSchedule;
import static core.utils.Constants.UploadFileName.CourseSchedule; 

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.controller.parameter.GenericScheduleFileParam;
import core.controller.parameter.ScheduleParam;
import core.utils.names.UploadFileNameResolver; 

public class DeleteScheduleFileParam  
											extends  GenericScheduleFileParam 
{
   
    @JsonCreator
	public DeleteScheduleFileParam(@JsonProperty("courseName") String courseName,
								     @JsonProperty("courseDegree") String courseDegree,
									   @JsonProperty("courseTerm") String  courseTerm) { 
    	
		super(  courseName,
					courseDegree,  
	   		 	       courseTerm);
	  
		p_scheduleFileNameResolver = (resolvedScheduleFileName) -> CourseSchedule.toString().concat( resolvedScheduleFileName );
		
		 
		setUploadFileNameResolver(new UploadFileNameResolver((ScheduleParam) this, p_scheduleFileNameResolver));
		
		
		setTargetFileName(AllLecturesSchedule.toString());
		 
	}
   
}

 							    
 
   
