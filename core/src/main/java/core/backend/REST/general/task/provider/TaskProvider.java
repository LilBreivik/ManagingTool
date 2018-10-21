package core.backend.REST.general.task.provider;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import core.backend.REST.assets.task.AssetsStockTask;
import core.backend.REST.fileasset.delete.task.DeleteFileTask;
import core.backend.REST.fileasset.download.task.DownloadFileTask; 
import core.backend.REST.fileasset.upload.task.UploadFileTask;
import core.backend.REST.nonfileasset.synthesize.task.SpecificSynthesizedCourseScheduleTask; 
import core.backend.REST.nonfileasset.collision.task.CollisionCheckTask;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import core.backend.REST.nonfileasset.notice.task.add.AddNoticeTask;
import core.backend.REST.nonfileasset.notice.task.delete.DeleteNoticeTask;
import core.backend.REST.nonfileasset.notice.task.read.ReadGeneralNoticeTask;
import core.backend.REST.nonfileasset.notice.task.read.ReadSpecificNoticeTask;
import core.backend.REST.nonfileasset.synthesize.task.GeneralSynthesizedCourseScheduleTask;
import core.backend.REST.nonfileasset.synthesize.task.SynthesizedTask;
import core.backend.utils.delete.DeleteHandler;
import core.backend.utils.download.handler.DownloadHandler;
import core.backend.utils.upload.UploadHandler; 
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.XLS.general.GeneralXLSFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;
import resources.components.filehandler.XML.general.RawXMLFileHandler; 
import resources.components.filesynthesizer.LectureScheduleSynthesizer;
import resources.database.repository.AccountsRepository;
import resources.database.repository.FilesRepository; 
import resources.utils.pathmanager.PathManager;
import scheduling.SchedulingCollisionManager;

@Configuration 
public class TaskProvider {
	 
	@Autowired 
	private AccountsRepository accountsRepo;
	
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
	@Qualifier("provide UploadCourseScheduleFileTask")
	public UploadFileTask<LectureScheduleOfCoursePOJO> provideCourseScheduleFileUploadTask(
			@Qualifier("provide UploadHandler for LectureScheduleXMLFile") UploadHandler<LectureScheduleOfCoursePOJO> pojo
			) {
		
		
		return new UploadFileTask<LectureScheduleOfCoursePOJO> (lectureScheduleXMLFileHandler, 
													lectureScheduleJSONFileHandler, 
												pojo, 
											lectureAssetsPathManager);
	}  
	

	@Bean
	@Qualifier("provide UploadLectureScheduleFileTask")
	public UploadFileTask<AllLecturesPOJO> provideLectureScheduleFileUploadTask(
			@Qualifier("XLSFileHandler for LectureScheduleXLSFiles")  GeneralXLSFileHandler xlsFileHandler, 
					@Qualifier("provide UploadHandler for LectureScheduleFile") UploadHandler<AllLecturesPOJO>	pojo) {
		
		return new UploadFileTask<AllLecturesPOJO> (xlsFileHandler, 
							  		  allLecturesScheduleJSONFileHandler, 
									pojo,
								lectureAssetsPathManager);
	}
	
	@Bean
	@Qualifier("provide GeneralSynthesizedCourseScheduleTask")
	public SynthesizedTask provideGeneralSynthesizedCourseScheduleTask(
	@Qualifier("JSONFileHandler for PersistenceCourseScheduleJSONFile")  GeneralJSONFileHandler<PersistenceCourseSchedulePOJO>  jsonFileHandler) {
		
		return new GeneralSynthesizedCourseScheduleTask( jsonFileHandler );
	} 
	 
	@Bean
	@Qualifier("provide SpecificSynthesizedCourseScheduleTask")
	public SynthesizedTask provideCourseSynthesizedCourseScheduleTask(LectureScheduleSynthesizer synthesizer) {
		 
		return new SpecificSynthesizedCourseScheduleTask(  synthesizer);
	}
	
	 
	@Bean
	@Qualifier("provide DeleteCourseScheduleTask")
	public DeleteFileTask provideDeleteCourseScheduleTask(
			
			@Qualifier("provide DeleteHandler for CourseScheduleFile") DeleteHandler deleteHandlerForCourseScheduleFile ) {
		
		return new DeleteFileTask(null,  deleteHandlerForCourseScheduleFile ); 	
	}
	 
	@Bean
	@Qualifier("provide DeleteLectureScheduleTask")
	public DeleteFileTask provideDeleteLectureScheduleTask( 
			@Qualifier("provide DeleteHandler for LectureScheduleFile") DeleteHandler deleteHandlerForLectureScheduleFile ) {
		 
		
		return new DeleteFileTask(allLecturesScheduleJSONFileHandler,  
											deleteHandlerForLectureScheduleFile);
		 
	} 
	 
	
	@Bean 
	@Qualifier("provide CollisionCheckTask")
	public CollisionCheckTask provideCollisionCheckTask(SchedulingCollisionManager m_collisionManager){
		
		return new CollisionCheckTask(m_collisionManager); 
	}
	
	@Bean 
	@Qualifier("provide AssetsStockTask")
	public AssetsStockTask provideAssetsStockTask(FilesRepository filesRepo){
		
		return new AssetsStockTask(filesRepo); 
	}
	
	
	@Bean 
	@Qualifier("provide AddNoticeTask")
	public NoticeTask provideAddNoticeTask(){
		 
		return new AddNoticeTask(noticeNoticeJSONfileHandler, 
				accountsRepo ); 
	}
	
	
	@Bean 
	@Qualifier("provide DeleteNoticeTask")
	public NoticeTask provideDeleteNoticeTask(){
		
		return new DeleteNoticeTask( noticeNoticeJSONfileHandler, 
				accountsRepo); 
	}
	
	
	@Bean 
	@Qualifier("provide ReadSpecificNoticeTask")
	public NoticeTask provideReadSpecificNoticeTask(){
		 
		return new ReadSpecificNoticeTask(noticeNoticeJSONfileHandler, 
				accountsRepo ); 
	}
	
	
	@Bean 
	@Qualifier("provide ReadGeneralNoticeTask")
	public NoticeTask provideReadGeneralNoticeTask( ){
		 
		return new ReadGeneralNoticeTask( noticeNoticeJSONfileHandler , 
				accountsRepo); 
	}
}
