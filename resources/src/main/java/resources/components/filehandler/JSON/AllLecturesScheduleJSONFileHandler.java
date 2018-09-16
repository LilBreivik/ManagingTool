package resources.components.filehandler.JSON;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Lecture.LectureInformationPOJO;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO; 
import resources.components.elements.POJO.Persistence.Lectures.PersistenceLectureSemesterSchedulePOJO; 
import resources.components.filehandler.assetsmanager.FileAssetsManager; 
 
@Component 
public class AllLecturesScheduleJSONFileHandler extends  PersistentJSONFileHandler<AllLecturesPOJO, 
																						LectureScheduleOfCoursePOJO >{

	@Autowired
	public AllLecturesScheduleJSONFileHandler( @Qualifier("FileAssetsManager for the JSON  Assets") FileAssetsManager fileassetsmanager) {
		super(AllLecturesPOJO.class, fileassetsmanager, (currentPersistencePOJO, persistentPOJOToAppend) -> {
			
			currentPersistencePOJO.addManyNewLecture( persistentPOJOToAppend.getAllLectures());
			
			return currentPersistencePOJO;
			
		}, 
				
		(currentPersistencePOJO, persistentPOJOToAppend) -> {
		 			
			for(PersistenceLectureSemesterSchedulePOJO pojo : persistentPOJOToAppend.getLecturesinAllSemesters()) {

				for(LectureInformationPOJO ppojoTest :  pojo.getM_CollectionOfLecturesInSemester()) {
					 
					System.out.println(ppojoTest.getName());
					currentPersistencePOJO.getAllLectures().removePOJO(ppojoTest.getName());	
				}
			}
			
			 System.out.println("lll");
			
			return currentPersistencePOJO;
					
		});
	}
 
}
