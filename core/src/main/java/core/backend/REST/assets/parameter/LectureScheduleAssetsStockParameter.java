package core.backend.REST.assets.parameter;


import static core.utils.Constants.UploadFileName.AllLecturesSchedule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import core.provider.FileNameProvider;
import resources.components.elements.POJO.Schedule.CoursePOJO;
 
public class LectureScheduleAssetsStockParameter 
												extends RESTScheduleRequest{
	@JsonCreator
	public LectureScheduleAssetsStockParameter (@JsonProperty("course") CoursePOJO  coursePOJO ) {
		super( coursePOJO );
		
		setFileNameResolver(FileNameProvider.provideFileNameResolverForLectureScheduleFile(this));
		
		setTargetFileName(AllLecturesSchedule.toString());
		
	}
}
