package core.backend.REST.general.task.provider;

import static resources.error.parameter.FileAssetParameterViolationError.FileExtension.XLS;
import static resources.error.parameter.FileAssetParameterViolationError.FileExtension.XML;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import core.backend.REST.assets.task.AssetsStockTask;
import core.backend.REST.fileasset.delete.task.DeleteFileTask;
import core.backend.REST.fileasset.download.task.DownloadFileTask;
import core.backend.REST.fileasset.upload.task.UploadFileTask;
import core.backend.REST.nonfileasset.synthesize.task.SpecificSynthesizedCourseScheduleTask;
import core.backend.REST.nonfileasset.collision.parameter.CollisionCheckParameter;
import core.backend.REST.nonfileasset.collision.task.CollisionCheckTask;
import core.backend.REST.nonfileasset.synthesize.task.GeneralSynthesizedCourseScheduleTask;
import core.backend.REST.nonfileasset.synthesize.task.SynthesizedTask;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.filehandler.PathManager;
import resources.components.filehandler.JSON.AllLecturesScheduleJSONFileHandler;
import resources.components.filehandler.JSON.LectureScheduleJSONFileHandler;
import resources.components.filehandler.XLS.LectureScheduleXLSFileHandler;
import resources.components.filehandler.XML.CourseScheduleXMLFileHandler;
import resources.components.filehandler.XML.LectureScheduleXMLFileHandler;
import resources.components.filehandler.filesynthesizer.LectureScheduleSynthesizer;
import resources.database.repository.FilesRepository;
import resources.utils.files.OrdinaryFileHandler;
import scheduling.SchedulingCollisionManager;

@Configuration
public class TaskProvider {

	@Bean
	@Qualifier("provide DownloadCourseScheduleFileTask")
	public DownloadFileTask provideCourseScheduleFileDownloadTask(LectureScheduleXMLFileHandler xmlFileHandler) {
		
		 return new DownloadFileTask(xmlFileHandler, (SourceFile, Response) ->  {
			
			 Response.setContentType("application/xml");
			  
			 Response.setHeader("Content-Disposition", "attachment; filename=" + ( new StringBuilder( "\"" + SourceFile.getName().concat(XML.toString()) + "\"")).toString() );
			
		});
	} 
	
	@Bean
	@Qualifier("provide DownloadLectureScheduleFileTask")
	public DownloadFileTask provideLectureScheduleFileDownloadTask(LectureScheduleXLSFileHandler xlsFileHandler) {
		
		return new DownloadFileTask(xlsFileHandler,  (SourceFile, Response) ->  {
			
			 Response.setContentType("application/xls");
			  
			 Response.setHeader("Content-Disposition", "attachment; filename=" + ( new StringBuilder( "\"" + SourceFile.getName().concat(XLS.toString()) + "\"")).toString() );
			
		});
	}
	 
	@Bean
	@Qualifier("provide UploadCourseScheduleFileTask")
	public UploadFileTask provideCourseScheduleFileUploadTask(LectureScheduleJSONFileHandler jsonFileHandler, 
													LectureScheduleXMLFileHandler xmlFileHandler, 
			@Qualifier("PathManager to Lecture Assets") PathManager pathManager) {
		
		
		return new UploadFileTask(xmlFileHandler, jsonFileHandler, pathManager);
	}  
	

	@Bean
	@Qualifier("provide UploadLectureScheduleFileTask")
	public UploadFileTask provideLectureScheduleFileUploadTask(AllLecturesScheduleJSONFileHandler jsonFileHandler, 
												LectureScheduleXLSFileHandler xlsFileHandler,
			@Qualifier("PathManager to Lecture Assets") PathManager pathManager) {
		
		
		return new UploadFileTask(xlsFileHandler, jsonFileHandler, pathManager);
	}

	
	@Bean
	@Qualifier("provide GeneralSynthesizedCourseScheduleTask")
	public SynthesizedTask provideGeneralSynthesizedCourseScheduleTask( CourseScheduleXMLFileHandler xmlFileHandler) {
		
		return new GeneralSynthesizedCourseScheduleTask( xmlFileHandler );
	}
	 
