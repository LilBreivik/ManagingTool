package resources.components.filehandler.XML;

import resources.components.filehandler.FileHandler; 
import resources.components.filehandler.assetsmanager.FileAssetsManager;
import resources.components.filehandler.assetsmanager.FileRepositoryManager;
import resources.components.filehandler.filereader.FileNameTranslator;
import resources.components.filehandler.filereader.FileReadingPlan;
import resources.components.filehandler.filereader.XMLFileReader;

public  class XMLFileHandler extends FileHandler {
 
	private IXMLFileReaderFactory m_ixmlFileReaderFactory;
	 
	protected XMLFileReader p_xmlFileReader;
	
	public XMLFileHandler( FileAssetsManager fileAssetsManager, IXMLFileReaderFactory ixmlFileReaderFactory) {
		super(fileAssetsManager);
		 
		m_ixmlFileReaderFactory = ixmlFileReaderFactory;
	}
	
	
	public XMLFileHandler( FileRepositoryManager repositoryManager, IXMLFileReaderFactory ixmlFileReaderFactory) {
		super(repositoryManager);
		 
		m_ixmlFileReaderFactory = ixmlFileReaderFactory;
	}
	
	
	@Override
	public Object readFile(String fileName) {
		
		return p_fileAssetsManager .readFile(new FileReadingPlan() {
			
			@Override
			public Object readFile(FileNameTranslator translator) {
				
				return m_ixmlFileReaderFactory.getXMLFileReader(translator.translateFileName(fileName)).readFile();
			}
		});
		 
	}
}
