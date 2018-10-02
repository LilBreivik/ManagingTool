package core.backend.REST.fileasset.download.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.fileasset.download.task.DownloadGeneralCourseScheduleTemplateFileTask; 
import core.backend.REST.general.controller.nonrequest.NonRequestController;

@Controller
public class GeneralCourseScheduleTemplateFileDownloadController 
											extends NonRequestController<HttpServletResponse> {
    
	private DownloadGeneralCourseScheduleTemplateFileTask m_downloadGeneralCourseScheduleTemplateFileTask;
	
	@Autowired
	public GeneralCourseScheduleTemplateFileDownloadController(@Qualifier("provide DownloadGeneralCourseScheduleTemplateFileTask")
																	DownloadGeneralCourseScheduleTemplateFileTask task) {
							super(task);
							
		m_downloadGeneralCourseScheduleTemplateFileTask =  task; 
	}
		 
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Download/General/Schedule/Course/Template", method = RequestMethod.POST )
	protected void handleCourseScheduleFileDownloadRequest(HttpServletResponse fileToDownload) {
		 
		m_downloadGeneralCourseScheduleTemplateFileTask.setHTTPResponse(fileToDownload);
	
		super.handleRequest();
	}

}
