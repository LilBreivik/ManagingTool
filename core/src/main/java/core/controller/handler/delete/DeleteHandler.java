package core.controller.handler.delete;
 
import core.controller.parameter.schedule.GenericScheduleFileParam;
import core.controller.parameter.schedule.synthesized.SynthesizedCourseScheduleFileParam;
import resources.components.filehandler.JSON.PersistentJSONFileHandler;

@FunctionalInterface
public interface DeleteHandler {

	public void handleDeletion(PersistentJSONFileHandler<?, ?> persistentJSONFileHandler,  GenericScheduleFileParam  deleteParameter);

	 
}
