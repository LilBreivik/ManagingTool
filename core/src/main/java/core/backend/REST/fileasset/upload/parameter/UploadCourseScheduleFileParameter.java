package core.backend.REST.fileasset.upload.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.provider.FileNameProvider;
import resources.error.ConnectionError;
import resources.error.parameter.FileAssetParameterViolationError;
import resources.fileconnection.XMLFileConnection;

public class UploadCourseScheduleFileParameter 
											extends UploadFileParameter {

	@JsonCreator
	public UploadCourseScheduleFileParameter(@JsonProperty("courseName") String courseName, 
												@JsonProperty("courseDegree") String courseDegree,
											    	@JsonProperty("courseTerm")	String courseTerm, 
											    		@JsonProperty("scheduleFile")	String scheduleFile) {
		super(courseName, courseDegree, courseTerm, scheduleFile);
		
		
		setFileNameResolver(FileNameProvider.provideFileNameResolverForCourseScheduleFile(this));
		
		setTargetFileName(  getFileNameResolver().getResolvedFileName());
		
	 	
		p_verifierJob = () -> { 
		    
	    	try { 
	    		
				new XMLFileConnection(getScheduleFile());
			 
			} catch (ConnectionError e) {
				
				throw new  FileAssetParameterViolationError(this.getScheduleFile(), 
																FileAssetParameterViolationError.FileExtension.XML );
			}
	    
	};
	}

}
