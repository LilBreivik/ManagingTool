package core.backend.REST.fileasset.delete.task;

   
import core.backend.REST.fileasset.delete.parameter.DeleteFileParameter; 
import core.backend.REST.general.task.nonresponse.AbstractFileHandlerNonResponseTaskImpl; 
import core.backend.utils.delete.DeleteHandler;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler; 
import resources.error.FileIsMissingError;  

public class DeleteFileTask 
		extends AbstractFileHandlerNonResponseTaskImpl<DeleteFileParameter, 
															GeneralPersistentJSONFileHandler   >{

	private DeleteHandler m_deleteHandler; 
	
	public DeleteFileTask(GeneralPersistentJSONFileHandler   fileHandler, 
			 DeleteHandler deleteHandler) {
		super(fileHandler);
		 
		m_deleteHandler = deleteHandler;  
		
	}
	 
 

	@Override
	public void workOnTask(DeleteFileParameter param) {
	
		final String resolvedFileName = param.getFileNameResolver().getResolvedFileName(); 
		
		
		try {
			 
		 	
			m_deleteHandler.handleDeletion(p_fileHandler, 
					param);
		} 
		catch(Exception ex) {
			  
			FileIsMissingError missingFileError = new FileIsMissingError("Die Datei " + resolvedFileName + " ist nicht vorhanden");
		 
			
			missingFileError.missingFileName =  param.getFileNameResolver().getResolvedFileName().toString(); 
			
			missingFileError.missingFileCause = "gelöscht"; 
			 
			throw missingFileError; 
		}
	}
	 
}
