package resources.components.filehandler;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList; 
import java.util.Collection; 

import com.jayway.jsonpath.InvalidPathException;

import resources.utils.general.GeneralPurpose;
import resources.utils.general.Constants.Directory;
 

import resources.error.InternalError;


public class PathManager {

	private Collection<String> m_subdirectoriesAsString;

	private Path m_pathToOperateOn;
	
	
	
	/**
	 * Constructor for Operations on subdirecotries on the
	 * working direcotry, if we operate on the working directory we need 
	 * do not specify any subdiretories, so we ammount of subdiretoires 
	 * will be 0
	 * */
	
	public PathManager(Directory... subdirectories) {
		
		// If we work on the working directory, we do not need to define 
		// the path to operate on, cause its is the working directory itself .... 
		
		m_subdirectoriesAsString = new ArrayList<>();
		
		if(subdirectories.length == 0) {
			
			m_pathToOperateOn = Paths.get(System.getProperty("user.dir"));
		}
	
		else {
			
			// we need to convert the  (Collection<Directory> ) to
			// a List<String> to be convinient to the underlysing path building method ...
			
			m_subdirectoriesAsString = GeneralPurpose.convertList((new ArrayList(GeneralPurpose.ArrayToCollection(subdirectories) )), sub -> sub.toString());
			 
			initialize();
		}
	
	}
	
	 
	
	
	/**
	 * Method that shall ensure 
	 * all needed Assets do exist before executing 
	 * any further Actions, that could let to bigger failures... 
	 * */
	
	private void initialize() {
		
		createPath();
	}
	
	
	/**
	 * Metod that is need 
	 * to access the other projects...
	 * */
	
	public void changeParentDirectory(Directory newParent){
		
		Path orphanPath = Paths.get(System.getProperty("user.dir")).getParent();
		
		Path adoptedByNewParentPath = Paths.get(orphanPath.toString(), newParent.toString() );

		m_pathToOperateOn =  Paths.get(adoptedByNewParentPath.toString(),   (m_subdirectoriesAsString.size() != 0) ?  GeneralPurpose. CollectionToArray(m_subdirectoriesAsString )  : new String[0]);
	
	}
	
	
	/**
	 * Method that created the 
	 * demanded Path 
	 * 
	 * @FIXME: custom exceptions are missing to handle 
	 * directory creation exceptions ... 
	 * */
	
	public void createPath() {
		
		try {
				
			 	
			Path pathThatShallBeCreated = Paths.get(System.getProperty("user.dir"), GeneralPurpose. CollectionToArray(m_subdirectoriesAsString ));
			
			Files.createDirectories(pathThatShallBeCreated );
			
			m_pathToOperateOn =  pathThatShallBeCreated; 
		}
		
		catch(InvalidPathException  e) {
			
			e.printStackTrace();
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void deletePath() {}
	
	public void deletePathAndSubdirectories() {}
	
	
	/**
	 * Method that gets the Abolute Path of A File 
	 * 
	 * @param String fileName
	 * @return (Path) abolute Path of A File 
	 * @throw FileNotFoundException, if the file does not exist
	 * */
	
	public Path getPathOfFile(String fileName) throws InternalError{
		 	
		File fileToCheckWhereItExists = new File(m_pathToOperateOn.toAbsolutePath().toString(), fileName);
		
		if(!fileToCheckWhereItExists.getAbsoluteFile().exists()){
			
    		throw new InternalError("Cannot build path to " + fileName + " on " + m_pathToOperateOn.toAbsolutePath().toString());
    	}
		
		return fileToCheckWhereItExists.toPath();
	}
	
	
	public boolean doesPathExist() {
		 
		try {
			
			return Files.exists(m_pathToOperateOn);
		}
		catch(SecurityException sceError) {
			
			throw new InternalError("Cannot Access Path due to security permissons");
			 
		} 
	}
	
	
	public boolean doesFileExistOnPath(String fileName) {
		 
		try {
			
			if(doesPathExist()) {
			
				getPathOfFile(fileName);
					
				return true;
			}
		} 
		catch(InternalError internalError) {
			
			return false;
		}
	 
		return false;
	}
	
	
	
	/**
	 * Method that returns the 
	 * demanded Path 
	 * 
	 * @return (Path) demanded Path 
	 * */
	
	public  Path getPathToOperateOn() {
		
		return m_pathToOperateOn; 
	}
}
