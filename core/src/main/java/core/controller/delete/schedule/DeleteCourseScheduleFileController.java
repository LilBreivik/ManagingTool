package core.controller.delete.schedule;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus;  
import core.controller.parameter.schedule.delete.DeleteScheduleFileParam;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus; 
import resources.components.filehandler.PathManager;
import resources.components.filehandler.JSON.AllLecturesScheduleJSONFileHandler; 
import resources.components.filehandler.JSON.LectureScheduleJSONFileHandler;
import resources.components.filehandler.XML.LectureScheduleXMLFileHandler;
import resources.utils.files.OrdinaryFileHandler; 

@Controller
public class DeleteCourseScheduleFileController 
												extends DeleteScheduleFileController{
    
	@Autowired
	public  DeleteCourseScheduleFileController (AllLecturesScheduleJSONFileHandler allLecturesJSONFileHandler, 
														LectureScheduleJSONFileHandler lecturesScheduleJSONFileHandler,
																LectureScheduleXMLFileHandler lectureScheduleXMLFileHandler) {
		
		super(allLecturesJSONFileHandler, 
				lectureScheduleXMLFileHandler.getFileAssetsManager().getPathManager(),
				  (DeleteParam, PathToSource, PathToAssembledFile) -> (PathToSource.toFile().exists()  && PathToAssembledFile.toFile().exists()), 
						
				  
				  (PersistentJSONFileHandler, DeleteParameter) -> {
							 
								PersistentJSONFileHandler.subtractFromPersistence(DeleteParameter.getTargetFileName(), 
										 lecturesScheduleJSONFileHandler.readFile(DeleteParameter.getUploadedFileName().getResolvedUploadedFileName() ));
								 
								// erase json 
							
								OrdinaryFileHandler.deleteFile(PersistentJSONFileHandler.getFileAssetsManager().getPathManager().getPathOfFile(DeleteParameter.getUploadedFileName().getResolvedUploadedFileName()).toFile() );
							
								// erase original 
								
								lectureScheduleXMLFileHandler.deleteFile( Paths.get(DeleteParameter.getUploadedFileName().getResolvedUploadedFileName()).toFile());
								
							});
	}

	  
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Delete/Schedule/Course", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public void deleteFile(@RequestBody  DeleteScheduleFileParam   deleteCourseScheduleFileParam)  
	{
		deleteCourseScheduleFileParam.verifyParameter();
		
	 	super.deleteScheduleFile(deleteCourseScheduleFileParam);	
	}
}
