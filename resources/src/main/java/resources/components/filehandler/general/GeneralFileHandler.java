package resources.components.filehandler.general;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path; 
import resources.components.filereader.general.IFileReader;
import resources.components.filereader.utils.FileNameTranslator; 
import resources.error.FileIsMissingError;
import resources.error.InternalError;
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.pathmanager.PathManager; 

/**
 * General class for filehandling 
 * operations
 * 
 * Every Filehandler-Class needs to 
 * inherit...
 * 
 * 
 * The class will just return "Object" objects, 
 * the inherits need to cast the returned values.
 *  
 * */


public abstract class GeneralFileHandler {
 
	/**
	 * We need a way to tanslate the name 
	 * of a demanded file, to the name of the physically 
	 * stored one 
	 * */
	
	protected FileNameTranslator p_FileNameTranslator;
	  
	protected PathManager p_PathManager;
	
	public GeneralFileHandler(PathManager pathManager, 
											FileNameTranslator fileNameTranslator) {
		
		p_FileNameTranslator = fileNameTranslator; 
		
		p_PathManager = pathManager;
	}
	 
	/**
	 * This method translates a certain
	 * file, to its physically stored counterpart, 
	 * if it is alreads stored !!! 
	 * 
	 * If it is not stored,it shall throw an internalError Exception
	 * 
	 * If the FileNameTranslator is not defined, we will assume, 
	 * the given File does already  represents the needed file 
	 * 
	 * @throw InternalError (unchecked)
	 * 
	 * */
	
	public File determinePhysicallyStoredFile(String fileNameThatShallBeTranslated) {
		
		
		File fileThatShallBeDeleted = new File( p_PathManager.getPathToOperateOn().toString(),
													fileNameThatShallBeTranslated);
		
	   if(p_FileNameTranslator == null) {
		   
		   return fileThatShallBeDeleted;
	   }	
	   else {
		   
		   final String filePath = fileThatShallBeDeleted.toPath().toAbsolutePath().toString().
		    substring(0, fileThatShallBeDeleted.toPath().toAbsolutePath().toString().lastIndexOf(File.separator));
		       
		   return 	new File( filePath, 
				  				p_FileNameTranslator.translateFileName( fileThatShallBeDeleted.getName()));   
	   }
	}
		
	/**
	 * API for general file handling methods 
	 * */
	
	 
	/**
	 * General Method to delete a File 
	 * 
	 * @param (String) fileName
	 * 
	 * @throw InternalError (unchecked)
	 * */
	 
	public void deleteFile(String fileName){ 
	
		try {
		  
			OrdinaryFileHandler.deleteFile(determinePhysicallyStoredFile(fileName));	
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
	 * Method to read a File, by a certain plan, that describes the needed
	 * "mechanism"
	 * 
	 * @param fileReader (IFileReader<ReadFileType>, the needed "mechanism" to describe how a certain file shall be read 
	 * @param (String) fileName
	 * @return (ReadFileType), an object that got the contents of a read file  
	 * 
	 * @throw FileIsMissingError (uncheked)
	 * @throw InternalError  (uncheked)
	 * */
	 
	protected  <ReadFileType> ReadFileType readFile(IFileReader<ReadFileType> fileReader, String fileName) {
		
		ReadFileType dummyObject = null;	
		
		try {
			   
			
			dummyObject = fileReader.readFile(determinePhysicallyStoredFile(fileName));
	    }
		catch (FileNotFoundException fileNotThereError) {
			  
			   throw  new FileIsMissingError("");
	    } 
	    catch (ClassCastException |  IOException  internalError) {
			  
		    throw new InternalError("");
			
		}
	    	   
	    return dummyObject;	
		
	} 
  
	/**
	 * 
	 * Method to move a file , per fileName 
	 * 
	 * A moved File, will overwrite any existing file
	 * 
	 * @param (String) fileName
	 * @param (Path) targetPath, path where the file shall be moved to 
	 * 
	 * @throw InternalError  (uncheked)
	 * 
	 * 
	public void moveFile(String fileName, Path targetPath) { 
		
		try {
			
			OrdinaryFileHandler.moveFile(determinePhysicallyStoredFile(fileName), targetPath);
		
		} catch (IOException e) {
			 
			throw new InternalError("Cannot move File " +  fileName);
		}
	}*/
	
	/**
	 * 
	 * Method to move a file 
	 * 
	 * A moved File, will overwrite any existing file
	 * 
	 * @param (File) file
	 * @param (Path) targetPath, path where the file shall be moved to 
	 * 
	 * @throw InternalError  (uncheked)
	 * 
	 * */
	public void moveFile(File file, Path targetPath) { 
		
		try {
			
			OrdinaryFileHandler.moveFile(file, targetPath);
		
		} catch (IOException e) {
			 
			throw new InternalError("Cannot move File " +  file.getName());
		}
	}
	
	
	/**
	 * 
	 * General method to check, 
	 * 
	 * if a file does exist ... 
	 * 
	 * @param (String), fileName
	 * @return (boolean), flag indicating , if the certain file does exist 
	 * 
	 * */
	public boolean checkIfFileDoesExist(String fileName) { 
		
		try {
			
		    p_PathManager.getPathOfFile(determinePhysicallyStoredFile(fileName).getName());
		
		    return true;
		     
		} catch (InternalError fileDoesNotExistError) {
			 
			return false; 
		}
	}
	 

	public PathManager getPathManager() {
		
		return p_PathManager; 
	}

	 
}
