package core.backend.REST.fileasset.download.parameter;
 

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.provider.FileNameProvider;
import resources.components.elements.POJO.Course.CoursePOJO;

public class DownloadCourseScheduleFileParameter 
						 							extends DownloadFileParameter{

	@JsonCreator
	public DownloadCourseScheduleFileParameter(@JsonProperty("course") CoursePOJO  coursePOJO) {
		super(coursePOJO);
	
		setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
	}

	 

}