	@Bean
	@Qualifier("provide SpecificSynthesizedCourseScheduleTask")
	public SynthesizedTask provideCourseSynthesizedCourseScheduleTask(LectureScheduleSynthesizer synthesizer) {
		
		return new SpecificSynthesizedCourseScheduleTask(  synthesizer);
	}
	
	 
	@Bean
	@Qualifier("provide DeleteCourseScheduleTask")
	public DeleteFileTask provideDeleteCourseScheduleTask(AllLecturesScheduleJSONFileHandler allLecturesJSONFileHandler, 
												LectureScheduleJSONFileHandler lecturesScheduleJSONFileHandler,
											LectureScheduleXMLFileHandler lectureScheduleXMLFileHandler) {
		
		return new DeleteFileTask(allLecturesJSONFileHandler, 
				lectureScheduleXMLFileHandler.getFileAssetsManager().getPathManager(), 
				
				    (DeleteParam, PathToSource, PathToAssembledFile) -> (PathToSource.toFile().exists()  && PathToAssembledFile.toFile().exists()),
				
					
					(PersistentJSONFileHandler, DeleteParameter) -> {
	 
						// erase json 
					
						OrdinaryFileHandler.deleteFile(PersistentJSONFileHandler.getFileAssetsManager().getPathManager().getPathOfFile(DeleteParameter.getFileNameResolver().getResolvedFileName()).toFile() );
					
						// erase original 
						
						lectureScheduleXMLFileHandler.deleteFile( Paths.get(DeleteParameter.getFileNameResolver().getResolvedFileName()).toFile());
						
					}); 	
	}
	 
	@Bean
	@Qualifier("provide DeleteLectureScheduleTask")
	public DeleteFileTask provideDeleteLectureScheduleTask(AllLecturesScheduleJSONFileHandler allLecturesJSONFileHandler, 
																LectureScheduleJSONFileHandler lecturesScheduleJSONFileHandler,
																	LectureScheduleXLSFileHandler lectureScheduleXLSFileHandler) {
		
		return new DeleteFileTask( lecturesScheduleJSONFileHandler, 
				lectureScheduleXLSFileHandler.getFileAssetsManager().getPathManager(),
				  (DeleteParam, PathToSource, PathToAssembledFile) -> (PathToSource.toFile().exists()  && PathToAssembledFile.toFile().exists()), 
				 
				  
				  (PersistentJSONFileHandler, DeleteParameter) -> {
							
					  	AllLecturesPOJO lecturesToSubstract = (AllLecturesPOJO) lectureScheduleXLSFileHandler.readFile(DeleteParameter.getFileNameResolver().getResolvedFileName());
						
						AllLecturesPOJO allLectures =  (AllLecturesPOJO) allLecturesJSONFileHandler.readFile(DeleteParameter.getTargetFileName());
						 
						allLectures.substractManyLectures(lecturesToSubstract.getAllLectures());
						 
						allLecturesJSONFileHandler.writeToFile(DeleteParameter.getTargetFileName(), allLectures);
						  	 
					   // erase original 
						
						lectureScheduleXLSFileHandler.deleteFile( Paths.get(DeleteParameter.getFileNameResolver().getResolvedFileName()).toFile());		 
				  }); 
	}
	 
	
	@Bean 
	@Qualifier("provide provideCollisionCheckTask")
	public CollisionCheckTask provideCollisionCheckTask(SchedulingCollisionManager m_collisionManager){
		
		return new CollisionCheckTask(m_collisionManager); 
	}
	
	@Bean 
	@Qualifier("provide AssetsStockTask")
	public AssetsStockTask provideAssetsStockTask(FilesRepository filesRepo){
		
		return new AssetsStockTask(filesRepo); 
	}
	
}
