package bootstrapping.bootstrap.hibernate;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootstrapping.bootstrap.BootstrapJob;
import bootstrapping.bootstrap.ConfigurationSucceeded;
import bootstrapping.bootstrap.hibernate.properties.HibernatePropertyManager;
import resources.error.ConfigurationFailedError;
 

@Component
public class HibernateAssetsBootstrap 
								extends BootstrapJob {
 

	@Autowired
	public  HibernateAssetsBootstrap(HibernatePropertyManager hibernatePropertyManager) {
		super("Hibernate Bootstrap Job" , hibernatePropertyManager);
	}

	@Override
	public void bootstrapConfiguration() {
		
		/*
		 * The Hibernate Bootstrap is not necessary... 
		 * 
		 * If the hibernate configure would miss, the application won't 
		 * start at all. 
		 * 
		 * Its just for convinience...
		 * */

		
		try {
			
			m_propertyManager.readProperties();
		
			configurationSucceeded(new ConfigurationSucceeded() {
				
				@Override
				public void commitSucceed() {

					m_propertyManager.getPropertyMapping()
						   .keySet()
						   .stream()
						   .forEach(key ->{
							    
							   BootstrapJobLogger.info(key + " : " + m_propertyManager.getPropertyMapping().get(key));
						   });
					  
				}
			});
		
		} catch (ConfigurationFailedError configurationFailed) {
			
			BootstrapJobLogger.error("Cannot configure Hibernate due to :  ", configurationFailed);
			
		}
			
	}

}
