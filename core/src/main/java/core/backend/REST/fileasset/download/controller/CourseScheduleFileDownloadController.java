package core.backend.REST.fileasset.download.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 
import core.backend.REST.fileasset.download.parameter.request.DownloadCourseScheduleFileParameter;
import core.backend.REST.fileasset.download.task.DownloadFileTask; 
import javax.servlet.http.HttpServletResponse; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; 

@Controller
public class CourseScheduleFileDownloadController 
		extends DownloadScheduleFileController<  DownloadCourseScheduleFileParameter, HttpServletResponse> {
    
	@Autowired
	public CourseScheduleFileDownloadController(@Qualifier("provide DownloadCourseScheduleFileTask") 
											DownloadFileTask task) {
		super(task);
	}

 
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Download/Schedule/Course", method = RequestMethod.POST, consumes="application/json")
	protected void handleCourseScheduleFileDownloadRequest(@RequestBody DownloadCourseScheduleFileParameter downloadCourseScheduleFileParam, 
			HttpServletResponse fileToDownload) {
	    
		downloadCourseScheduleFileParam.setDownloadResponse(fileToDownload);
		
		super.handleRequest(downloadCourseScheduleFileParam);
	}
	
	 
}
