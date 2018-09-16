package resources.configuration;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import resources.components.filehandler.PathManager;
import resources.components.filehandler.assetsmanager.FileAssetsManager;
import resources.components.filehandler.assetsmanager.FileRepositoryManager;
import resources.components.filehandler.filereader.FileNameTranslator;
import resources.database.repository.FilesRepository; 

@Configuration
public class FileAssetManagerProvider {
	
	@Autowired
	private FilesRepository m_fileRepository;
		
	@Bean
	@Qualifier("FileRepository Manager for Lectures Assets")
	public FileRepositoryManager provideLectureXMLRepositoryManager(@Qualifier("PathManager to Lecture Assets") 
	PathManager pathManager){
		 
		return new FileRepositoryManager( pathManager, m_fileRepository, new FileNameTranslator() {
			
			@Override
			public String translateFileName(String filename) {
				 
				
				return FilenameUtils.getName( m_fileRepository.read(filename).getFilePath());
				
			}
		});
	}
	
	
	@Bean
	@Qualifier("FileAssetsManager Manager for POM XML Assets")
	public  FileAssetsManager providePomXMLFileAssetsManager(@Qualifier("PathManager to Working Directory") 
	PathManager pathToWorkingDirectory ){
		 
		return new  FileAssetsManager( pathToWorkingDirectory, new FileNameTranslator() {
			
			@Override
			public String translateFileName(String filename) {
			
				return filename;
			}
		});
	}
	
	@Bean
	@Qualifier("FileAssetsManager for Course Schedule XML Assets")
	public  FileAssetsManager provideCourseScheduleXMLFileAssetsManager(@Qualifier("PathManager to Course Assets")
	PathManager pathToWorkingDirectory ){
		 
		return new  FileAssetsManager( pathToWorkingDirectory, new FileNameTranslator() {
			
			@Override
			public String translateFileName(String filename) {
			
				return filename;
			}
		});
	}
	
	@Bean
	@Qualifier("FileAssetsManager for the JSON  Assets")
	public  FileAssetsManager provideCourseScheduleJSONFileAssetsManager(@Qualifier("PathManager to JSONFiles") 
	PathManager pathToWorkingDirectory ){
		 
		return new  FileAssetsManager( pathToWorkingDirectory, new FileNameTranslator() {
			
			@Override
			public String translateFileName(String filename) {
			
				return filename;
			}
		});
	}
}
