package bootstrapping.bootstrap.hibernate.properties;
  
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyFile;
import resources.components.filehandler.PathManager;
 

@Component
public class HibernatePropertyFile 
								extends  PropertyFile{
 
	@Autowired
	public HibernatePropertyFile(@Qualifier("PathManager to Hibernate Properties") PathManager pathToPropertiesHibernate) throws FileNotFoundException {
		super(pathToPropertiesHibernate.getPathOfFile("hibernate.configure").toFile().getAbsolutePath());
	
	}
 
}
