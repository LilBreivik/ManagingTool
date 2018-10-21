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
  

public  class GeneralJSONFileHandler<POJOClass> extends RawFileHandler {
 	 
	private Class<?> m_castingClass;
	
	public GeneralJSONFileHandler(Class<?> castingClass, 
				PathManager pathManager  ) {
		
		super(pathManager);
		
		m_castingClass = castingClass;	 
	}
	  
	
	/**
	 * Method to create a 
	 * certain JSON-File per FileName 
	 * 
	 * @param (String) fileName, that describes the JSON File, that shall be created
	 * */
	
	public void createJSONFile(String fileName) {
		
		createFile(p_PathManager.buildFileFromFileName(fileName).getName());
	}
	  
	
	/**
	 * Method to read a 
	 * certain JSON-File per FileName 
	 * 
	 * @param (String) fileName, that describes the JSON File, that shall be read 
	 * @return (POJOClass) parsed POJO Object 
	 * */
	 
	public POJOClass readJSONFile(String fileName) {
 
	 	 
		return readFile( new GeneralJSONFileReader<POJOClass>(m_castingClass ),
				p_PathManager.buildFileFromFileName(fileName).getName());
	}
	 
	
	
	/**
	 * Method to write some content to a 
	 * certain JSON-File per FileName 
	 * 
	 * @param (String) fileName, that describes the JSON File, that shall be read 
	 * @param (POJOClass) content, that shall be applied to 
	 * */
	
	public void writeToJSONFile(String fileName, POJOClass contentObject) {
		
	//	createJSONFile(fileName);
	
		writeToFile(new GeneralJSONFileWriter<POJOClass>(), 
	 						p_PathManager.buildFileFromFileName(fileName).getName(), 
								contentObject);
	}
}

 
