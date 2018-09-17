package resources.components.filehandler.assetsmanager;
 
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository; 
import resources.components.filehandler.PathManager;
import resources.components.filehandler.filereader.FileNameTranslator;
import resources.components.filehandler.filereader.FileReadingPlan;
import resources.error.InternalError;

/**
 * Class that handles
 * 
 * file access that needs
 * to be tracked by the database
 * 
 * */

public class FileRepositoryManager  extends FileAssetsManager {
	
	private FilesRepository m_FileRepository;
	
	public FileRepositoryManager(PathManager planBuilderForTheDemandedPath,
									FilesRepository fileRepository,
										FileNameTranslator fileNameTranslator) {
		
		super(planBuilderForTheDemandedPath, fileNameTranslator); 
		
		m_FileRepository =  fileRepository;
		
	}
 

	@Override
	public void deleteFile(File  fileToBeDeleted) {
	 
		System.out.println();
		
		final String fileNameToTranslate = p_fileNameTranslator.translateFileName(fileToBeDeleted.getName());
		  
		
		System.out.println(getPathManager().getPathOfFile(fileNameToTranslate).toFile().toString());
	 	
		// here we remove the expecting file physically 
			
		super.deleteFile(getPathManager().getPathOfFile(fileNameToTranslate).toFile());
		
		// We will commit the change to the database at this point 
		
		m_FileRepository.delete(fileToBeDeleted.getName());
		 
	}
  
	@Override
	public void moveFile(File file, Path targetPath) {
		
		/* 
		 * we have to modify the expected path, to store different files
		* while a single request is waiting for its transaction to succeed
		*/ 
		 
		final String expectedFileName = targetPath.toFile().getName();
		
		final Path pathToExistingFile = Paths.get(FilenameUtils. getFullPath(targetPath.toString()) , file.getName());
		
		System.out.println("");
		  
	    super.moveFile( file, pathToExistingFile);
		 
	    
	    
	    PathManager pathManagerToCurrentWorkDirectory  = new PathManager( );
	    
		Files fileToUpdate = new Files();
	 	
		fileToUpdate.setFileName(expectedFileName); // we store the information to the row with the expected name
		fileToUpdate.setFileUploaded(true);
		fileToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis()));
		
	//	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
	//	final String uploaderName = authentication.getName();
		
		fileToUpdate.setFileUploader("Rainer Winkler"); 
//		fileToUpdate.setFileUploader(uploaderName); 
		fileToUpdate.setFilePath( pathManagerToCurrentWorkDirectory.getPathToOperateOn().relativize(pathToExistingFile  ).toString()); // we have to store the physical location of this file 
		
		
 		m_FileRepository.addNewFile(fileToUpdate);
		
	}
	
 

}
