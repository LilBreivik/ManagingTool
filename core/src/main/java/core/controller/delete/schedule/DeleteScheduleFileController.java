package core.controller.delete.schedule;
 
 
import core.controller.handler.delete.DeleteFileAssetsHandler;
import core.controller.handler.delete.DeleteHandler;
import core.controller.parameter.schedule.GenericScheduleFileParam; 
import core.controller.processor.delete.DeleteProcessor; 
import resources.components.filehandler.PathManager; 
import resources.components.filehandler.JSON.PersistentJSONFileHandler; 
import resources.error.MissingAssetError; 

public class DeleteScheduleFileController   {

	private PersistentJSONFileHandler<?, ?>  m_jsonFileHandler;
	private PathManager m_pathManagerToOriginalFileAsset;
	private DeleteFileAssetsHandler m_deleteFileAssetHandler;
	private DeleteHandler m_deleteHandler; 
	
	protected DeleteProcessor p_delegatedDeletionProcessor;
	protected DeleteHandler p_delegatedDeletionHandler;
 
	
	public DeleteScheduleFileController( PersistentJSONFileHandler<?, ?> jsonFileHandler, 
												PathManager pathManagerToOriginalFileAsset,
													DeleteFileAssetsHandler deleteFileAssetHandler, 
														DeleteHandler deleteHandler) {
		 
		
		m_deleteHandler = deleteHandler; 
		m_deleteFileAssetHandler = deleteFileAssetHandler ; 
		m_jsonFileHandler = jsonFileHandler;  
		m_pathManagerToOriginalFileAsset = pathManagerToOriginalFileAsset;
		
	}

	 
	public void deleteScheduleFile( GenericScheduleFileParam   deleteCourseScheduleFileParam) {
		 

		try {
			
			m_deleteHandler.handleDeletion(m_jsonFileHandler, 
					deleteCourseScheduleFileParam);
		}
		catch(Exception ex) {
			
			StringBuilder misssingFileName = new StringBuilder(); 
			
			misssingFileName.append((m_jsonFileHandler.getFileAssetsManager().getPathManager()
															.getPathOfFile(deleteCourseScheduleFileParam
																	.getTargetFileName()).toFile().exists() ) ? 
																			m_jsonFileHandler.getFileAssetsManager().getPathManager()
																			.getPathOfFile(deleteCourseScheduleFileParam
																					.getTargetFileName()).toFile().getName()
																			
																			: "");
			
			
			throw new MissingAssetError("Die Datei " + misssingFileName.toString() + " ist nicht vorhanden");
		}
			 
	
	}
 

}
