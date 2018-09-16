package core.controller.upload.schedule;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import core.controller.parameter.schedule.upload.LectureScheduleFileUploadParam;

import org.springframework.beans.factory.annotation.Autowired;

import resources.components.filehandler.JSON.AllLecturesScheduleJSONFileHandler; 
import resources.components.filehandler.XLS.LectureScheduleXLSFileHandler; 
import org.springframework.http.HttpStatus;

@Controller
public class LectureScheduleFileUploadController 
												extends UploadScheduleFileController<LectureScheduleFileUploadParam>{


	@Autowired 
	public LectureScheduleFileUploadController(AllLecturesScheduleJSONFileHandler jsonFileHandler, LectureScheduleXLSFileHandler xlsFileHandler) {
		super(jsonFileHandler, xlsFileHandler);
	}
  
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Upload/Schedule/Lecture", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public void uploadFile(@RequestBody  LectureScheduleFileUploadParam uploadedScheduleFile )  
	{
	 
		uploadedScheduleFile.verifyParameter();
		
	 	super.uploadScheduleFile(uploadedScheduleFile);	
	}
}
