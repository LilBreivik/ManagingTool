package core.backend.REST.fileasset.upload.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import core.backend.REST.fileasset.upload.parameter.UploadFileParameter;
import core.backend.utils.upload.UploadHandler;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.XLS.general.GeneralXLSFileHandler; 
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.pathmanager.PathManager;

@Component
public class UploadLectureScheduleFileTask 
												extends UploadFileTask<AllLecturesPOJO>{

	@Autowired
	public UploadLectureScheduleFileTask(@Qualifier("XLSFileHandler for LectureScheduleXLSFiles")  GeneralXLSFileHandler xlsFileHandler,
		                          	@Qualifier("JSONFileHandler for AllLecturesScheduleJSONFile") GeneralPersistentJSONFileHandler<AllLecturesPOJO> jsonFileHandler,
		                        @Qualifier("provide UploadHandler for LectureScheduleFile") UploadHandler<AllLecturesPOJO>	uploadHandler,
	                    	@Qualifier("PathManager to Lecture Assets") PathManager lectureAssetsPathManager) {
	
		super(xlsFileHandler, jsonFileHandler, uploadHandler, lectureAssetsPathManager);
	
	}

	
	@Override
	public void workOnTask(UploadFileParameter parameter)  {
	 	 
		
		final String resolvedFileName = parameter.getFileNameResolver().getResolvedFileName(); 
		
	    final String targetFileName = parameter.getTargetFileName();
		
	    
	    CourseScheduleFilePOJO coursePOJOFromRequest = ( CourseScheduleFilePOJO ) parameter.getRequest();
	    
	    
			 
		// first we will check if a persistent file does exist 
		 
		if(!p_jsonFileHandler.checkIfFileDoesExist(targetFileName)) {
			
			
			// move temp file to target directoy 
			 
			p_fileHandler.moveFile(parameter.getScheduleFile() , 
				
					OrdinaryFileHandler.buildPath(p_pathManagerToLectureAssetsDirectory.getPathToOperateOn(),
					
								resolvedFileName));
			
			// if not it must be created 
			
			p_jsonFileHandler.createFile(targetFileName);
			
			AllLecturesPOJO pojo = p_uploadHandler.handleUploadedFile(resolvedFileName);
			
			pojo.getAllLectures().stream().forEach(lecture -> lecture.setvTerm(coursePOJOFromRequest.getCourseTerm()));
			
			p_jsonFileHandler.writeToFile(targetFileName, pojo); 
		}
		 
		// after its creation, the new contents will be appended 
		
		else {
			
			

			// move temp file to target directoy 
			 
			p_fileHandler.moveFile(parameter.getScheduleFile() , 
				
					OrdinaryFileHandler.buildPath(p_pathManagerToLectureAssetsDirectory.getPathToOperateOn(),
					
								resolvedFileName));
			
			
			AllLecturesPOJO pojo = p_uploadHandler.handleUploadedFile(resolvedFileName);
			
			pojo.getAllLectures().stream().forEach(lecture -> lecture.setvTerm(coursePOJOFromRequest.getCourseTerm()));
			
			
			p_jsonFileHandler.appendToPersistence( targetFileName, pojo); 
		}
		
	}
}
