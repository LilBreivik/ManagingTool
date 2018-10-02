package core.backend.REST.fileasset.download.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.provider.FileNameProvider;
import resources.components.elements.POJO.Schedule.CoursePOJO;

public class DownloadCourseLectureFileParameter 
													extends DownloadFileParameter{

	@JsonCreator
	public DownloadCourseLectureFileParameter(@JsonProperty("course") CoursePOJO  coursePOJO) {
		super(coursePOJO);
		 
		setFileNameResolver(FileNameProvider.provideFileNameResolverForLectureScheduleFile(this));
	}

}
