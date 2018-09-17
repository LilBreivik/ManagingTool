package core.controller.parameter.upload;
  
import static core.utils.Constants.UploadFileName.CourseSchedule;  
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.controller.parameter.ScheduleParam;
import core.controller.verifier.CourseVerifier; 
import core.utils.names.UploadFileNameResolver; 
import resources.components.filehandler.ComponentsManufactory;
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler;
import resources.error.ConnectionError;
import resources.error.parameter.FileAssetParameterViolationError; 
import resources.fileconnection.XMLFileConnection; 

public class CourseScheduleFileUploadParam
											extends GenericScheduleFileUploadParam   {
 	
	@JsonCreator
	public CourseScheduleFileUploadParam(@JsonProperty("courseName") String courseName,
											@JsonProperty("courseDegree") String courseDegree,
												@JsonProperty("courseTerm") String  courseTerm, 
													@JsonProperty("scheduleFile") String scheduleFile) { 
		
		super( new CourseVerifier(ComponentsManufactory.createComponent("persistenceCourseScheduleJSONFileHandler", PersistenceCourseScheduleJSONFileHandler.class)), 
						courseName,
							 courseDegree,
								  courseTerm, 
								     scheduleFile);
		 
		
		// Caution... The Uploaded FileName, is the same as the target file name....
	 
		// but the files are in different directories 
		
		 
		
		p_scheduleFileNameResolver 	= (resolvedScheduleFileName) -> CourseSchedule.toString().concat( resolvedScheduleFileName );
		
	 	setUploadFileNameResolver(new UploadFileNameResolver((ScheduleParam) this, p_scheduleFileNameResolver ));
		
	 	setTargetFileName( getUploadedFileName().getResolvedUploadedFileName());
		
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
