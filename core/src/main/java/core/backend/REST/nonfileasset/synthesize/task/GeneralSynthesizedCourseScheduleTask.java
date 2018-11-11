package core.backend.REST.nonfileasset.synthesize.task;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler; 

@Component
public class GeneralSynthesizedCourseScheduleTask 
													extends SynthesizedTask<PersistenceCourseSchedulePOJO> {

	@Autowired
	@Qualifier("JSONFileHandler for PersistenceCourseScheduleJSONFile")  
	private GeneralJSONFileHandler<PersistenceCourseSchedulePOJO>  m_jsonFileHandler;
	 

	@Override
	public void workOnTask() {

		this.response =  m_jsonFileHandler.readFile("CourseSchedule");
	} 
  
}
