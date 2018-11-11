package core.backend.REST.fileasset.upload.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.provider.NameResolverProvider;
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO; 
import resources.error.ConnectionError;
import resources.error.parameter.fileasset.FileAssetParameterViolationError; 
import resources.fileconnection.XMLFileConnection;
 
public class UploadCourseFileParameter 
											extends  UploadFileParameter<XMLFileConnection> {
 
	@JsonCreator
	public UploadCourseFileParameter(@JsonProperty( uploadFileRequestParameter ) CourseScheduleFilePOJO  courseScheduleFilePOJO) {
		super(courseScheduleFilePOJO);
	  	 
		setFileNameResolver(NameResolverProvider.provideNameResolverForCourseScheduleFile()); 
		
		setTargetFileName(  p_fileNameResolver.getResolvedFileName());
		
		p_verifierJob = () -> { 
		    
	    	try { 
	    		 
	    		p_Connection = new XMLFileConnection();
				
	    		p_Connection.buildConnectionToAFile(getScheduleFile());
	    	 	
			} catch (ConnectionError e) {
				
				throw new  FileAssetParameterViolationError(getScheduleFile(), 
																FileAssetParameterViolationError.FileExtension.XML );
			}
	   };
	}

}
