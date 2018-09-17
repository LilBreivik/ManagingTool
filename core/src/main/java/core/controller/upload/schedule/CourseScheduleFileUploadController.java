package core.controller.upload.schedule;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus;

import core.controller.parameter.upload.CourseScheduleFileUploadParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import resources.components.filehandler.JSON.LectureScheduleJSONFileHandler;
import resources.components.filehandler.XML.LectureScheduleXMLFileHandler; 

@Controller
public class CourseScheduleFileUploadController 
												extends UploadScheduleFileController<CourseScheduleFileUploadParam>{
   
	@Autowired
	public  CourseScheduleFileUploadController (LectureScheduleJSONFileHandler jsonFileHandler, 
			LectureScheduleXMLFileHandler xmlFileHandler) {
		
		super(jsonFileHandler, xmlFileHandler);
	}

	  
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Upload/Schedule/Course", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public void uploadFile(@RequestBody  CourseScheduleFileUploadParam   uploadedLectureScheduleFile)  
	{
		uploadedLectureScheduleFile.verifyParameter();
		
	 	super.uploadScheduleFile(uploadedLectureScheduleFile);	
	}
}
