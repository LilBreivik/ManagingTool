package core.backend.utils.delete;

 
import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import resources.components.filehandler.JSON.PersistentJSONFileHandler;

@FunctionalInterface
public interface DeleteHandler {

	public void handleDeletion(PersistentJSONFileHandler<?, ?> persistentJSONFileHandler,  
			RESTScheduleRequest  deleteParameter);

	 
}
