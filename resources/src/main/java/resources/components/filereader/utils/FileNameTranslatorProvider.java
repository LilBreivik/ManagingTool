package resources.components.filereader.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import resources.database.repository.FilesRepository;


@Configuration
public class FileNameTranslatorProvider {

	@Autowired 
	private FilesRepository m_fileRepository; 
	
	@Bean  
	@Qualifier("FileNameTranslator that uses the Database")
	public FileNameTranslator provideFileNameTranslatorFromDatabase() {
		
		return new FileNameTranslator() {
			
			@Override
			public String translateFileName(String filename) {
				 	
				return FilenameUtils.getName( m_fileRepository.read(filename).getFilePath());
			}
		};
	}
	

	@Bean  
	@Qualifier("FileNameTranslator that uses Raw Files")
	public FileNameTranslator provideFileNameTranslatorForRawFiles() {
		
		return null;
	}
}
