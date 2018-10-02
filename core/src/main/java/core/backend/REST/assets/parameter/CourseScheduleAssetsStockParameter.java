package core.backend.REST.assets.parameter;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import core.provider.FileNameProvider;
import resources.components.elements.POJO.Schedule.CoursePOJO;
 
public class CourseScheduleAssetsStockParameter 
												extends RESTScheduleRequest{
	@JsonCreator
	public CourseScheduleAssetsStockParameter (@JsonProperty("course") CoursePOJO  coursePOJO ) {
		super( coursePOJO );
		
        setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
		
		setTargetFileName(  getFileNameResolver().getResolvedFileName());
	}
}
