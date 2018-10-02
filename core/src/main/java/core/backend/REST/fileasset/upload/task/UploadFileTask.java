package core.backend.REST.fileasset.upload.task;
  

import core.backend.REST.fileasset.upload.parameter.UploadScheduleFileParameter;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.AbstractTaskImpl;
import resources.components.filehandler.FileHandler;
import resources.components.filehandler.PathManager;
import resources.components.filehandler.JSON.PersistentJSONFileHandler; 
import resources.utils.files.OrdinaryFileHandler;

public class UploadFileTask 
								extends AbstractTaskImpl<UploadScheduleFileParameter, String>{

	private FileHandler m_UploadFileHandler; 
	
    private PathManager  m_pathManagerToLectureAssetsDirectory; 
	
    private PersistentJSONFileHandler<?, ?>  m_jsonFileHandler;
    
	public UploadFileTask(FileHandler uploadFileHandler, 
								 PersistentJSONFileHandler<?, ?> jsonFileHandler, 
										PathManager pathManagerToLectureAssetsDirectory) {
							
		
		m_jsonFileHandler = jsonFileHandler; 
		m_pathManagerToLectureAssetsDirectory = pathManagerToLectureAssetsDirectory; 
		m_UploadFileHandler = uploadFileHandler; 
	}
	
	@Override
	public void workOnTask(UploadScheduleFileParameter parameter) {
		 	
		m_UploadFileHandler.moveFile(parameter.getScheduleFile() , 
				OrdinaryFileHandler.buildPath(m_pathManagerToLectureAssetsDirectory.getPathToOperateOn(),
						parameter.getFileNameResolver().getResolvedFileName()));
	 
			m_jsonFileHandler.createFile(parameter.getTargetFileName());
			   
			m_jsonFileHandler.writeToFile(parameter.getTargetFileName(), 
					m_UploadFileHandler.readFile(parameter.getFileNameResolver().getResolvedFileName() ));
    
	}


    @Override
    public SuccessResponse<String> getResultsFromTask() {
    
    	return  new SuccessResponse<String>("Du hast die Datei erfolgreich hochgeladen") ;
    }
 
}
