package resources.components.filehandler.utils.subtractor.provider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO; 
import resources.components.filehandler.utils.subtractor.general.GeneralPOJOPersistenceSubtractor; 

@Configuration
public class POJOPersistenceSubstractorProvider {

	
	@Bean
	@Qualifier("POJOPersistenceSubstractor for AllLecturesScheduleJSON")
	public GeneralPOJOPersistenceSubtractor<AllLecturesPOJO > provideAllLecturesScheduleJSONPOJOPersistenceSubstractor(){
		 
		return (currentPersistencePOJO, persistentPOJOToAppend) -> {
		 
			currentPersistencePOJO.substractManyLectures(persistentPOJOToAppend.getAllLectures());
			 
			return currentPersistencePOJO; 
		};
	}
	
	@Bean
	@Qualifier("POJOPersistenceSubstractor for LectureScheduleOfCoursePOJO")
	public GeneralPOJOPersistenceSubtractor<LectureScheduleOfCoursePOJO > provideLectureScheduleOfCoursePOJOPersistenceSubstractor(){
		
	    return (currentPersistencePOJO, persistentPOJOToAppend) -> {
			 
	     	currentPersistencePOJO.setLecturesinAllSemesters( persistentPOJOToAppend.getLecturesinAllSemesters());
					
			return currentPersistencePOJO;
			
		};	 
	}
	 
}
