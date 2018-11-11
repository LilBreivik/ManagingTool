package core.backend.REST.fileasset.delete.parameter;

import static core.utils.Constants.UploadFileName.AllLecturesSchedule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty; 
import core.provider.NameResolverProvider;
import resources.components.elements.POJO.Course.CoursePOJO;

public class DeleteLectureFileParameter 
												extends DeleteFileParameter{

	@JsonCreator
	public DeleteLectureFileParameter ( @JsonProperty(deleteFileRequestParameter ) CoursePOJO  coursePOJO) {
	    super(coursePOJO);
	 
		setFileNameResolver(NameResolverProvider.provideNameResolverForLectureScheduleFile()); 
				
		setTargetFileName(AllLecturesSchedule.toString());
	}

}
