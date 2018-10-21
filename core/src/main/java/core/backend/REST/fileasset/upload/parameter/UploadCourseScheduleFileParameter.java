package core.backend.REST.fileasset.upload.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty; 
import core.provider.FileNameProvider;
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO;
import resources.error.ConnectionError;
import resources.error.parameter.fileasset.FileAssetParameterViolationError;
import resources.fileconnection.XMLFileConnection;

public class UploadCourseScheduleFileParameter 
											extends  UploadScheduleFileParameter{
 
	@JsonCreator
	public UploadCourseScheduleFileParameter(@JsonProperty("courseScheduleFile") CourseScheduleFilePOJO  courseScheduleFilePOJO) {
		super(courseScheduleFilePOJO);

		setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
		
		setTargetFileName(  getFileNameResolver().getResolvedFileName());
		
	 	
		p_verifierJob = () -> { 
		    
	    	try { 
	    		
				new XMLFileConnection(getScheduleFile().toPath());
			 
			} catch (ConnectionError e) {
				
				throw new  FileAssetParameterViolationError(this.getScheduleFile(), 
																FileAssetParameterViolationError.FileExtension.XML );
			}
	   };
	}

}
