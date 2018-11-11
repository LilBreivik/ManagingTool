package core.backend.REST.fileasset.download.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.provider.NameResolverProvider;
import resources.components.elements.POJO.Course.CoursePOJO;

public class DownloadCourseFileParameter 
											extends DownloadFileParameter{

	@JsonCreator
	public DownloadCourseFileParameter(@JsonProperty("course") CoursePOJO pojo) {
		super(pojo);
		
		setFileNameResolver(NameResolverProvider.provideNameResolverForCourseScheduleFile()); 
		
	}

}
