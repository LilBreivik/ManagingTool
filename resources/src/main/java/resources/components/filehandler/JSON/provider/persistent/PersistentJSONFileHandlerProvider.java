package resources.components.filehandler.JSON.provider.persistent;
 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.utils.adder.general.GeneralPOJOPersistenceAdder;
import resources.components.filehandler.utils.subtractor.general.GeneralPOJOPersistenceSubtractor;
import resources.utils.pathmanager.PathManager; 

@Configuration
public class PersistentJSONFileHandlerProvider {
  
	@Bean
	@Qualifier("JSONFileHandler for AllLecturesScheduleJSONFile")
	public GeneralPersistentJSONFileHandler<AllLecturesPOJO> provideAllLecturesScheduleJSONFileHandler(
										@Qualifier("PathManager to JSONFiles") PathManager pathManagerToJSONFiles,
											@Qualifier("POJOPersistenceAdder for AllLecturesScheduleJSON") GeneralPOJOPersistenceAdder<AllLecturesPOJO> persistenceAddition,
												@Qualifier("POJOPersistenceSubstractor for AllLecturesScheduleJSON") GeneralPOJOPersistenceSubtractor<AllLecturesPOJO> persistenceSubstraction){
					 
		return new GeneralPersistentJSONFileHandler<AllLecturesPOJO>(AllLecturesPOJO.class, 
																		 pathManagerToJSONFiles,
																			 persistenceAddition,
																				persistenceSubstraction );
	}
	
	@Bean
	@Qualifier("JSONFileHandler for LectureScheduleJSONFile")
	public GeneralPersistentJSONFileHandler<LectureScheduleOfCoursePOJO> provideLectureScheduleJSONFileHandler(
									@Qualifier("PathManager to JSONFiles") PathManager pathManagerToJSONFiles,
										@Qualifier("POJOPersistenceAdder for LectureScheduleOfCoursePOJO") GeneralPOJOPersistenceAdder<LectureScheduleOfCoursePOJO> persistenceAddition,
											@Qualifier("POJOPersistenceSubstractor for LectureScheduleOfCoursePOJO") GeneralPOJOPersistenceSubtractor<LectureScheduleOfCoursePOJO> persistenceSubstraction		
										){
			
		return new GeneralPersistentJSONFileHandler< LectureScheduleOfCoursePOJO>(LectureScheduleOfCoursePOJO.class, 
																					pathManagerToJSONFiles,
																					    persistenceAddition,
																						 	persistenceSubstraction	);		
	}
	 
	  
	@Bean  
	@Qualifier("JSONFileHandler for NoticesJSONFile")
	public GeneralJSONFileHandler<NoticesPOJO> provideNoticesJSONFileHandler(
		 
			@Qualifier("Path to User Notices") PathManager pathManagerToJSONFiles){
		
		return new GeneralJSONFileHandler<NoticesPOJO>(NoticesPOJO.class, 
				pathManagerToJSONFiles );  
	}
}
