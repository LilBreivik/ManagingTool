package core.backend.REST.fileasset.download.controller;
 
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.fileasset.download.parameter.DownloadLectureFileParameter;
import core.backend.REST.fileasset.download.task.DownloadFileTask; 
import core.backend.REST.general.controller.REST.NonResponseController;  

@Controller
public class DownloadLectureFileController
							extends  NonResponseController< DownloadFileTask,  DownloadLectureFileParameter  >{

	 
	public static final String DownloadLectureFileURL = "/Download/Schedule/Lecture";
	

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = DownloadLectureFileURL, method = RequestMethod.POST, consumes="application/json")
	public void handleRequest( HttpServletResponse response,
			@RequestBody  DownloadLectureFileParameter  restRequest) {
		
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
