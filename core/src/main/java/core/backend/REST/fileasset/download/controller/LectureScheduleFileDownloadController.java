package core.backend.REST.fileasset.download.controller;

import javax.servlet.http.HttpServletResponse;
import core.backend.REST.fileasset.download.parameter.request.DownloadCourseLectureFileParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.fileasset.download.task.DownloadFileTask;
import core.backend.REST.general.response.result.successfully.SuccessResponse;

@Controller
public class LectureScheduleFileDownloadController 
        extends DownloadScheduleFileController<  DownloadCourseLectureFileParameter, HttpServletResponse>{

	@Autowired
	public LectureScheduleFileDownloadController(@Qualifier("provide DownloadLectureScheduleFileTask") 
														DownloadFileTask task) {
		super(task); 
	}

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Download/Schedule/Lecture", method = RequestMethod.POST, consumes="application/json")
	protected SuccessResponse<HttpServletResponse> handleCourseScheduleFileDownloadRequest(@RequestBody DownloadCourseLectureFileParameter  downloadLectureScheduleFileParam, 
			HttpServletResponse fileToDownload) {
	 
		downloadLectureScheduleFileParam.setDownloadResponse(fileToDownload);
		 
		return super.handleRequest(downloadLectureScheduleFileParam);
	}
	
}
