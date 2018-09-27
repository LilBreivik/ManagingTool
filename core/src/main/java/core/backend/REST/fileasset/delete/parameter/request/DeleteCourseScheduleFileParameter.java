package core.backend.REST.fileasset.delete.parameter.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.provider.FileNameProvider; 

public class DeleteCourseScheduleFileParameter 
											extends DeleteFileParameter {

	@JsonCreator
	public DeleteCourseScheduleFileParameter(@JsonProperty("courseName") String courseName, 
												@JsonProperty("courseDegree") String courseDegree,
											    	@JsonProperty("courseTerm")	String courseTerm ) {
		super(courseName, courseDegree, courseTerm );
		
		
		setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
		
		setTargetFileName(  getFileNameResolver().getResolvedFileName());
		
		 
	}

}
