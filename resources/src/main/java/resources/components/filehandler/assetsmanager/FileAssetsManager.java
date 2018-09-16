package resources.components.filehandler.assetsmanager;


import java.io.File;
import java.io.IOException; 
import java.nio.file.Path; 
import resources.components.filehandler.PathManager;
import resources.components.filehandler.filecreator.FileBuildingPlan;
import resources.components.filehandler.filereader.FileNameTranslator;
import resources.components.filehandler.filereader.FileReadingPlan;
import resources.utils.files.OrdinaryFileHandler;

/**
 * Class to handle 
 * every File Operation... 
 * 
 * Contains all information
 * of the path , whre the file 
 * will be stored, and it manages 
 * the initialization
 * 
 * */


public class FileAssetsManager{

	protected  PathManager p_PathManager; 
	 
	protected FileNameTranslator p_fileNameTranslator; 
	
	public FileAssetsManager(PathManager planBuilderForTheDemandedPath, 
								FileNameTranslator fileNameTranslator) {
		
		p_fileNameTranslator = fileNameTranslator;
		p_PathManager = planBuilderForTheDemandedPath;	
		 
	}
	
	
	/**
	 * General Method to delete a File 
	 * */
	
	
	public void deleteFile(File fileThatShallBeDeleted)
	{ 
		try {
		  
			OrdinaryFileHandler.deleteFile(fileThatShallBeDeleted);	
		} 
		catch (InternalError fileNotEx) {
			
			fileNotEx.printStackTrace();
		}
		catch(SecurityException sEX) {
			  
			  sEX.printStackTrace();
		}
    		 
	}
	/**
	 * 
	 * General Method to move a file 
	 * 
	 * A moved File, will overwrite any existing file
	 * 
	 * @param (String) fileName, name of the file that shall be moved 
	 * 			(Path) targetPath, path where the file shall be moved to 
	 * */
	
 
	public void moveFile(File  file, Path targetPath) { 
		
		try {
			
			OrdinaryFileHandler.moveFile(file, targetPath);
		
		} catch (IOException e) {
			 
			e.printStackTrace();
			
			throw new resources.error.InternalError("Cannot move File " + file.getName());
		}
	}
	
	/**
	 * 
	 * General Method to create a file 
	 * 
	 * @param (FileBuilderPlan) fileBuilderPlan, plan that is needed 
	 * to build a file  
	 * */
	
	public void createFile(FileBuildingPlan fileBuilderPlan) {
		
		fileBuilderPlan.buildFile();	
	}
	
	/**
	 * General Method to read a file 
	 * 
	 * @param (FileBuilderPlan) fileBuilderPlan, plan that is needed 
	 * to build a file  
	 * */

	public  Object readFile(FileReadingPlan fileReaderPlan) {
	 
		
		return fileReaderPlan.readFile(p_fileNameTranslator);
	
	}

	/**
	 * General Method to write to a file 
	 * 
	 * We seperate Creating and writong to files due to 
	 * keep the code "more lean", and to avoid some possible 
	 * coherncy dependencies 
	 * 
	 * @param (FileBuilderPlan) fileBuilderPlan, plan that is needed 
	 * to build a file  
	 * */
	 
	
	public void writeToFile(FileBuildingPlan fileBuildingPlan) {
		 
		fileBuildingPlan.buildFile();	
	}
	

	public PathManager getPathManager() {
		
		return p_PathManager;
	}
 
	public FileNameTranslator getFileNameTranslator() {
		
		return p_fileNameTranslator;
	}

}
