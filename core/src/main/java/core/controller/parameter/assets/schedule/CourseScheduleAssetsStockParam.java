package core.controller.parameter.assets.schedule;

import static core.utils.Constants.UploadFileName.CourseSchedule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.controller.parameter.ScheduleParam;
import core.controller.parameter.assets.AssetsStockParam;
import core.utils.names.UploadFileNameResolver;

public class CourseScheduleAssetsStockParam extends AssetsStockParam{

	@JsonCreator
	public CourseScheduleAssetsStockParam(@JsonProperty("courseName") String courseName,
									         @JsonProperty("courseDegree") String courseDegree,
								        	   @JsonProperty("courseTerm") String  courseTerm) {
		super(courseName, courseDegree, courseTerm);
		this.configureAssetsStock();
	}

	@Override
	public void configureAssetsStock() {
	
		p_scheduleFileNameResolver = (resolvedScheduleFileName) -> CourseSchedule.toString().concat( resolvedScheduleFileName );
		 
		setUploadFileNameResolver(new UploadFileNameResolver((ScheduleParam) this, p_scheduleFileNameResolver));
		  
	}

}
