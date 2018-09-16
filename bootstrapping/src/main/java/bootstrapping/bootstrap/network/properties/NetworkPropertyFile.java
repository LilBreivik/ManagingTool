package bootstrapping.bootstrap.network.properties;
 
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyFile;
import resources.components.filehandler.PathManager;
 

@Component
public class NetworkPropertyFile 
								extends  PropertyFile{
	 
	@Autowired
	public  NetworkPropertyFile (@Qualifier("PathManager to Network Properties") PathManager pathToPropertiesGreetings) throws FileNotFoundException {
		super(pathToPropertiesGreetings.getPathOfFile("network.configure").toFile().getAbsolutePath());
	 
	}

}
