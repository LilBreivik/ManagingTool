package core.backend.REST.fileasset.download.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import core.backend.REST.fileasset.download.parameter.DownloadCourseFileParameter; 
import core.backend.REST.fileasset.download.task.DownloadFileTask; 
import core.backend.REST.general.controller.REST.NonResponseController; 
import javax.servlet.http.HttpServletResponse;   
 
@Controller
public class DownloadCourseFileController 
						extends  NonResponseController< DownloadFileTask,   DownloadCourseFileParameter  >{
    
	public static final String DownloadCourseFileURL = "/Download/Schedule/Course";
	

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = DownloadCourseFileURL, method = RequestMethod.POST, consumes="application/json")
	public void handleRequest( HttpServletResponse response,
			@RequestBody  DownloadCourseFileParameter  restRequest) {
		
		 restRequest.setDownloadResponse(response);
			
		 super.handleRequest( restRequest);
	}

 
	/**
	 * Configuration 
	 * Area 
	 * */
	
	
	@Override 
	@Autowired
	public void setTask(@Qualifier("provide DownloadCourseScheduleFileTask")  DownloadFileTask task) {
			
		p_task = task;
	}
	  
}
 