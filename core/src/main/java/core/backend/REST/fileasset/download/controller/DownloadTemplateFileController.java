package core.backend.REST.fileasset.download.controller;
  
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.fileasset.download.parameter.DownloadTemplateFileParameter;
import core.backend.REST.fileasset.download.task.DownloadFileTask;
import core.backend.REST.general.controller.REST.NonResponseController; 
 
@Controller
public class  DownloadTemplateFileController
                            extends  NonResponseController< DownloadFileTask,  DownloadTemplateFileParameter > {
    
	 
  public static final String DownloadTemplateFileURL = "/Download/General/Schedule/Course/Template";
		

  @ResponseStatus( HttpStatus.OK )
  @RequestMapping(value = DownloadTemplateFileURL, method = RequestMethod.POST )
  public void handleRequest(HttpServletResponse response) {
		
	  DownloadTemplateFileParameter restRequest = new DownloadTemplateFileParameter();
	   
	  
	  restRequest.setDownloadResponse(response);
		
	  super.handleRequest( restRequest);	 
  }
   
  /**
	 * Configuration 
	 * Area 
	 * */
	 
	
	@Override 
	@Autowired
	public void setTask(@Qualifier("provide DownloadGeneralCourseScheduleTemplateFileTask")  DownloadFileTask task) {
			
		p_task = task;
	}
  
} 