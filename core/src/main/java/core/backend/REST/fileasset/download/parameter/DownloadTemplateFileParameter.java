package core.backend.REST.fileasset.download.parameter;
 
 
import core.utils.names.CourseScheduleTemplateFileNameResolver;
import resources.error.parameter.ParameterViolationError; 


public class DownloadTemplateFileParameter 
												extends DownloadFileParameter{
	
	public DownloadTemplateFileParameter() {
		
	  p_fileNameResolver = new CourseScheduleTemplateFileNameResolver(); 
	
	} 
	
	@Override
	public void verifyParameter() throws ParameterViolationError {
	
		// Parameter does not need to be verified , cause it does not get any 
		// information from client, it is reated by the backend itself 
	}
}
