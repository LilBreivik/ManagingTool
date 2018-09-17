package core.controller.delete;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus;

import core.controller.parameter.delete.DeleteLectureFileParam;

import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO; 
import resources.components.filehandler.JSON.AllLecturesScheduleJSONFileHandler; 
import resources.components.filehandler.JSON.LectureScheduleJSONFileHandler;
import resources.components.filehandler.XLS.LectureScheduleXLSFileHandler; 

@Controller
public class DeleteLectureScheduleFileController 
												extends DeleteScheduleFileController{
    
	@Autowired
	public  DeleteLectureScheduleFileController (AllLecturesScheduleJSONFileHandler allLecturesJSONFileHandler, 
														LectureScheduleJSONFileHandler lecturesScheduleJSONFileHandler,
														    LectureScheduleXLSFileHandler lectureScheduleXLSFileHandler) {
		
		super(lecturesScheduleJSONFileHandler, 
				lectureScheduleXLSFileHandler.getFileAssetsManager().getPathManager(),
				  (DeleteParam, PathToSource, PathToAssembledFile) -> (PathToSource.toFile().exists()  && PathToAssembledFile.toFile().exists()), 
				 
				  
				  (PersistentJSONFileHandler, DeleteParameter) -> {
							
					  	AllLecturesPOJO lecturesToSubstract = (AllLecturesPOJO) lectureScheduleXLSFileHandler.readFile(DeleteParameter.getUploadedFileName().getResolvedUploadedFileName());
						
						AllLecturesPOJO allLectures =  (AllLecturesPOJO) allLecturesJSONFileHandler.readFile(DeleteParameter.getTargetFileName());
						
						
						
						allLectures.substractManyLectures(lecturesToSubstract.getAllLectures());
						
						
						allLecturesJSONFileHandler.writeToFile(DeleteParameter.getTargetFileName(), allLectures);
						
							 	 
					   // erase original 
						
						lectureScheduleXLSFileHandler.deleteFile( Paths.get(DeleteParameter.getUploadedFileName().getResolvedUploadedFileName()).toFile());
							 
				  });
	}

	  
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Delete/Schedule/Lecture", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public void deleteFile(@RequestBody  DeleteLectureFileParam   deleteCourseScheduleFileParam)  
	{
		deleteCourseScheduleFileParam.verifyParameter();
		
	 	super.deleteScheduleFile(deleteCourseScheduleFileParam);	
	}
}
