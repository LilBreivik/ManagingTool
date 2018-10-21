package resources.utils.pathmanager;

 
import java.io.File; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths; 
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.jayway.jsonpath.InvalidPathException;
import resources.utils.general.GeneralPurpose;
import resources.utils.general.Constants.Directory; 
import resources.error.InternalError;

/**
 * Class that handles 
 * the common path operations
 * */

public class PathManager {
 
	private Path m_pathToOperateOn;
	
	
	/**
	 * Constructor for Operations on subdirecotries on the
	 * working direcotry, if we operate on the working directory itself, 
	 * we do not need to specify any subdiretories, so the  ammount of subdiretoires 
	 * will be 0
	 * 
	 * @param (Directory[]) subdirectories
	 * */
	
	public PathManager(Directory... subdirectories) {
		
		setUpPath( GeneralPurpose.convertList((GeneralPurpose.ArrayToList(subdirectories) ), sub -> sub.toString()));
	}
	
	/**
	 * Constructor for Operations on subdirecotries on the
	 * working direcotry, if we operate on the working directory itself, 
	 * we do not need to specify any subdiretories, so the  ammount of subdiretoires 
	 * will be 0
	 * 
	 * @param (String[]) subdirectories
	 * */
	
	public PathManager(String... subdirectories) {
		
		setUpPath( GeneralPurpose.convertList((GeneralPurpose.ArrayToList(subdirectories) ), sub -> sub.toString()));
	}
   
	protected void setUpPath(List<String> subdirectories){
		
		
		// If we work on the working directory, we do not need to define 
		// the path to operate on, cause its is the working directory itself .... 
			 
		
		if(subdirectories.size() == 0) {
					
				m_pathToOperateOn = Paths.get(System.getProperty("user.dir"));
		}
			
		else {
			 
			/**
			 * At this point we have to guarantee that the directories 
			 * were set ... 
			 * */
			  
			createPath(subdirectories);
		}
	}
	
	 
	
	/**
	 * Method that creates
	 * the demanded Path 
	 * 
	 * 
	 * @param (Collection<String>) subdirectoriesAsString
	 * */
	
	protected void createPath(Collection<String> subdirectoriesAsString) {
		
		try {
				
			Path pathThatShallBeCreated = Paths.get(System.getProperty("user.dir"), GeneralPurpose. CollectionToArray( subdirectoriesAsString ));
			
			Files.createDirectories(pathThatShallBeCreated );
			
			// the demanded Path , callable via its getter 
			
			m_pathToOperateOn =  pathThatShallBeCreated; 
		}
		
		catch(InvalidPathException | IOException pathCreationError) {
			
			throw new InternalError("@Fixme path creation Error");
		} 
	}
	
	
	/**
	 * Method that deletes
	 * thePath , with all its files 
	 * */
	
	public void deletePath() {
		 		
		try {

			FileUtils.deleteDirectory(getPathToOperateOn().toFile());

		} catch (IOException e) {
			
			new InternalError("Could not delete Path " + m_pathToOperateOn.toAbsolutePath());
		}
	}
	
	/**
	 * Method that gets the Abolute Path of A File 
	 * 
	 * @param String fileName
	 * @return (Path) abolute Path of A File 
	 * @throw InternalError, if the file does not exist
	 * */
	
	public Path getPathOfFile(String fileName)  {
		 	
		File fileToCheckWhereItExists = buildFileFromFileName(fileName);
		
		if(!fileToCheckWhereItExists.getAbsoluteFile().exists()){
			
    		throw new InternalError("Cannot build path to " + fileName + " on " + m_pathToOperateOn.toAbsolutePath().toString());
    	}
		
		return fileToCheckWhereItExists.toPath();
	}
	
	
	/**
	 * Method that builds a
	 * File that belongs to the given 
	 * Path 
	 * 
	 * @param (String) fileName 
	 * @return (File) file that shall belong to the expected file 
	 * */
	
	public File buildFileFromFileName(String fileName) {
	 	
		return new File(getPathToOperateOn().toString(), 
						fileName);
	}
	
	
	/**
	 * 
	 * Method that shall return 
	 * the PATH of a file WITHOUT the ending filename ... 
	 * 
	 * @param (File), file 
	 * @return (Path), simply the Path
	 * 
	 * */
	
	public static Path extractPathFromFile(File file){
		
		return Paths.get(file.toPath().toAbsolutePath().toString().
			    substring(0,file.toPath().toAbsolutePath().toString().lastIndexOf(File.separator)));
	}
	
	
	
	/**
	 * Method that checks if 
	 * the certain path , that 
	 * belongs to the pathManager still exist 
	 * 
	 * @return (boolean) flags if the specific path does still exist 
	 * */
	
	public boolean doesPathExist() {
		 
		try {
			
			return Files.exists(getPathToOperateOn());
		}
		catch(SecurityException sceError) {
			
			throw new InternalError("Cannot Access Path due to security permissons");
			 
		} 
	}
	
	
	/**
	 * Method that checks if 
	 * a certain file , does 
	 * exist on the path 
	 * 
	 * @param (String) fileName 
	 * @return (boolean) flags if the specific path does still exist 
	 * */
	
	
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
