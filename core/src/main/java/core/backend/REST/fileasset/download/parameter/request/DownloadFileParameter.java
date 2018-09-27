package core.backend.REST.fileasset.download.parameter.request;

 
import javax.servlet.http.HttpServletResponse;

import core.backend.REST.general.request.MasterRESTRequest;
 
public abstract class DownloadFileParameter 
										extends MasterRESTRequest{
	
	private HttpServletResponse reponse; 
	
	public DownloadFileParameter(String courseName, String courseDegree, String courseTerm) {
		super(courseName, courseDegree, courseTerm);
		 
	}

	public HttpServletResponse getDownloadResponse() {
	
		return this.reponse;
	}
	
	public void setDownloadResponse(HttpServletResponse reponse) {
		this.reponse = reponse;
	}
}
