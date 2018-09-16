package resources.components.filehandler.JSON;
   
    
import resources.components.filehandler.FileHandler; 
import resources.components.filehandler.assetsmanager.FileAssetsManager;
import resources.components.filehandler.filecreator.FileBuildingPlan;
import resources.components.filehandler.filecreator.JSONFileCreator;
import resources.components.filehandler.filecreator.JSONFileCreatorManager;
import resources.components.filehandler.filereader.FileNameTranslator;
import resources.components.filehandler.filereader.FileReadingPlan;
import resources.components.filehandler.filereader.JSONFileReader;
import resources.components.filehandler.filereader.JSONFileReaderManager;
import resources.components.filehandler.filewriter.JSONFileWriter; 
 

/**
 * 
 * @FIXME 
 * Put a generic inside <>..... 
 * 
 * we can do the casting from the 
 * called methods ...
 * 
 * */

public class JSONFileHandler extends FileHandler {
 	 
	private JSONFileCreatorManager m_JSONFileCreatorManager;
	
	private JSONFileReaderManager m_JSONFileReaderManager;
	
	private JSONFileCreator m_JSONFileCreator; 	
		
	private JSONFileReader m_JSONFileReader; 
	 
	private JSONFileWriter m_JSONFileWriter;
	
	private Class<? > m_pojoClass;
	 
	 
	public JSONFileHandler(Class<? > pojoClass, 
								FileAssetsManager fileAssetsManager) {
		super(fileAssetsManager);
 		  
		
		m_pojoClass = pojoClass;
		
	    m_JSONFileCreatorManager = new JSONFileCreatorManager( m_pojoClass);
	    
	    m_JSONFileReaderManager = new JSONFileReaderManager( m_pojoClass);
	 
	    m_JSONFileCreator = new JSONFileCreator(p_fileAssetsManager.getPathManager(), m_JSONFileCreatorManager);  
	  
	    m_JSONFileReader = new JSONFileReader(p_fileAssetsManager.getPathManager(), m_JSONFileReaderManager);
	 
	    m_JSONFileWriter = new JSONFileWriter(p_fileAssetsManager.getPathManager());
		 
	}
	 

	public void createFile(String fileName) {
		
		p_fileAssetsManager.createFile(new FileBuildingPlan() {
			
			@Override
			public void buildFile() {
				 
				 m_JSONFileCreator.createFile(fileName);
			}
		});
	}
	
	@Override
	public Object readFile(String fileName) {
		
		// FIXME: think about a possibility to do a class cast exception
		
		return p_fileAssetsManager.readFile(new  FileReadingPlan() {
			
			@Override
			public Object readFile(FileNameTranslator translator) {
				
				return  m_pojoClass.cast(m_JSONFileReader.readFile(fileName));
			}
		});
			
	}	
 
	public void writeToFile(String fileName, Object contentObject) {
	
		// first check if the file exists , if not 
		// we will try to create it.. 
		 
		p_fileAssetsManager.getPathManager().getPathOfFile(fileName);
		
		p_fileAssetsManager.writeToFile(new FileBuildingPlan() {
			
			@Override
			public void buildFile() { 
				
				m_JSONFileWriter .writeToFile(fileName, m_pojoClass.cast(contentObject) );
			}
		});
	}
	
}
