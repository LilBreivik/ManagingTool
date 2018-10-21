package resources.components.filehandler.XML.general;
 
import resources.components.filehandler.general.FileRepositoryHandler; 
import resources.components.filereader.XML.XMLFileReader;
import resources.components.filereader.XML.XMLFileReaderManager;
import resources.components.filereader.utils.FileNameTranslator;
import resources.database.repository.FilesRepository; 
import resources.fileconnection.XMLFileConnection;
import resources.utils.pathmanager.PathManager;

public  class GeneralXMLFileHandler<POJOClass> 
										extends FileRepositoryHandler 
													implements IXMLFileHandler<POJOClass>{
 
	private  XMLFileReaderManager<POJOClass> m_xmlFileReaderManager; 
	  

	public GeneralXMLFileHandler(FilesRepository fileRepository, 
									PathManager pathManager,
										FileNameTranslator fileNameTranslator,
											XMLFileReaderManager<POJOClass> xmlFileReaderManager ) {
		
		super(pathManager, 
					fileRepository, 
						fileNameTranslator);
		 
		m_xmlFileReaderManager = xmlFileReaderManager;
	}
	  
	
	@Override 
	public POJOClass readXMLFile(String fileName) {
 
		return  readFile( new XMLFileReader<POJOClass>(new XMLFileConnection(p_PathManager.getPathToOperateOn()), 
								m_xmlFileReaderManager), 
									p_PathManager.buildFileFromFileName(fileName).getName());
	}	 
}
