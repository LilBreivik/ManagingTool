package core.controller.download;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus;

import core.controller.parameter.download.DownloadLectureScheduleFileParam;

import javax.servlet.http.HttpServletResponse; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import resources.components.filehandler.XLS.LectureScheduleXLSFileHandler; 

import static resources.error.parameter.FileAssetParameterViolationError.FileExtension.XLS;

@Controller
public class LectureScheduleFileDownloadController 
												extends DownloadScheduleFileController {
   
	@Autowired
	public   LectureScheduleFileDownloadController  (  LectureScheduleXLSFileHandler xlsFileHandler) {
		
		super(xlsFileHandler, (SourceFile, Response) ->  {
			
			 Response.setContentType("application/xls");
			  
			 Response.setHeader("Content-Disposition", "attachment; filename=" + ( new StringBuilder( "\"" + SourceFile.getName().concat(XLS.toString()) + "\"")).toString() );
			
		});
	}

	  
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Download/Schedule/Lecture", method = RequestMethod.POST, consumes="application/json")
	public void downnloadFile(@RequestBody  DownloadLectureScheduleFileParam    downloadLectureScheduleFileParam, 
																HttpServletResponse fileToDownload)  
	{
		downloadLectureScheduleFileParam.verifyParameter();
	
		
	 	super.downloadScheduleFile(downloadLectureScheduleFileParam, fileToDownload );	
	}
}
