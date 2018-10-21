package resources.components.filehandler.XLS.provider;

   
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;  
import resources.components.filehandler.XLS.general.GeneralXLSFileHandler;
import resources.components.filereader.utils.FileNameTranslator;
import resources.constraint.LectureConstraint;
import resources.database.repository.FilesRepository;
import resources.utils.pathmanager.PathManager; 

@Configuration
public class XLSFileHandlerProvider {
 	 
	@Bean
	@Qualifier("XLSFileHandler for LectureScheduleXLSFiles")
	public GeneralXLSFileHandler provideLectureScheduleXLSFileHandler(
						FilesRepository  filesRepository ,  
							LectureConstraint lectureConstraint,
								@Qualifier("PathManager to Lecture Assets") PathManager pathManagerToLectureScheduleFiles,
									@Qualifier("FileNameTranslator that uses the Database")	FileNameTranslator translator){
					
		return new GeneralXLSFileHandler( filesRepository,
											lectureConstraint,
											   pathManagerToLectureScheduleFiles, 
													translator);    
	}
	 
}
