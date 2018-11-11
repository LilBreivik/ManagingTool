package core.backend.REST.fileasset.download.parameter;

import javax.servlet.http.HttpServletResponse;
 
import core.backend.REST.general.request.schedule.file.RESTScheduleFileRequest;
import resources.components.elements.POJO.Course.CoursePOJO; 


public abstract  class DownloadFileParameter 
											
										extends RESTScheduleFileRequest< CoursePOJO >{

	protected HttpServletResponse reponse; 
	
	public DownloadFileParameter() {};
	 
	public DownloadFileParameter(CoursePOJO pojo) {
		super(pojo);
	}
 
	public HttpServletResponse getDownloadResponse() {
		
		return this.reponse;
	}
	
	public void setDownloadResponse(HttpServletResponse reponse) {
		this.reponse = reponse;
	}
}
