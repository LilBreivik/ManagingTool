package resources.components.filehandler.XML;

import static resources.utils.general.Constants.XMLScheduleFilesRootNodes.courses;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; 
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Course.CourseSchedulePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.elements.XML.XMLCourseElement;
import resources.components.elements.XML.XMLElement;
import resources.components.filehandler.PathManager;
import resources.components.filehandler.assetsmanager.FileAssetsManager;
import resources.components.filehandler.filereader.XMLFileReader;
import resources.components.filehandler.filereader.XMLFileReaderManager;
import resources.error.ConnectionError;
import resources.fileconnection.XMLFileConnection;
import resources.utils.general.GeneralPurpose;

 
/**
 * 
 * Class that configures the 
 * JSON File Hanlder for the Course Schedules
 * 
 * (originally delivered as XML File)
 *  
 *  
 * @FIXME 
 * 
 * Caution BoilerplateCode 
 * 
 * If we change pathBuilder , this needs to be updated 
 * 
 * PathBuilder builderForRequestedPath = new PathBuilder(requestedPathManager.createPathBuildingPlan());
		
 * 
 * */


@Component 
public class CourseScheduleXMLFileHandler extends XMLFileHandler {

	 
	@Autowired
	public CourseScheduleXMLFileHandler(@Qualifier("FileAssetsManager for Course Schedule XML Assets")   FileAssetsManager fileassetsmanager  ) {
		super(fileassetsmanager, new IXMLFileReaderFactory() {
			

			@Override 
			public XMLFileReader getXMLFileReader(String fileName) {
		    		
					try {
						
						return new XMLFileReader(new XMLFileConnection( new File( fileassetsmanager.getPathManager().getPathToOperateOn().toFile(), 
								fileName)), 
								new XMLFileReaderManager() {
									
									@Override
									public XMLElement getRootNode() {
										
										return new XMLElement("rootNode", 
																courses.toString());
									}
									
									@Override
									public Object convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO) {
										 
										List<XMLElement> listOfElementsToBeConverteToPOJO = GeneralPurpose.CollectionToList(elementsToBeConverteToPOJO);
										
										Collection< CourseSchedulePOJO> courseScheduleInformation = new ArrayList< CourseSchedulePOJO>();
										
										for(int itr = 0 ; itr < listOfElementsToBeConverteToPOJO .size(); itr += 1) {
											
											if(!(listOfElementsToBeConverteToPOJO .get(itr).getM_elementName().equals("course") 
																	|| 
																	listOfElementsToBeConverteToPOJO .get(itr).getM_elementName().equals("courses"))) {
												
												courseScheduleInformation .add(
														
														CourseSchedulePOJO.createCoursePOJO(XMLCourseElement. createCourseElement (listOfElementsToBeConverteToPOJO .subList(itr, itr + 3)))
														
														);
												
												itr += 2;
											}
										} 
										
										return PersistenceCourseSchedulePOJO.createCourseSchedulePOJO(courseScheduleInformation);
									}
								});
					} catch (ConnectionError e) {
						
						return null;
					}
				 
			}
			
		} );
	}
 
}

