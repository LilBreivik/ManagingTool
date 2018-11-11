package core.backend.REST.fileasset.upload.task;
    
import core.backend.REST.fileasset.upload.parameter.UploadFileParameter;
import core.backend.REST.general.request.RESTRequest;
import core.backend.REST.general.task.nonresponse.AbstractFileHandlerNonResponseTaskImpl; 
import core.backend.utils.upload.UploadHandler; 
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.general.FileRepositoryHandler; 
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.pathmanager.PathManager;

public abstract class UploadFileTask<PersistentPOJO> 
			extends AbstractFileHandlerNonResponseTaskImpl<UploadFileParameter, FileRepositoryHandler>{
 
    protected PathManager  p_pathManagerToLectureAssetsDirectory; 
	
    protected GeneralPersistentJSONFileHandler<PersistentPOJO>  p_jsonFileHandler;
    
    protected UploadHandler<PersistentPOJO> p_uploadHandler; 
    
	public UploadFileTask(FileRepositoryHandler fileHandler, 
							 GeneralPersistentJSONFileHandler<PersistentPOJO> jsonFileHandler, 
							 	UploadHandler<PersistentPOJO> uploadHandler,
									PathManager pathManagerToLectureAssetsDirectory) {
		
		super(fileHandler);
	 
		p_jsonFileHandler = jsonFileHandler; 
		p_pathManagerToLectureAssetsDirectory = pathManagerToLectureAssetsDirectory;
		p_uploadHandler = uploadHandler;
	} 
  
 	  
	@Override 
	public void workOnTask(UploadFileParameter parameter) {
	 	 
		
		final String resolvedFileName = parameter.getFileNameResolver().getResolvedFileName(); 
		
	 	
	    final String targetFileName = parameter.getTargetFileName();
		
			 
		// first we will check if a persistent file does exist 
		 
		if(!p_jsonFileHandler.checkIfFileDoesExist(targetFileName)) {
			
			
			// move temp file to target directoy 
			 
			p_fileHandler.moveFile(parameter.getScheduleFile() , 
				
					OrdinaryFileHandler.buildPath(p_pathManagerToLectureAssetsDirectory.getPathToOperateOn(),
					
								resolvedFileName));
			
			// if not it must be created 
			
			p_jsonFileHandler.createFile(targetFileName);
			
			PersistentPOJO pojo = p_uploadHandler.handleUploadedFile(resolvedFileName);
			
			p_jsonFileHandler.writeToFile(targetFileName, pojo); 
		}
		 
		// after its creation, the new contents will be appended 
		
		else {
			
			

			// move temp file to target directoy 
			 
			p_fileHandler.moveFile(parameter.getScheduleFile() , 
				
					OrdinaryFileHandler.buildPath(p_pathManagerToLectureAssetsDirectory.getPathToOperateOn(),
					
								resolvedFileName));
			
			
			PersistentPOJO pojo = p_uploadHandler.handleUploadedFile(resolvedFileName);
			
			p_jsonFileHandler.appendToPersistence( targetFileName, pojo); 
		}
		
	}
}
