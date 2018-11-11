package core.backend.utils.verifier;
 
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler; 


public class CourseVerifier extends Verifier<PersistenceCourseSchedulePOJO>  {
  
	public CourseVerifier (GeneralJSONFileHandler<PersistenceCourseSchedulePOJO> jsonFileHandler) {
		 
		verifier = (PersistenceCourseSchedulePOJO)  jsonFileHandler.readFile("CourseSchedule");
	}
 
}
