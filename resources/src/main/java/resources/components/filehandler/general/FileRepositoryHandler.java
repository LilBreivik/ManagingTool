package resources.components.filehandler.general;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date; 
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import resources.components.filereader.utils.FileNameTranslator;
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository;
import resources.utils.pathmanager.PathManager;

/**
 *  FileHandler 
 *  that operates on files, 
 *  that must be tracked with the 
 *  expected database 
 * */

public abstract class FileRepositoryHandler 
										extends GeneralFileHandler {

	private FilesRepository m_FileRepository; 
	
	public  FileRepositoryHandler(PathManager pathManager, 
										  FilesRepository fileRepository, 
								  		  		FileNameTranslator fileNameTranslator) {
 
		super( pathManager, fileNameTranslator);
	    m_FileRepository = fileRepository; 
	}
	
	
	 
	
	@Override
	public void moveFile(File fileToBeMoved , Path targetPath) {
		
		/* 
		 * we have to modify the expected path, to store different files
		* while a single request is waiting for its transaction to succeed
		*/ 
		 
		final String expectedFileName = targetPath.toFile().getName();
		
		final Path pathToExistingFile = Paths.get(FilenameUtils. getFullPath(targetPath.toString()) , fileToBeMoved.getName());
		  
	    super.moveFile( fileToBeMoved, pathToExistingFile);
		 
	     
	    PathManager pathManagerToCurrentWorkDirectory  = new PathManager(new String[0] );
	    
		Files fileStateToUpdate = new Files();
	 	
		fileStateToUpdate.setFileName(expectedFileName); // we store the information to the row with the expected name
		fileStateToUpdate.setFileUploaded(true);
		fileStateToUpdate.setFiledeleted(false);
		fileStateToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis()));
		  
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		final String uploaderName = authentication.getName();
        
		fileStateToUpdate.setFileUploader(uploaderName);  
		fileStateToUpdate.setFilePath( pathManagerToCurrentWorkDirectory.getPathToOperateOn().relativize(pathToExistingFile  ).toString()); // we have to store the physical location of this file 
		
		
 		m_FileRepository.addNewFile(fileStateToUpdate);
		
	}
	 
	@Override
	public void deleteFile(String fileNameThatShallBeDeleted) {
		  
		File fileThatShallBeDeleted = new File( p_PathManager.getPathToOperateOn().toString(), fileNameThatShallBeDeleted);
		
		
		// logically fileName, that is used, to connect the controller with the real physically stored file 
		final String logicallyFileName = fileThatShallBeDeleted.getName();
		
		// real fileName that is physically stored, which must be first read from the database 
		
		final String translatedFileName = p_FileNameTranslator.translateFileName(logicallyFileName);
			 	
		
		// here we remove the expecting file physically 
				 
		super.deleteFile( logicallyFileName );
			
		
		// We will commit the change to the database at this point 
			
		
		Files fileStateToUpdate = new Files();
		 	
		fileStateToUpdate.setFileName(logicallyFileName); // we store the information to the row with the expected name
		
		fileStateToUpdate.setFileUploaded(false);
		
		fileStateToUpdate.setFiledeleted(true);
			
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String currentPrincipalName = authentication.getName();
			 
		final String deleterName = authentication.getName();
		 
		
		fileStateToUpdate.setFileDeleter( deleterName);
		
		fileStateToUpdate.setFileDeletedAt(new Date(System.currentTimeMillis()));
			
			 
		fileStateToUpdate.setFilePath(null); // we have to store the physical location of this file 
		 
		m_FileRepository.addNewFile(fileStateToUpdate);
			 
		
	} 

}
