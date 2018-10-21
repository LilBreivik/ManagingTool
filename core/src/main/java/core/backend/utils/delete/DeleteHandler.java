package core.backend.utils.delete;

 
import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler; 

@FunctionalInterface
public interface DeleteHandler {

	public void handleDeletion(GeneralPersistentJSONFileHandler<? > persistentJSONFileHandler,  
			RESTScheduleRequest  deleteParameter);

	 
}
