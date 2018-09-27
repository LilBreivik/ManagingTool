package core.backend.utils.delete;

import core.backend.REST.fileasset.delete.parameter.request.DeleteFileParameter;
import resources.components.filehandler.JSON.PersistentJSONFileHandler;

@FunctionalInterface
public interface DeleteHandler {

	public void handleDeletion(PersistentJSONFileHandler<?, ?> persistentJSONFileHandler,  
			DeleteFileParameter  deleteParameter);

	 
}
