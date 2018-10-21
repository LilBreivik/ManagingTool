package core.backend.REST.fileasset.delete.task;

   
import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;
import core.backend.REST.general.task.response.AbstractFileHandlerTaskImpl;
import core.backend.utils.delete.DeleteHandler;  
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler; 
import resources.error.FileIsMissingError;  

public class DeleteFileTask 
								extends AbstractFileHandlerTaskImpl<GeneralPersistentJSONFileHandler, RESTScheduleRequest, String> {

	private DeleteHandler m_deleteHandler; 
	   
	public DeleteFileTask( GeneralPersistentJSONFileHandler<?> jsonFileHandler, 
												 DeleteHandler deleteHandler) {
		  
		super(jsonFileHandler);
		 
		m_deleteHandler = deleteHandler;  
		
	}
  

	@Override
	public void workOnTask(RESTScheduleRequest parameter) {
		
		 
		try {
			
		 	
			m_deleteHandler.handleDeletion(p_fileHandler, 
					parameter);
		} 
		catch(Exception ex) {
			  
			FileIsMissingError missingFileError = new FileIsMissingError("Die Datei " + parameter.getFileNameResolver().getResolvedFileName().toString() + " ist nicht vorhanden");
		 
			
			missingFileError.missingFileName =  parameter.getFileNameResolver().getResolvedFileName().toString(); 
			
			missingFileError.missingFileCause = "gelöscht"; 
			 
			throw missingFileError; 
		}
    
	}
	
	
	@Override
	public SuccessResponse<String> getResultsFromTask() {
		 
		return new SuccessResponse<String> ("Du hast die Datei erfolgreich gelöscht");
	}

}
