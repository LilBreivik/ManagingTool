package resources.components.filehandler.XML.general;
 
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.filehandler.general.FileRepositoryHandler;
import resources.components.filereader.XML.reader.XMLFileReader;
import resources.components.filereader.XML.readermanager.XMLFileReaderManager;
import resources.components.filereader.utils.FileNameTranslator;
import resources.database.repository.FilesRepository;  
import resources.utils.pathmanager.PathManager;

public  class GeneralXMLFileHandler<POJOClass> 
										extends FileRepositoryHandler {
   
	public GeneralXMLFileHandler(FilesRepository fileRepository, 
									PathManager pathManager,
										FileNameTranslator fileNameTranslator,
												XMLFileReader<POJOClass> fileReader) {
		
		super(pathManager, 
					fileRepository, 
						fileNameTranslator, 
							fileReader);
		  
	}
	  
	
	 

 





	@Override 
	public POJOClass readFile(String fileName) {
 
		return  super.readFile( p_PathManager.buildFileFromFileName(fileName).getName());
	}	 
}
