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

import core.backend.REST.fileasset.download.parameter.DownloadCourseScheduleFileParameter;
import core.backend.REST.fileasset.download.task.DownloadFileTask; 
import core.backend.REST.general.controller.nonresponse.NonResponseController;
 
@Controller
public class GeneralCourseScheduleTemplateFileDownloadController 
                            extends  NonResponseController< DownloadFileTask,  DownloadCourseScheduleFileParameter > {
    
  @Autowired
  public GeneralCourseScheduleTemplateFileDownloadController (
		  @Qualifier("provide DownloadGeneralCourseScheduleTemplateFileTask")  DownloadFileTask task) {
			super(task);
			
  }


  @ResponseStatus( HttpStatus.OK )
  @RequestMapping(value = "/Download/General/Schedule/Course/Template", method = RequestMethod.POST, consumes="application/json")

  protected void handleCourseScheduleFileDownloadRequest(@RequestBody DownloadCourseScheduleFileParameter downloadCourseScheduleFileParam, 
          		HttpServletResponse fileToDownload) {
	
	downloadCourseScheduleFileParam.setDownloadResponse(fileToDownload);
	
	super.handleRequest(downloadCourseScheduleFileParam);
	
  }


} 