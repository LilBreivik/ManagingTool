package bootstrapping.bootstrap.database.properties;
 
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyFile;
import resources.components.filehandler.PathManager;
 

@Component
public class DatabasePropertyFile 
								extends  PropertyFile{
 
	@Autowired
	public DatabasePropertyFile (@Qualifier("PathManager to Database Properties") PathManager pathToPropertiesGreetings) throws FileNotFoundException {
		super(pathToPropertiesGreetings.getPathOfFile("resources.database.configure").toFile().getAbsolutePath());
	 
	}

}
