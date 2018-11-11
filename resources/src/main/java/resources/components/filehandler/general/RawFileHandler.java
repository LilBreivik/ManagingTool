package resources.components.filehandler.general;
  

import resources.components.filereader.general.IFileReader;
import resources.components.filewriter.general.IFileWriter;  
import resources.utils.pathmanager.PathManager; 

/**
 *  FileHandler 
 *  that operates directly on the 
 *  expected files 
 * */

public abstract class RawFileHandler 
									extends GeneralFileHandlerImplOfAPI  {
  
	
	public RawFileHandler(PathManager pathManager,
			IFileReader  fileReader) {

		// the fileNameTranslator will be null, cause the given files are the already ohysically stored ones 
		
		super(pathManager, null, fileReader);
	}
	
	
	public RawFileHandler(PathManager pathManager,
							IFileReader  fileReader, 
								IFileWriter  fileWriter) {

		// the fileNameTranslator will be null, cause the given files are the already ohysically stored ones 
		
		super(pathManager, null, fileReader,  fileWriter);
	}
	
	@Override 
	public  void createFile( String fileName) {
		
		super.createFile(fileName);
	}
	
	
	@Override
	public <ReadObjectType> ReadObjectType  readFile(String fileName) {
	 
		return super.readFile(fileName);
	}
	
	
	@Override
	public < WrittenObjectType> void writeToFile( String fileName, WrittenObjectType content) {
		 
	   super.writeToFile(fileName, content);
	}
	
}
	  
