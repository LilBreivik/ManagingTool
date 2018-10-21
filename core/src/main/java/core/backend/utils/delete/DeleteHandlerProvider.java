package core.backend.utils.delete;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.XLS.general.GeneralXLSFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;
import resources.components.filehandler.utils.subtractor.general.GeneralPOJOPersistenceSubtractor;

@Configuration
public class DeleteHandlerProvider {

	@Bean
	@Qualifier("provide DeleteHandler for LectureScheduleFile")
	public DeleteHandler provideDeleteHandlerForLectureScheduleFile(
			
	@Qualifier("POJOPersistenceSubstractor for AllLecturesScheduleJSON") GeneralPOJOPersistenceSubtractor<AllLecturesPOJO>  substractor,
			@Qualifier("XLSFileHandler for LectureScheduleXLSFiles")  GeneralXLSFileHandler   lectureScheduleXLSFileHandler) {
		
		
		
		return new DeleteHandler() {
			
			@Override
			public void handleDeletion(GeneralPersistentJSONFileHandler<?> persistentJSONFileHandler,
					RESTScheduleRequest deleteParameter) {
				
				System.out.println();
				
			  	AllLecturesPOJO lecturesToSubstract = (AllLecturesPOJO) lectureScheduleXLSFileHandler.readXLSFile(deleteParameter.getFileNameResolver().getResolvedFileName());
				
				AllLecturesPOJO allLectures =  (AllLecturesPOJO) persistentJSONFileHandler.readJSONFile(deleteParameter.getTargetFileName());
				
				
				substractor.subtractFromPersistence(allLectures, lecturesToSubstract);
				 
				
				allLectures.substractManyLectures(lecturesToSubstract.getAllLectures());
				  
			   // erase original 
				
				lectureScheduleXLSFileHandler.deleteFile(deleteParameter.getFileNameResolver().getResolvedFileName());	
			}
		};
	}
	
	 
	@Bean
	@Qualifier("provide DeleteHandler for CourseScheduleFile")
	public DeleteHandler provideDeleteHandlerForCourseScheduleFile(
			 
				@Qualifier("XMLFileHandler for LectureScheduleXMLFiles") GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>  lectureScheduleXMLFileHandler) {
		
		return new DeleteHandler() {
			
			@Override
			public void handleDeletion(GeneralPersistentJSONFileHandler<?> persistentJSONFileHandler,
					RESTScheduleRequest deleteParameter) {
				 
				lectureScheduleXMLFileHandler.deleteFile(deleteParameter.getFileNameResolver().getResolvedFileName());	
				 
			}
		};
	}
	 
	
}
