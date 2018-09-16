package core.controller.parameter.schedule.upload;
   

import java.io.File; 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import static core.utils.Constants.UploadFileName.LectureSchedule;
import static core.utils.Constants.UploadFileName.AllLecturesSchedule;
import core.controller.parameter.schedule.ScheduleParam;
import core.controller.verifier.CourseVerifier; 
import core.utils.names.UploadFileNameResolver; 
import resources.components.filehandler.ComponentsManufactory;
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler;
import resources.error.ConnectionError;
import resources.error.parameter.FileAssetParameterViolationError; 
import resources.fileconnection.XLSFileConnection;

public class LectureScheduleFileUploadParam
											extends GenericScheduleFileUploadParam   {
	 

	 
	@JsonCreator
	public LectureScheduleFileUploadParam( @JsonProperty("courseName") String courseName,
												@JsonProperty("courseDegree") String courseDegree,
													@JsonProperty("courseTerm") String  courseTerm, 
														@JsonProperty("scheduleFile") String scheduleFile) {
			

		super( new CourseVerifier(ComponentsManufactory.createComponent("persistenceCourseScheduleJSONFileHandler", PersistenceCourseScheduleJSONFileHandler.class)), 
					courseName,
						 courseDegree,
							courseTerm, 
								scheduleFile);
															
															
															
		// Caution: The finally processed files will be stored in AllLecturesSchedule.
		// The single assets got their own names 
		
		p_scheduleFileNameResolver = (resolvedScheduleFileName) -> LectureSchedule.toString().concat( resolvedScheduleFileName );
		
		setUploadFileNameResolver(new UploadFileNameResolver((ScheduleParam) this, p_scheduleFileNameResolver));
 	
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
