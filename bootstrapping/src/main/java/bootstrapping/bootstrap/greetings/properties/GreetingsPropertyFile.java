package bootstrapping.bootstrap.greetings.properties;
 
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyFile;
import resources.components.filehandler.PathManager;
 

@Component
public class GreetingsPropertyFile 
								extends  PropertyFile{
 
	@Autowired
	public GreetingsPropertyFile (@Qualifier("PathManager to Greetings Properties") PathManager pathToPropertiesGreetings) throws FileNotFoundException {
		super(pathToPropertiesGreetings.getPathOfFile("greetings.configure").toFile().getAbsolutePath());
	 
	}

}
