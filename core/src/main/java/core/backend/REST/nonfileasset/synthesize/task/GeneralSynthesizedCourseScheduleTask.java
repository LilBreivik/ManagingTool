package core.backend.REST.nonfileasset.synthesize.task;
 
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.FileHandler;

public class GeneralSynthesizedCourseScheduleTask 
													extends SynthesizedTask<PersistenceCourseSchedulePOJO> {

	public GeneralSynthesizedCourseScheduleTask(FileHandler synthesizer) {
		super(synthesizer);
	}

	 
	@Override
	public void workOnTask() {

		this.response =  (PersistenceCourseSchedulePOJO) p_fileHandler.readFile("CourseSchedule");
	} 
  
}
