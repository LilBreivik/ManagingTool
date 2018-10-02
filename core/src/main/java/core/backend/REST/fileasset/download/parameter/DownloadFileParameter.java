package core.backend.REST.fileasset.download.parameter;

import javax.servlet.http.HttpServletResponse;

import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import resources.components.elements.POJO.Schedule.CoursePOJO;


public abstract class DownloadFileParameter 
												extends RESTScheduleRequest{


	protected HttpServletResponse reponse; 
	
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
