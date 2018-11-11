package resources.components.filehandler.XLS.provider;

    
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;  
import resources.components.filehandler.XLS.general.GeneralXLSFileHandler;
import resources.components.filereader.XLS.XLSFileReader;
import resources.components.filereader.utils.FileNameTranslator;
import resources.constraint.impl.LectureConstraint;
import resources.database.repository.FilesRepository; 
import resources.utils.pathmanager.PathManager; 

@Configuration
public class XLSFileHandlerProvider {
 	  
	
	
	@Bean 
	@Qualifier("XLSFileHandler for LectureScheduleXLSFiles")
	public GeneralXLSFileHandler provideLectureScheduleXLSFileHandler(
						FilesRepository  filesRepository ,  
							LectureConstraint lectureConstraint,
								@Qualifier("XLSFileReader for LectureScheduleFile") XLSFileReader xlsFileReader,
									@Qualifier("PathManager to Lecture Assets") PathManager pathManager,
										@Qualifier("FileNameTranslator that uses the Database")	FileNameTranslator fileNameTranslator){
	
		return new GeneralXLSFileHandler(filesRepository,
											pathManager, 
												fileNameTranslator, 
													xlsFileReader);
	
	}
	 
}
 