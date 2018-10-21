package resources.components.filehandler.XML.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.XML.general.RawXMLFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;
import resources.components.filereader.XML.XMLFileReaderManager;
import resources.components.filereader.utils.FileNameTranslator;
import resources.database.repository.FilesRepository;
import resources.utils.pathmanager.PathManager;

@Configuration 
public class XMLFileHandlerProvider {
 
	@Autowired 
	@Qualifier("PathManager to JSONFiles")
	private PathManager pathManagerToJSONFiles;
	
	 
	@Bean
	@Qualifier("XMLFileHandler for LectureScheduleXMLFiles")
	public GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>  provideLectureScheduleXMLFileHandler( 
		FilesRepository  filesRepository ,   
		@Qualifier("PathManager to Lecture Assets") PathManager pathManagerToLectureAssets,
	      	@Qualifier("FileNameTranslator that uses the Database")	FileNameTranslator translator, 
	       		@Qualifier("XMLFileReaderManager for LectureScheduleXMLFile")  XMLFileReaderManager<LectureScheduleOfCoursePOJO> xmlFileReaderManager){

		
		return new  GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>(filesRepository,
															pathManagerToLectureAssets,
														 translator,
												 xmlFileReaderManager);
	}
	
	
	@Bean
	@Qualifier("XMLFileHandler for GeneralCourseScheduleTemplateXMLFile")
	public RawXMLFileHandler<LectureScheduleOfCoursePOJO>  provideGeneralCourseScheduleTemplateXMLFile( 
	         	@Qualifier("XMLFileReaderManager for GeneralCourseScheduleTemplateXMLFile")  XMLFileReaderManager<LectureScheduleOfCoursePOJO> xmlFileReaderManager){
 
		return new RawXMLFileHandler<LectureScheduleOfCoursePOJO>(pathManagerToJSONFiles, xmlFileReaderManager);
		 
	}
	
	 
	@Bean
	@Qualifier("XMLFileHandler for CourseScheduleXMLFile")
	public RawXMLFileHandler<PersistenceCourseSchedulePOJO>  provideCourseScheduleXMLFile( 
			@Qualifier("PathManager to Course Assets") PathManager pathManagerToCourseAssets,
	         	@Qualifier("XMLFileReaderManager for CourseScheduleXMLFile")  XMLFileReaderManager<PersistenceCourseSchedulePOJO> xmlFileReaderManager){
 
		return new RawXMLFileHandler<PersistenceCourseSchedulePOJO>(pathManagerToCourseAssets, xmlFileReaderManager);
	}	 
	 
}
