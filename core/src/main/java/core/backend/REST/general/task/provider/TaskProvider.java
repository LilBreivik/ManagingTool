package core.backend.REST.general.task.provider;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import core.backend.REST.assets.task.AssetsStockTask;
import core.backend.REST.fileasset.delete.task.DeleteFileTask;
import core.backend.REST.fileasset.download.task.DownloadFileTask;  
import core.backend.utils.delete.DeleteHandler;
import core.backend.utils.download.handler.DownloadHandler; 
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO; 
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler; 
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;
import resources.components.filehandler.XML.general.RawXMLFileHandler;   
import resources.database.repository.FilesRepository; 
import resources.utils.pathmanager.PathManager; 

@Configuration 
public class TaskProvider {
	  
	
	@Autowired 
	@Qualifier("XMLFileHandler for LectureScheduleXMLFiles")
	private GeneralXMLFileHandler<LectureScheduleOfCoursePOJO> lectureScheduleXMLFileHandler;
	  
	@Autowired 
	@Qualifier("JSONFileHandler for NoticesJSONFile")
	private GeneralJSONFileHandler<NoticesPOJO> noticeNoticeJSONfileHandler;
	 
	@Autowired 
	@Qualifier("PathManager to Lecture Assets") 
	private PathManager lectureAssetsPathManager;
	
	@Autowired 
	@Qualifier("JSONFileHandler for LectureScheduleJSONFile")
	private GeneralPersistentJSONFileHandler<LectureScheduleOfCoursePOJO> lectureScheduleJSONFileHandler;
	
	@Autowired 
	@Qualifier("JSONFileHandler for AllLecturesScheduleJSONFile")
	private GeneralPersistentJSONFileHandler<AllLecturesPOJO> allLecturesScheduleJSONFileHandler;
	
	@Bean
	@Qualifier("provide DownloadGeneralCourseScheduleTemplateFileTask")
	public DownloadFileTask  provideDownloadGeneralCourseScheduleTemplateFileTask(
			 
    @Qualifier("XMLFileHandler for GeneralCourseScheduleTemplateXMLFile") RawXMLFileHandler<LectureScheduleOfCoursePOJO>  generalCourseScheduleTemplateXMLFile,
			
			@Qualifier("provide DownloadHandler for GeneralCourseScheduleTemplateFile")  DownloadHandler processor  ) {
		 
		return new DownloadFileTask (generalCourseScheduleTemplateXMLFile, processor );
	}
	     
	@Bean
	@Qualifier("provide DownloadCourseScheduleFileTask")
	public DownloadFileTask provideCourseScheduleFileDownloadTask(  
			@Qualifier("provide DownloadHandler for CourseScheduleFile")   DownloadHandler processor  ) {
		
		 return new DownloadFileTask( lectureScheduleXMLFileHandler, processor );
	} 
	
	@Bean
	@Qualifier("provide DownloadLectureScheduleFileTask")
	public DownloadFileTask provideLectureScheduleFileDownloadTask( 
			@Qualifier("provide DownloadHandler for LectureScheduleFile")  DownloadHandler processor) {
	 	
		 return new DownloadFileTask(lectureScheduleXMLFileHandler, processor );
	 
	}
	 
	  
	 
	@Bean
	@Qualifier("provide DeleteCourseScheduleTask")
	public DeleteFileTask provideDeleteCourseScheduleTask( 
		@Qualifier("JSONFileHandler for LectureScheduleJSONFile") GeneralPersistentJSONFileHandler<LectureScheduleOfCoursePOJO>  jsonFileHandler,
			@Qualifier("provide DeleteHandler for CourseScheduleFile") DeleteHandler deleteHandlerForCourseScheduleFile ) {
		
		return new DeleteFileTask( jsonFileHandler,  deleteHandlerForCourseScheduleFile ); 	
	}
	  
	@Bean
	@Qualifier("provide DeleteLectureScheduleTask")
	public DeleteFileTask provideDeleteLectureScheduleTask( 
			 
		@Qualifier("JSONFileHandler for AllLecturesScheduleJSONFile")  GeneralPersistentJSONFileHandler<AllLecturesPOJO> jsonFileHandler,
			@Qualifier("provide DeleteHandler for LectureScheduleFile") DeleteHandler deleteHandlerForLectureScheduleFile ) {
		 
		
		return new DeleteFileTask(jsonFileHandler,  
											deleteHandlerForLectureScheduleFile);
		 
	} 
	 

	@Bean 
	@Qualifier("provide AssetsStockTask")
	public AssetsStockTask provideAssetsStockTask(FilesRepository filesRepo){
		
		return new AssetsStockTask(filesRepo); 
	}
	 
}
