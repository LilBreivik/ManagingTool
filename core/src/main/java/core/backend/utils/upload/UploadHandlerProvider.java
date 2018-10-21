package core.backend.utils.upload;
 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.filehandler.XLS.general.GeneralXLSFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;

@Configuration
public class UploadHandlerProvider {

	@Bean
	@Qualifier("provide UploadHandler for LectureScheduleFile")
	public UploadHandler<AllLecturesPOJO> provideUploadHandlerForLectureScheduleFile(
						@Qualifier("XLSFileHandler for LectureScheduleXLSFiles")  GeneralXLSFileHandler xlsFileHandler
			) {
		 System.out.println();
		
		return new UploadHandler<AllLecturesPOJO>() {

			@Override
			public AllLecturesPOJO handleUploadedFile(String fileName) {
				 
				return xlsFileHandler.readXLSFile(fileName);
			}
		}; 
	}
	
	
	@Bean
	@Qualifier("provide UploadHandler for LectureScheduleXMLFile")
	public UploadHandler<LectureScheduleOfCoursePOJO> provideUploadHandlerForLectureScheduleXMLFile(
			@Qualifier("XMLFileHandler for LectureScheduleXMLFiles")   GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>  xmlFileHandler
			) {
		 
		return new UploadHandler<LectureScheduleOfCoursePOJO>() {

			@Override
			public LectureScheduleOfCoursePOJO handleUploadedFile(String fileName) {
				 
				return xmlFileHandler.readXMLFile(fileName);
			}
		};
			 
		 
	}
}
