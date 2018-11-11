package core.backend.REST.fileasset.upload.parameter;

import static core.utils.Constants.UploadFileName.AllLecturesSchedule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.provider.NameResolverProvider;
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO; 
import resources.error.ConnectionError;
import resources.error.parameter.fileasset.FileAssetParameterViolationError;
import resources.fileconnection.XLSFileConnection; 

public class UploadLectureFileParameter 
	 											extends  UploadFileParameter<XLSFileConnection>  {

	@JsonCreator
	public UploadLectureFileParameter(
			@JsonProperty( uploadFileRequestParameter ) CourseScheduleFilePOJO  courseScheduleFilePOJO
		) {
		super(courseScheduleFilePOJO); 
		 
		 
		setFileNameResolver(NameResolverProvider.provideNameResolverForLectureScheduleFile()); 
		
		setTargetFileName(AllLecturesSchedule.toString());
		
		p_verifierJob = () -> {
			
	    	try {
	
	    		p_Connection = new XLSFileConnection();
				
	    		p_Connection.buildConnectionToAFile(getScheduleFile());
			 
			} catch (ConnectionError e) {
				
				throw new  FileAssetParameterViolationError(getScheduleFile(), 
																FileAssetParameterViolationError.FileExtension.XLS );
			}
	       
		};
	}
}
