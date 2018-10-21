package core.backend.REST.fileasset.delete.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import core.provider.FileNameProvider;
import resources.components.elements.POJO.Course.CoursePOJO; 

public class DeleteCourseScheduleFileParameter 
											extends RESTScheduleRequest{

	@JsonCreator
	public DeleteCourseScheduleFileParameter(@JsonProperty("course") CoursePOJO  coursePOJO) {
		super( coursePOJO );
		 
		setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
		
		setTargetFileName(  getFileNameResolver().getResolvedFileName());
		
		 
	}

}
