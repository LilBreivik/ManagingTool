package core.backend.REST.fileasset.download.parameter.request;
 

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.provider.FileNameProvider;

public class DownloadCourseScheduleFileParameter 
													extends DownloadFileParameter{

	@JsonCreator
	public DownloadCourseScheduleFileParameter(@JsonProperty("courseName") String courseName, 
													@JsonProperty("courseDegree") String courseDegree,
														@JsonProperty("courseTerm")	String courseTerm) {
		super(courseName, courseDegree, courseTerm);
	
		setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
	}

	 

}
