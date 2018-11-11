package resources.components.filehandler.XML.provider;
 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.XML.general.RawXMLFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;
import resources.components.filereader.XML.reader.XMLFileReader; 
import resources.components.filereader.utils.FileNameTranslator;
import resources.database.repository.FilesRepository;
import resources.utils.pathmanager.PathManager;

@Configuration 
public class XMLFileHandlerProvider {
 
	 
	@Bean
	@Qualifier("XMLFileHandler for LectureScheduleXMLFiles")
	public GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>  provideLectureScheduleXMLFileHandler( 
		FilesRepository  filesRepository ,   
		@Qualifier("PathManager to Lecture Assets") PathManager pathManagerToLectureAssets,
	      	@Qualifier("FileNameTranslator that uses the Database")	FileNameTranslator translator, 
	       		@Qualifier("XMLFileReader for LectureScheduleXMLFile")  XMLFileReader<LectureScheduleOfCoursePOJO> xmlFileReader){

		
		return new  GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>(filesRepository,
															pathManagerToLectureAssets,
														 translator,
												 xmlFileReader);
	}
	 
	 
	@Bean
	@Qualifier("XMLFileHandler for GeneralCourseScheduleTemplateXMLFile")
	public RawXMLFileHandler<LectureScheduleOfCoursePOJO>  provideGeneralCourseScheduleTemplateXMLFile( 
			@Qualifier("PathManager to Lecture Assets") PathManager pathManagerToLectureAssets,
	         	@Qualifier("XMLFileReader for GeneralCourseScheduleTemplateXMLFile")  XMLFileReader<LectureScheduleOfCoursePOJO> xmlFileReaderManager){
 
		return new RawXMLFileHandler<LectureScheduleOfCoursePOJO>(pathManagerToLectureAssets, xmlFileReaderManager);
		 
	}
	
	  
	@Bean
	@Qualifier("XMLFileHandler for CourseScheduleXMLFile")
	public RawXMLFileHandler<PersistenceCourseSchedulePOJO>  provideCourseScheduleXMLFile( 
			@Qualifier("PathManager to Course Assets") PathManager pathManagerToCourseAssets,
	         	@Qualifier("XMLFileReader for CourseScheduleXMLFile")  XMLFileReader<PersistenceCourseSchedulePOJO> xmlFileReaderManager){
 
		return new RawXMLFileHandler<PersistenceCourseSchedulePOJO>(pathManagerToCourseAssets, xmlFileReaderManager);
	}	 
	 
}
