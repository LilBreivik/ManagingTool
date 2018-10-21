package core.backend.REST.fileasset.upload.task;
   

import core.backend.REST.fileasset.upload.parameter.UploadScheduleFileParameter;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.response.AbstractFileHandlerTaskImpl;
import core.backend.utils.upload.UploadHandler; 
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.general.FileRepositoryHandler; 
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.pathmanager.PathManager;

public class UploadFileTask<PersistentPOJO> 
								extends AbstractFileHandlerTaskImpl<FileRepositoryHandler, UploadScheduleFileParameter, String>{
 
    private PathManager  m_pathManagerToLectureAssetsDirectory; 
	
    private GeneralPersistentJSONFileHandler<PersistentPOJO>  m_jsonFileHandler;
    
    private UploadHandler<PersistentPOJO> m_uploadHandler; 
    
	public UploadFileTask(FileRepositoryHandler fileHandler, 
							 GeneralPersistentJSONFileHandler<PersistentPOJO> jsonFileHandler, 
							 	UploadHandler<PersistentPOJO> uploadHandler,
									PathManager pathManagerToLectureAssetsDirectory) {
		
		super(fileHandler);
	 
		m_jsonFileHandler = jsonFileHandler; 
		m_pathManagerToLectureAssetsDirectory = pathManagerToLectureAssetsDirectory;
		m_uploadHandler = uploadHandler;
	}
 
	@Override 
	public void workOnTask(UploadScheduleFileParameter parameter) {
	
		// move temp file to target directoy 
		 
		p_fileHandler.moveFile(parameter.getScheduleFile() , 
				OrdinaryFileHandler.buildPath(m_pathManagerToLectureAssetsDirectory.getPathToOperateOn(),
						parameter.getFileNameResolver().getResolvedFileName()));
		 
		
		// first we will check if a persistent file does exist 
		
		if(!m_jsonFileHandler.checkIfFileDoesExist(parameter.getTargetFileName())) {
			
			// if not it must be created 
			
			m_jsonFileHandler.createJSONFile(parameter.getTargetFileName());
			
			PersistentPOJO pojo = m_uploadHandler.handleUploadedFile(parameter.getFileNameResolver().getResolvedFileName());
			
			m_jsonFileHandler.writeToJSONFile(parameter.getTargetFileName(), pojo); 
		}
		 
		// after its creation, the new contents will be appended 
		
		else {
			
			
			PersistentPOJO pojo = m_uploadHandler.handleUploadedFile(parameter.getFileNameResolver().getResolvedFileName());
			
			m_jsonFileHandler.appendToPersistence( parameter.getTargetFileName(), pojo); 
		}
		
	}


    @Override
    public SuccessResponse<String> getResultsFromTask() {
    
    	return  new SuccessResponse<String>("Du hast die Datei erfolgreich hochgeladen") ;
    }
	 
}
