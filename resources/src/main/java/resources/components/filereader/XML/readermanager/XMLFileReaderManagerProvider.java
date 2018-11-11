package resources.components.filereader.XML.readermanager;

import static resources.utils.general.Constants.XMLScheduleFilesRootNodes.course;
import static resources.utils.general.Constants.XMLScheduleFilesRootNodes.courses;
import static resources.utils.general.Constants.XMLScheduleFilesRootNodes.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import resources.components.elements.POJO.Course.CourseSchedulePOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.elements.POJO.Pom.PomXMLPOJO;
import resources.components.elements.XML.XMLCourseElement;
import resources.components.elements.XML.XMLElement;
import resources.utils.general.GeneralPurpose;

@Configuration
public class XMLFileReaderManagerProvider {

	@Bean
	@Qualifier("XMLFileReaderManager for CourseScheduleXMLFile")
	public XMLFileReaderManager<PersistenceCourseSchedulePOJO> provideXMLFileReaderManagerForCourseScheduleXMLFile() {
		
		return new XMLFileReaderManager<PersistenceCourseSchedulePOJO>() {
			
			@Override
			public XMLElement getRootNode() {
				
				return new XMLElement("rootNode", 
										courses.toString());
			}
			
			@Override
			public PersistenceCourseSchedulePOJO convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO) {
				 
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
		};
	}
	
	
	@Bean
	@Qualifier("XMLFileReaderManager for PomXMLFile")
	public XMLFileReaderManager<PomXMLPOJO> provideXMLFileReaderManagerForPomXMLFile()  {
		
		return new  XMLFileReaderManager<PomXMLPOJO>(){

			@Override
			public XMLElement getRootNode() {
				
				return new XMLElement("rootNode", project.toString());
			}

			@Override
			public PomXMLPOJO convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO) {
				 
				return  PomXMLPOJO.createPomXMLPOJO(GeneralPurpose.CollectionToList(elementsToBeConverteToPOJO));
	
			}
		};
	}
	
	
	@Bean
	@Qualifier("XMLFileReaderManager for LectureScheduleXMLFile")
	public XMLFileReaderManager<LectureScheduleOfCoursePOJO> provideXMLFileReaderManagerForLectureScheduleXMLFile()  {
		
		return new  XMLFileReaderManager<LectureScheduleOfCoursePOJO>(){

			@Override
			public XMLElement getRootNode() {
				
				return new XMLElement("rootNode", 
										course.toString());
			}

			@Override
			public LectureScheduleOfCoursePOJO convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO) {
				 
			 	return  LectureScheduleOfCoursePOJO.createLectureSchedulePOJO(GeneralPurpose.CollectionToList(elementsToBeConverteToPOJO));
		
			}
		};
	}
	
	
	@Bean
	@Qualifier("XMLFileReaderManager for GeneralCourseScheduleTemplateXMLFile")
	public XMLFileReaderManager<LectureScheduleOfCoursePOJO> provideXMLFileReaderManagerForGeneralCourseScheduleTemplateXMLFile()  {
		
		return new  XMLFileReaderManager<LectureScheduleOfCoursePOJO>(){

			@Override
			public XMLElement getRootNode() {
				
				return new XMLElement("rootNode", 
										course.toString());
			}

			@Override
			public LectureScheduleOfCoursePOJO convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO) {
				 
			 	return  LectureScheduleOfCoursePOJO.createLectureSchedulePOJO(GeneralPurpose.CollectionToList(elementsToBeConverteToPOJO));
		
			}
		};
	}
}
