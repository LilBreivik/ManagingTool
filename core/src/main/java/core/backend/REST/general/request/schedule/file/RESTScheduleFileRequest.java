package core.backend.REST.general.request.schedule.file;

import core.backend.REST.general.request.schedule.RESTCustomScheduleRequest;
import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.utils.names.INameResolver;

/**
 * Class that 
 * 
 * describes a common Rest Request Object 
 * 
 * RESTRequest<Request>
 * 
 * Request = Requesttype of Object 
 * */

public class RESTScheduleFileRequest <ScheduleFileRequest extends CoursePOJO>
														extends RESTCustomScheduleRequest<ScheduleFileRequest>{

	protected  FileNameResolver p_fileNameResolver; 
	
	protected  String p_targetFileName; 
	
	public RESTScheduleFileRequest() {};
 
	public RESTScheduleFileRequest(ScheduleFileRequest pojo) {
		super(pojo);		 
	}
 
	
	
	/**
	 * Setter and Getter 
	 * Area
	 * */
	
	
	public FileNameResolver getFileNameResolver() {
	 	
		return p_fileNameResolver; 
	}
 
	public void setFileNameResolver(INameResolver resolver) {
	 	
		p_fileNameResolver = new FileNameResolver( getRequest(),  resolver);
	}

	public String getTargetFileName() {

		return p_targetFileName;			
	}
  	
	public void setTargetFileName(String targetFileName) {
		
		p_targetFileName = targetFileName; 
	}
	
}
