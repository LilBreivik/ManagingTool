package core.backend.REST.fileasset.delete.task;

  
import core.backend.REST.fileasset.delete.parameter.request.DeleteFileParameter;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.AbstractTaskImpl;
import core.backend.utils.delete.DeleteFileAssetsHandler;
import core.backend.utils.delete.DeleteHandler;
import core.backend.utils.delete.DeleteProcessor;
import resources.components.filehandler.PathManager; 
import resources.components.filehandler.JSON.PersistentJSONFileHandler;
import resources.error.FileIsMissingError;
import resources.error.MissingAssetError; 

public class DeleteFileTask 
								extends AbstractTaskImpl<DeleteFileParameter, String> {

	private PersistentJSONFileHandler<?, ?>  m_jsonFileHandler;
	private PathManager m_pathManagerToOriginalFileAsset;
	private DeleteFileAssetsHandler m_deleteFileAssetHandler;
	private DeleteHandler m_deleteHandler; 
	
	protected DeleteProcessor p_delegatedDeletionProcessor;
	protected DeleteHandler p_delegatedDeletionHandler;
 
	
	public DeleteFileTask( PersistentJSONFileHandler<?, ?> jsonFileHandler, 
												PathManager pathManagerToOriginalFileAsset,
													DeleteFileAssetsHandler deleteFileAssetHandler, 
														DeleteHandler deleteHandler) {
		 
		
		m_deleteHandler = deleteHandler; 
		m_deleteFileAssetHandler = deleteFileAssetHandler ; 
		m_jsonFileHandler = jsonFileHandler;  
		m_pathManagerToOriginalFileAsset = pathManagerToOriginalFileAsset;
		
	}
 

	@Override
	public void workOnTask(DeleteFileParameter parameter) {
		
		 
		try {
			
			m_deleteHandler.handleDeletion(m_jsonFileHandler, 
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
