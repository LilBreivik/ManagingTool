package core.controller.parameter.assets.schedule;

import static core.utils.Constants.UploadFileName.LectureSchedule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.controller.parameter.ScheduleParam;
import core.controller.parameter.assets.AssetsStockParam;
import core.utils.names.UploadFileNameResolver;

public class LectureScheduleAssetsStockParam extends AssetsStockParam{

	@JsonCreator
	public LectureScheduleAssetsStockParam(@JsonProperty("courseName") String courseName,
									         @JsonProperty("courseDegree") String courseDegree,
								        	   @JsonProperty("courseTerm") String  courseTerm) {
		super(courseName, courseDegree, courseTerm);
		this.configureAssetsStock();
	}

	@Override
	public void configureAssetsStock() {
	
		p_scheduleFileNameResolver = (resolvedScheduleFileName) -> LectureSchedule.toString().concat( resolvedScheduleFileName );
		 
		setUploadFileNameResolver(new UploadFileNameResolver((ScheduleParam) this, p_scheduleFileNameResolver));
		  
	}

}
