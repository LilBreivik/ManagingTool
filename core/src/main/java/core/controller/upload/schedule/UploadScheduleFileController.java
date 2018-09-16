package core.controller.upload.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import core.controller.parameter.schedule.ScheduleParam;
import core.controller.parameter.schedule.upload.GenericScheduleFileUploadParam;
import core.controller.upload.MasterUploadController;
import resources.components.filehandler.FileHandler;
import resources.components.filehandler.PathManager;
import resources.components.filehandler.JSON.PersistentJSONFileHandler;
import resources.error.parameter.ParameterViolationError;
import resources.utils.files.OrdinaryFileHandler;

public class UploadScheduleFileController<UploadParam extends ScheduleParam  >
														
														extends MasterUploadController {
	
	@Qualifier("PathManager to Lecture Assets")
	@Autowired 
	private PathManager pathManagerToLectureAssetsDirectory;
	 
	private PersistentJSONFileHandler<?, ?>  m_jsonFileHandler;
	
	private FileHandler m_fileHandler;
	 
	public UploadScheduleFileController(PersistentJSONFileHandler<?, ?> jsonFileHandler, 
												 FileHandler fileHandler) {
		
		 super(fileHandler);
		 m_jsonFileHandler = jsonFileHandler; 
		 m_fileHandler = fileHandler; 
	}
		
	protected void uploadScheduleFile( GenericScheduleFileUploadParam parameter) throws ParameterViolationError, InternalError, ClassCastException  {
  
		
		super.uploadFile(() -> moveUploadedFile(parameter.getScheduleFile() , OrdinaryFileHandler.buildPath(pathManagerToLectureAssetsDirectory.getPathToOperateOn(), parameter.getUploadedFileName().getResolvedUploadedFileName())),
					
								() -> {
									
									if(!m_jsonFileHandler.getFileAssetsManager().getPathManager().doesFileExistOnPath(parameter.getTargetFileName())) {
										
										m_jsonFileHandler.createFile(parameter.getTargetFileName());
										 
										System.out.println();
										System.out.println();
										
										Object test = m_fileHandler.readFile(parameter.getUploadedFileName().getResolvedUploadedFileName());
										System.out.println(test);
										System.out.println();
										
										m_jsonFileHandler.writeToFile(parameter.getTargetFileName(), 
												m_fileHandler.readFile(parameter.getUploadedFileName().getResolvedUploadedFileName()));
							
									}
									else {
										
										System.out.println(parameter.getUploadedFileName().getResolvedUploadedFileName());
										
										Object test = m_fileHandler.readFile(parameter.getUploadedFileName().getResolvedUploadedFileName());
										
										System.out.println(test);
										
										m_jsonFileHandler.appendToPersistence(parameter.getTargetFileName(),  m_fileHandler.readFile(parameter.getUploadedFileName().getResolvedUploadedFileName() ));
									}
							}
						); 
	} 
}
