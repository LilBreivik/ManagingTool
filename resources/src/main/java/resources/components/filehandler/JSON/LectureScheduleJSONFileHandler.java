package resources.components.filehandler.JSON;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO; 
import resources.components.filehandler.PathManager;
import resources.components.filehandler.assetsmanager.FileAssetsManager; 

@Component 
public class LectureScheduleJSONFileHandler  extends  PersistentJSONFileHandler<LectureScheduleOfCoursePOJO,
																					AllLecturesPOJO> {

	@Autowired
	public LectureScheduleJSONFileHandler( @Qualifier("FileAssetsManager for the JSON  Assets") FileAssetsManager fileassetsmanager) { 
		super(LectureScheduleOfCoursePOJO.class, fileassetsmanager,  (currentPersistencePOJO, persistentPOJOToAppend) -> {
			
			currentPersistencePOJO.setLecturesinAllSemesters( persistentPOJOToAppend.getLecturesinAllSemesters());
			
			return currentPersistencePOJO;
	
		}, 
		(currentPersistencePOJO, persistentPOJOToAppend) -> {
			
			System.out.println(persistentPOJOToAppend);
			System.out.println(currentPersistencePOJO);
			System.out.println("");
			
		//	currentPersistencePOJO.setLecturesinAllSemesters( persistentPOJOToAppend.getLecturesinAllSemesters());
					
			return currentPersistencePOJO;
			
		}		
		);   
	} 
}

