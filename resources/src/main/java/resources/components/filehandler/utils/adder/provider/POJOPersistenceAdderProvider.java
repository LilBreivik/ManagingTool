package resources.components.filehandler.utils.adder.provider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.filehandler.utils.adder.general.GeneralPOJOPersistenceAdder; 

@Configuration
public class POJOPersistenceAdderProvider {

	@Bean
	@Qualifier("POJOPersistenceAdder for AllLecturesScheduleJSON")
	public GeneralPOJOPersistenceAdder<AllLecturesPOJO> provideAllLecturesScheduleJSONPOJOPersistenceAdder(){
		
		 return (currentPersistencePOJO, persistentPOJOToAppend) -> {
				
				currentPersistencePOJO.addManyNewLecture( persistentPOJOToAppend.getAllLectures());
				
				return currentPersistencePOJO;
				
		};
	}
	
	@Bean
	@Qualifier("POJOPersistenceAdder for LectureScheduleOfCoursePOJO")
	public GeneralPOJOPersistenceAdder<LectureScheduleOfCoursePOJO> provideLectureScheduleOfCoursePOJOPersistenceAdder(){
		
	    	return (currentPersistencePOJO, persistentPOJOToAppend) -> {
				
				currentPersistencePOJO.setLecturesinAllSemesters( persistentPOJOToAppend.getLecturesinAllSemesters());
				
				return currentPersistencePOJO;
		
			};
	}
}
