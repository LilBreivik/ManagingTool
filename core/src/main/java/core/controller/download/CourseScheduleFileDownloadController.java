package core.controller.download;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus;

import core.controller.parameter.download.DownloadCourseScheduleFileParam;

import javax.servlet.http.HttpServletResponse; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;  
import resources.components.filehandler.XML.LectureScheduleXMLFileHandler; 

import static resources.error.parameter.FileAssetParameterViolationError.FileExtension.XML;

@Controller
public class CourseScheduleFileDownloadController 
												extends DownloadScheduleFileController {
   
	@Autowired
	public   CourseScheduleFileDownloadController  ( LectureScheduleXMLFileHandler xmlFileHandler) {
		
		super(xmlFileHandler, (SourceFile, Response) ->  {
			
			 Response.setContentType("application/xml");
			  
			 Response.setHeader("Content-Disposition", "attachment; filename=" + ( new StringBuilder( "\"" + SourceFile.getName().concat(XML.toString()) + "\"")).toString() );
			
		});
	}

	  
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Download/Schedule/Course", method = RequestMethod.POST, consumes="application/json")
	public void downnloadFile(@RequestBody  DownloadCourseScheduleFileParam    downloadCourseScheduleFileParam, 
																HttpServletResponse fileToDownload)  
	{
		downloadCourseScheduleFileParam.verifyParameter();
	
		
	 	super.downloadScheduleFile(downloadCourseScheduleFileParam, fileToDownload );	
	}
}
