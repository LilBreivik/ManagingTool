package resources.components.filehandler.general;
 
import java.io.File;
import java.io.IOException; 
import resources.components.filewriter.general.IFileWriter; 
import resources.error.InternalError;
import resources.utils.pathmanager.PathManager; 

/**
 *  FileHandler 
 *  that operates directly on the 
 *  expected files 
 * */

public abstract class RawFileHandler 
									extends GeneralFileHandler{
  
	public RawFileHandler(PathManager pathManager) {

		// the fileNameTranslator will be null, cause the given files are the already ohysically stored ones 
		
		super(pathManager, null);
	}
	
	/**
	 * 
	 * General Method to create a file 
	 *  
	 * @param (String) fileName
	 * to build a file  
	 * 
	 * @throw InternalError (unchecked)
	 * */
	 
	protected  void createFile( String fileName) {
		
		File fileThatShallBeCreated = new File(p_PathManager.getPathToOperateOn().toString() , fileName);
		
		try {
						  
			  fileThatShallBeCreated .createNewFile();
		}  
		catch (IOException  creationError) {
			 
			  throw new InternalError("Die Datei " + fileThatShallBeCreated.getName() + " kann nicht erzeugt werden ");
		}
	}
	
	
	/**
	 * 
	 * Method to write to a File 
	 * 
	 * @param fileWriter (IFileWriter<WrittenObjectType>, the needed "mechanism" to describe how a certain file shall be written to  
	 * @param fileName (String), fileName of the certain 
	 * @param content (WrittenObjectType), the content, that shall be written to the persistent File 
	 * 
	 *  
	 * @throw InternalError (unchecked)
	 * 
	 * */
	
	protected  <WrittenObjectType> void writeToFile( IFileWriter<WrittenObjectType> fileWriter, 
														String fileName, 
															   WrittenObjectType  content) {
		

		File fileThatShallBeAppended = new File(p_PathManager.getPathToOperateOn().toString() , fileName);
		
		
		try {
			
			fileWriter.writeToFile(fileThatShallBeAppended, content);
		
		} catch (IOException e) {
		
			  throw new InternalError("Die Datei " + fileThatShallBeAppended.getName() + " kann nicht verarbeitet werden ");
		}
	}
	
}
	  
