package bootstrapping.bootstrap.greetings.properties;
  
import static bootstrapping.properties.PropertyAlias.Alias.Version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertiesManager;
import resources.components.elements.POJO.Pom.PomXMLPOJO;
import resources.components.filehandler.XML.PomXMLFileHandler;
import resources.error.ConfigurationFailedError;

@Component
public class GreetingsPropertyManager 
						extends PropertiesManager{

	
	private PomXMLFileHandler m_xmlFileHandler; 
	
	@Autowired 
	public GreetingsPropertyManager(GreetingsPropertyFile propertyFile, 
									       PomXMLFileHandler xmlFileHandler,	
									       		AuthorProperty authorProperty, 
									       			AdvisorProperty advisorProperty, 
									       					VersionProperty versionProperty)
	{
		super(propertyFile, authorProperty, advisorProperty,  versionProperty); 
	
		m_xmlFileHandler = xmlFileHandler; 
	}
	
	
	/**
	 * This function updates the 
	 * version number , so we get the current version, that is 
	 * inn the pom.xml file ... 
	 */
 
	public void updateVersionNumberInPropertiesFile() throws ConfigurationFailedError  {
	     
			super.readProperties();
			
		    PomXMLPOJO pojo = (PomXMLPOJO) m_xmlFileHandler.readFile("pom.xml");
		
		    super.getPropertyMapping().put(Version.toString(), pojo.getVersionName());
	    	 	
			super.writePropertiesToConfigureFile();
			 
	}
 
}
