package core.backend.REST.assets.parameter.request;


import static core.utils.Constants.UploadFileName.AllLecturesSchedule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.provider.FileNameProvider;
 
public class LectureScheduleAssetsStockParameter 
												extends AssetsStockParameter{

	@JsonCreator
	public LectureScheduleAssetsStockParameter (@JsonProperty("courseName") String courseName, 
													@JsonProperty("courseDegree") String courseDegree,
														@JsonProperty("courseTerm")	String courseTerm ) {
		super(courseName, courseDegree, courseTerm);
		
		setFileNameResolver(FileNameProvider.provideFileNameResolverForLectureScheduleFile(this));
		
		setTargetFileName(AllLecturesSchedule.toString());
		
	}
}
