package core.backend.REST.fileasset.delete.parameter.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import static core.utils.Constants.UploadFileName.AllLecturesSchedule;

import core.provider.FileNameProvider; 

public class DeleteLectureScheduleFileParameter 
									extends DeleteFileParameter {

	@JsonCreator
	public DeleteLectureScheduleFileParameter(
			
			@JsonProperty("courseName") String courseName, 
				@JsonProperty("courseDegree") String courseDegree,
			    	@JsonProperty("courseTerm")	String courseTerm 
				
			) {
		super(courseName, courseDegree, courseTerm );
		 
		setFileNameResolver(FileNameProvider.provideFileNameResolverForLectureScheduleFile(this));
		
		setTargetFileName(AllLecturesSchedule.toString());
		 
	}
}
