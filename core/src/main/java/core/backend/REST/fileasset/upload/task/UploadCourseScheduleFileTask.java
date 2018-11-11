package core.backend.REST.fileasset.upload.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import core.backend.utils.upload.UploadHandler;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler; 
import resources.utils.pathmanager.PathManager;

@Component
public class UploadCourseScheduleFileTask 
												extends UploadFileTask<LectureScheduleOfCoursePOJO>{

	@Autowired
	public UploadCourseScheduleFileTask(@Qualifier("XMLFileHandler for LectureScheduleXMLFiles") GeneralXMLFileHandler<LectureScheduleOfCoursePOJO> lectureScheduleXMLFileHandler,
									@Qualifier("JSONFileHandler for LectureScheduleJSONFile") GeneralPersistentJSONFileHandler<LectureScheduleOfCoursePOJO> lectureScheduleJSONFileHandler,
								@Qualifier("provide UploadHandler for LectureScheduleXMLFile") UploadHandler<LectureScheduleOfCoursePOJO> uploadHandler,
							@Qualifier("PathManager to Lecture Assets") PathManager lectureAssetsPathManager) {
		
		super(lectureScheduleXMLFileHandler, lectureScheduleJSONFileHandler, uploadHandler, lectureAssetsPathManager);
		 
	}

}
