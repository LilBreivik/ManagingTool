package bootstrapping.bootstrap.greetings;
  

import static bootstrapping.properties.PropertyAlias.Alias.Advisor;
import static bootstrapping.properties.PropertyAlias.Alias.Author;
import static bootstrapping.properties.PropertyAlias.Alias.Version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootstrapping.bootstrap.BootstrapJob;
import bootstrapping.bootstrap.ConfigurationFailed;
import bootstrapping.bootstrap.ConfigurationSucceeded;
import bootstrapping.bootstrap.greetings.properties.GreetingsPropertyManager;
import bootstrapping.properties.PropertiesManager;
import bootstrapping.properties.PropertyAlias;
import resources.error.ConfigurationFailedError;


@Component
public class GreetingsAssetsBootstrap
								extends BootstrapJob{
 
	private GreetingsPropertyManager m_greetingsManager;
	
	@Autowired 
	public  GreetingsAssetsBootstrap ( GreetingsPropertyManager greetingsManager) {
		super("Greeting Bootstrap Job");

		m_greetingsManager = greetingsManager;
	}
	
	
	@Override
	public void bootstrapConfiguration()   {
		
		BootstrapJobLogger.info("running ... Bootstrap Greetings Procedere ");

		// print greetings Screen ... 
		// ---> Ascii art of Applicatio 

		// verison Number from pom.xml

		// author: 

		// admin: 

		
		try {
			
			m_greetingsManager.updateVersionNumberInPropertiesFile();
			 
			m_propertyManager = (PropertiesManager) m_greetingsManager;
			
			configurationSucceeded(new ConfigurationSucceeded() {
				
				@Override
				public void commitSucceed() {
 
					BootstrapJobLogger.info("Version : " +  m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(Version)));
					
					BootstrapJobLogger.info("Author : " + m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(Author)));
					
					BootstrapJobLogger.info("Advisor : " + m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(Advisor)));
				}
			});
			
			
		} catch (ConfigurationFailedError configurationFailed) {
			
            configurationFailed(new ConfigurationFailed() {
				
				@Override
				public void commitFail() {

					BootstrapJobLogger.error("Cannot configure the Greeting due to :  ", configurationFailed);
				}
			});	
		}
		
	}

}
