package resources.components.filereader.XML.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.elements.POJO.Pom.PomXMLPOJO;
import resources.components.filereader.XML.readermanager.XMLFileReaderManager; 
import resources.fileconnection.XMLFileConnection;

@Configuration
public class XMLFileReaderProvider {

	@Autowired 
	private XMLFileConnection m_xmlFileConnection; 
		 
	@Bean
	@Qualifier("XMLFileReader for CourseScheduleXMLFile")
	public XMLFileReader<PersistenceCourseSchedulePOJO> provideXMLFileReaderForCourseScheduleXMLFile(
			@Qualifier("XMLFileReaderManager for CourseScheduleXMLFile") 
					XMLFileReaderManager<PersistenceCourseSchedulePOJO>	 xmlFileReaderManager
			
			){
		
		return new XMLFileReader<PersistenceCourseSchedulePOJO>(m_xmlFileConnection, xmlFileReaderManager);
		
	}
	
	@Bean
	@Qualifier("XMLFileReader for PomXMLFile")
	public XMLFileReader<PomXMLPOJO> provideXMLFileReaderManagerForPomXMLFile(
			@Qualifier("XMLFileReaderManager for PomXMLFile") 
					XMLFileReaderManager<PomXMLPOJO>	 xmlFileReaderManager
			
			){
		
		return new XMLFileReader<PomXMLPOJO>(m_xmlFileConnection, xmlFileReaderManager);	
	}
	
	@Bean
	@Qualifier("XMLFileReader for LectureScheduleXMLFile")
	public XMLFileReader<LectureScheduleOfCoursePOJO> provideXMLFileReaderForLectureScheduleXMLFile(
			@Qualifier("XMLFileReaderManager for LectureScheduleXMLFile") 
					XMLFileReaderManager<LectureScheduleOfCoursePOJO>	 xmlFileReaderManager
			
			){
		
		return new XMLFileReader<LectureScheduleOfCoursePOJO>(m_xmlFileConnection, xmlFileReaderManager);	
	}
	
	@Bean
	@Qualifier("XMLFileReader for GeneralCourseScheduleTemplateXMLFile") 
	public XMLFileReader<LectureScheduleOfCoursePOJO> provideXMLFileReaderForGeneralCourseScheduleTemplateXMLFile(
			@Qualifier("XMLFileReaderManager for GeneralCourseScheduleTemplateXMLFile") 
					XMLFileReaderManager<LectureScheduleOfCoursePOJO>	 xmlFileReaderManager
			
			){
		
		return new XMLFileReader<LectureScheduleOfCoursePOJO>(m_xmlFileConnection, xmlFileReaderManager);	
	}
	
}
