package core.backend.REST.nonfileasset.synthesize.task;
 
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;

public class GeneralSynthesizedCourseScheduleTask 
													extends SynthesizedTask<PersistenceCourseSchedulePOJO> {

	private GeneralJSONFileHandler<PersistenceCourseSchedulePOJO> m_fileHandler;
	
	
	public GeneralSynthesizedCourseScheduleTask(GeneralJSONFileHandler<PersistenceCourseSchedulePOJO> fileHandler) {
		
		m_fileHandler = fileHandler; 
	}

	 


	@Override
	public void workOnTask() {

		this.response =  m_fileHandler.readJSONFile("CourseSchedule");
	} 
  
}
