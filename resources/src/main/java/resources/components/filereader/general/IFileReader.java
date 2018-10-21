package resources.components.filereader.general;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
 

///  IFileReader<ReadObjectType, Connection extends GeneralFileConnection> 

public interface IFileReader<ReadObjectType> {


	 /**
	  * There are two kinds of 
 	  * FileHandler, one that does not need to build 
	  * a connection object to the file, and the ones 
	  * that just need a fileName.. 
	  * 
	  * We need to provide a unique way, 
	  * to call the expected "filereading method" for a 
	  * Callee
	  * @throws IOException 
	  * @throws ClassCastException 
	  * @throws FileNotFoundException 
	  * 
	  * 
	  */
	 
	  public abstract ReadObjectType readFile(File file) throws FileNotFoundException, ClassCastException, IOException;
 
 
}
