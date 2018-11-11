package core.backend.utils.delete;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import core.backend.REST.general.request.schedule.file.RESTScheduleFileRequest;
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.components.elements.POJO.Course.CourseSchedulePOJO;
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
					RESTScheduleFileRequest deleteParameter) {
			
				final String resolvedFileName = deleteParameter.getFileNameResolver().getResolvedFileName();
				
			  	AllLecturesPOJO lecturesToSubstract = (AllLecturesPOJO) lectureScheduleXLSFileHandler.readFile(resolvedFileName);
				
				AllLecturesPOJO allLectures =  (AllLecturesPOJO) persistentJSONFileHandler.readFile(deleteParameter.getTargetFileName());
				
				
				CoursePOJO courseScheduleRequest = (CoursePOJO) deleteParameter.getRequest();
				
				allLectures.getAllLectures().stream()
								.forEach(lecture -> lecture.getvTerm().equals(courseScheduleRequest.getCourseTerm()));
				
				AllLecturesPOJO lecturesLeft = substractor.subtractFromPersistence(allLectures, lecturesToSubstract);
				 
				// put the lectures that shall be left to the persistent file 
				
				persistentJSONFileHandler.writeToFile(deleteParameter.getTargetFileName(), lecturesLeft);
				
				 
			   // erase original 
				
				lectureScheduleXLSFileHandler.deleteFile(resolvedFileName);	
			}
		};
	}
	
	
	@Bean
	@Qualifier("provide DeleteHandler for CourseScheduleFile")
	public DeleteHandler provideDeleteHandlerForCourseScheduleFile(
			@Qualifier("XMLFileHandler for LectureScheduleXMLFiles") GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>  lectureScheduleXMLFileHandler
			) 
	
	{
		return new DeleteHandler() {
			
			@Override
			public void handleDeletion(GeneralPersistentJSONFileHandler<?> persistentJSONFileHandler,
					RESTScheduleFileRequest deleteParameter) {
				 
				
				persistentJSONFileHandler.deleteFile(deleteParameter.getTargetFileName());
				
				
				lectureScheduleXMLFileHandler.deleteFile(deleteParameter.getFileNameResolver().getResolvedFileName());	
				 
			}
		};
	}
	 
	
}
