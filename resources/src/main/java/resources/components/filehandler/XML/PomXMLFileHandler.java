package resources.components.filehandler.XML;
 
import static resources.utils.general.Constants.XMLScheduleFilesRootNodes.project;

import java.io.File; 
import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Pom.PomXMLPOJO;
import resources.components.elements.XML.XMLElement;
import resources.components.filehandler.PathManager;
import resources.components.filehandler.assetsmanager.FileAssetsManager;
import resources.components.filehandler.filereader.XMLFileReader;
import resources.components.filehandler.filereader.XMLFileReaderManager;
import resources.error.ConnectionError;
import resources.fileconnection.XMLFileConnection;
import resources.utils.general.GeneralPurpose;

 
@Component 
public class PomXMLFileHandler extends XMLFileHandler{

	 
	@Autowired
	public PomXMLFileHandler( @Qualifier("FileAssetsManager Manager for POM XML Assets") FileAssetsManager fileassetsmanager ) {
		super(fileassetsmanager , new IXMLFileReaderFactory() {
			

			@Override 
			public XMLFileReader getXMLFileReader(String fileName) {
		     	
				try {
				 
					
					return new XMLFileReader(new XMLFileConnection( new File( fileassetsmanager.getPathManager().getPathToOperateOn().toFile(), 
							fileName)), 
							new XMLFileReaderManager() {
								
								@Override
								public XMLElement getRootNode() {
									
									return new XMLElement("rootNode", 
											project.toString());
								}
								
								@Override
								public Object convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO) {
									 
									return  PomXMLPOJO.createPomXMLPOJO(GeneralPurpose.CollectionToList(elementsToBeConverteToPOJO));
						
								}
							});
				} catch (ConnectionError e) {
					e.printStackTrace();
					return null; 
				}
			}
			 
			
		} );
	}
}

