package core.backend.REST.fileasset.upload.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import static core.utils.Constants.UploadFileName.AllLecturesSchedule;
import core.provider.FileNameProvider;
import resources.error.ConnectionError;
import resources.error.parameter.FileAssetParameterViolationError;
import resources.fileconnection.XLSFileConnection;

public class UploadLectureScheduleFileParameter 
									extends UploadFileParameter {

	@JsonCreator
	public UploadLectureScheduleFileParameter(
			
			@JsonProperty("courseName") String courseName, 
				@JsonProperty("courseDegree") String courseDegree,
			    	@JsonProperty("courseTerm")	String courseTerm, 
			    		@JsonProperty("scheduleFile")	String scheduleFile
				
			) {
		super(courseName, courseDegree, courseTerm, scheduleFile);
		 
		setFileNameResolver(FileNameProvider.provideFileNameResolverForLectureScheduleFile(this));
		
		setTargetFileName(AllLecturesSchedule.toString());
		
		p_verifierJob = () -> {
			
	    	try {
				
				new  XLSFileConnection(getScheduleFile());
			 
			} catch (ConnectionError e) {
				
				throw new  FileAssetParameterViolationError(this.getScheduleFile(), 
																FileAssetParameterViolationError.FileExtension.XLS );
			}
	       
		};
	}
}
