package core.backend.REST.fileasset.delete.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import static core.utils.Constants.UploadFileName.AllLecturesSchedule;

import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import core.provider.FileNameProvider;
import resources.components.elements.POJO.Schedule.CoursePOJO; 

public class DeleteLectureScheduleFileParameter 
									extends RESTScheduleRequest {

	@JsonCreator
	public DeleteLectureScheduleFileParameter(@JsonProperty("course") CoursePOJO  coursePOJO) {
		super(coursePOJO );
		 
		setFileNameResolver(FileNameProvider.provideFileNameResolverForLectureScheduleFile(this));
		
		setTargetFileName(AllLecturesSchedule.toString());
		 
	}
}
