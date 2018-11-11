package core.backend.utils.delete;

 
import core.backend.REST.general.request.schedule.file.RESTScheduleFileRequest;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler; 

@FunctionalInterface
public interface DeleteHandler{

	public void handleDeletion(GeneralPersistentJSONFileHandler<?> persistentJSONFileHandler,  
			RESTScheduleFileRequest  deleteParameter);

	 
}
