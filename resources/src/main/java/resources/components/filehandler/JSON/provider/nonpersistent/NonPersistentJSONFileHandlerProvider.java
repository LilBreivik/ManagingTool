package resources.components.filehandler.JSON.provider.nonpersistent;
  
import resources.components.elements.POJO.Course.CoursePOJO; 
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.components.filereader.JSON.GeneralJSONFileReader;
import resources.components.filewriter.JSON.GeneralJSONFileWriter;
import resources.utils.pathmanager.PathManager; 

@Configuration
public class NonPersistentJSONFileHandlerProvider {
  
	@Autowired 
	@Qualifier("PathManager to JSONFiles")
	private PathManager pathManagerToJSONFiles;
	
	@Bean
	@Qualifier("JSONFileHandler for PersistenceCourseScheduleJSONFile")
	public GeneralJSONFileHandler<PersistenceCourseSchedulePOJO> providePersistenceCourseScheduleJSONFileHandler( ){
		
		return new GeneralJSONFileHandler<PersistenceCourseSchedulePOJO >( 
				
				pathManagerToJSONFiles, 
					new GeneralJSONFileReader<PersistenceCourseSchedulePOJO>(PersistenceCourseSchedulePOJO.class) , 
						new  GeneralJSONFileWriter<PersistenceCourseSchedulePOJO>());    
	}
	  
	@Bean
	@Qualifier("JSONFileHandler for CourseScheduleJSONFile")
	public GeneralJSONFileHandler<  CoursePOJO> provideCourseScheduleJSONFileHandler( ){
		
		return new GeneralJSONFileHandler<CoursePOJO >( pathManagerToJSONFiles,
					new GeneralJSONFileReader<CoursePOJO>(CoursePOJO.class), 
						new  GeneralJSONFileWriter<CoursePOJO>());    
	}
	 
	@Bean
	@Qualifier("JSONFileHandler for SynthesizedCourseScheduleJSONFile")
	public GeneralJSONFileHandler<LectureScheduleOfCoursePOJO > provideSynthesizedCourseScheduleJSONFileHandler( ){
		
		return new GeneralJSONFileHandler<LectureScheduleOfCoursePOJO >( pathManagerToJSONFiles, 
				new GeneralJSONFileReader<LectureScheduleOfCoursePOJO>(LectureScheduleOfCoursePOJO.class), 
					new  GeneralJSONFileWriter<LectureScheduleOfCoursePOJO>());  
	   
	}
	  
}
