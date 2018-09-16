package core.controller.verifier;
 
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler; 


public class CourseVerifier extends Verifier<PersistenceCourseSchedulePOJO>  {
  
	public CourseVerifier (PersistenceCourseScheduleJSONFileHandler jsonFileHandler) {
		 
		verifier = (PersistenceCourseSchedulePOJO)  jsonFileHandler.readFile("CourseSchedule");
	}
 
}
