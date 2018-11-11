package resources.components.filehandler.JSON.general;
      
import resources.components.filehandler.general.RawFileHandler; 
import resources.components.filereader.JSON.GeneralJSONFileReader;
import resources.components.filewriter.JSON.GeneralJSONFileWriter; 
import resources.utils.pathmanager.PathManager; 
 

/**
 * General JSON FileHandler
 * 
 * @genericType (POJOClass), pojo class that describes the 
 * contnt of the expected json file 
 * 
 * */
  

public  class GeneralJSONFileHandler<POJOClass>
									 extends RawFileHandler  {
 	  
	
	public GeneralJSONFileHandler( PathManager pathManager,
										GeneralJSONFileReader<POJOClass> jsonFileReader, 
										  GeneralJSONFileWriter<POJOClass> jsonFileWriter) {
		
		super(pathManager,  jsonFileReader , jsonFileWriter);
		 
	}
	 

	@Override
	public void createFile(String fileName) {
 
		super.createFile(p_PathManager.buildFileFromFileName(fileName).getName());
	}
	

	@Override
	public <ReadObjectType> ReadObjectType readFile(String fileName) {
		
		return super.readFile(p_PathManager.buildFileFromFileName(fileName).getName());
	}

	
	@Override
	public <WrittenObjectType> void writeToFile(String fileName, WrittenObjectType content) {
		
		super.writeToFile(p_PathManager.buildFileFromFileName(fileName).getName(), content);
	}
	

}

 
