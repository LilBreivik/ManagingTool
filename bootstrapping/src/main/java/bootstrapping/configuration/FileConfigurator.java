package bootstrapping.configuration;
  

import static resources.utils.general.Constants.Directory.core;

import java.io.File;
import java.io.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import resources.components.filehandler.PathManager; 


/**
 * Class that globally
 * contains Bean methods to supply 
 * PathManger Objects, to build Components
 * 
 * */

@Configuration
public class FileConfigurator {

	@Autowired
	@Qualifier("PathManager to Resource")
	PathManager PathMangagerForResourceDirectory;
	
	
	@Bean
	@Qualifier("Filehandler to Application Properties")
	public File pathToApplicationProperties() throws FileNotFoundException{
		
		PathMangagerForResourceDirectory.changeParentDirectory(core);
		
		return PathMangagerForResourceDirectory.getPathOfFile("application.properties").toFile();
	} 
 
}
