package bootstrapping.bootstrap.network;

 
import static bootstrapping.properties.PropertyAlias.Alias.Hostname;
import static bootstrapping.properties.PropertyAlias.Alias.Port;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootstrapping.bootstrap.BootstrapJob;
import bootstrapping.bootstrap.ConfigurationFailed;
import bootstrapping.bootstrap.ConfigurationSucceeded;
import bootstrapping.bootstrap.network.properties.NetworkPropertyManager;
import bootstrapping.properties.PropertyAlias;
import resources.error.ConfigurationFailedError;
import resources.utils.network.ConfigurePurposes;

/**
 * This class shall handle the test of the 
 * Network Configuration Asset
 * */
 
@Component
public class NetworkAssetsBootstrap 
								extends BootstrapJob{
	
	@Autowired
	public NetworkAssetsBootstrap(NetworkPropertyManager networkManager) {
		super("Network Bootstrap Job", networkManager);		 
	}
	

	@Override
	public void bootstrapConfiguration() {
		 
		BootstrapJobLogger.info("running ... Bootstrap Network Procedere ");
 
		try {
			
			m_propertyManager.readProperties();
			 
			System.out.println(m_propertyManager.getPropertyMapping());
		
			System.out.println(PropertyAlias.resolveAlias(Port) );
			
			int port =  Integer.parseInt(m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(Port)  ));
			
			String hostName = m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(Hostname));
			
			
			/*
			 * We check if the port is available, if not, 
			 * we will return ConfigurationFailedError
			 * */
			
			if(!ConfigurePurposes.checkPortAvailability(port)) 
			{
				configurationFailed(new ConfigurationFailed() {
					
					@Override
					public void commitFail() {
						
						BootstrapJobLogger.error("", new ConfigurationFailedError("Cannot claim Port " + port) );
					}
				});
			}
			else if (!ConfigurePurposes.checkHostnameAvailability(hostName)) 
			{
				configurationFailed(new ConfigurationFailed() {
					
					@Override
					public void commitFail() {
						
						BootstrapJobLogger.error("", new ConfigurationFailedError("Cannot find Hostname " + hostName) );
					}
				});
			}
			
			else {
				
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
				
				
			}
			
			
		} catch (ConfigurationFailedError configurationFailed) {
			
			BootstrapJobLogger.error("Cannot configure the Greeting due to :  ", configurationFailed);
			
		}
		
    }
	
}