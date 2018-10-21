package resources.components.filehandler.XML.general;
 

import resources.components.filehandler.general.RawFileHandler;
import resources.components.filereader.XML.XMLFileReader;
import resources.components.filereader.XML.XMLFileReaderManager;
import resources.fileconnection.XMLFileConnection;
import resources.utils.pathmanager.PathManager;

public class RawXMLFileHandler<POJOClass> 
						extends RawFileHandler
								implements IXMLFileHandler<POJOClass> {

	private  XMLFileReaderManager<POJOClass> m_xmlFileReaderManager; 
	 
	public RawXMLFileHandler(PathManager pathManagerToJSONFiles,
			XMLFileReaderManager<POJOClass> xmlFileReaderManager) { 
		
		super(pathManagerToJSONFiles);
		
		m_xmlFileReaderManager = xmlFileReaderManager;
	}

	@Override
	public POJOClass readXMLFile(String fileName) {
		 
		return  readFile( new XMLFileReader<POJOClass>(new XMLFileConnection(p_PathManager.getPathToOperateOn()), 
				m_xmlFileReaderManager),  fileName);
	}
}
