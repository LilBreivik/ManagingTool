package resources.components.filehandler.XML;
 
import static resources.utils.general.Constants.XMLScheduleFilesRootNodes.course;

import java.io.File;
import java.util.Collection; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; 
import org.springframework.stereotype.Component; 
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.XML.XMLElement; 
import resources.components.filehandler.assetsmanager.FileRepositoryManager;
import resources.components.filehandler.filereader.XMLFileReader;
import resources.components.filehandler.filereader.XMLFileReaderManager;
import resources.error.ConnectionError;
import resources.fileconnection.XMLFileConnection;
import resources.utils.general.GeneralPurpose;
 
@Component 
public class LectureScheduleXMLFileHandler 
 									extends XMLFileHandler {

	@Autowired
	public LectureScheduleXMLFileHandler(@Qualifier("FileRepository Manager for Lectures Assets") FileRepositoryManager repositoryManager ) {
		super(repositoryManager , new IXMLFileReaderFactory() {
			
			@Override
			public XMLFileReader getXMLFileReader(String fileName) {
			 
				try {
					return new XMLFileReader(new XMLFileConnection( new File( repositoryManager.getPathManager().getPathToOperateOn().toFile(), 
							fileName)), 
							new XMLFileReaderManager() {
								
								@Override
								public XMLElement getRootNode() {
									
									return new XMLElement("rootNode", 
															course.toString());
								}
								
								@Override
								public Object convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO) {
									 
							 	return  LectureScheduleOfCoursePOJO.createLectureSchedulePOJO(GeneralPurpose.CollectionToList(elementsToBeConverteToPOJO));
						
								}
							});
				} catch (ConnectionError e) {
					
					return null; 
				}
				
			}
		} );

	}
 
 
	 
}

