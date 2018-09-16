package bootstrapping.bootstrap.network.properties;
 
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertiesManager;

import java.util.Map; 

@Component
public class NetworkPropertyManager 
						extends PropertiesManager{
	
	@Autowired 
	public NetworkPropertyManager (NetworkPropertyFile  propertyFile, 
											HostnameProperty hostnameProperty, 
												  PortProperty portProperty) {
		
		super(propertyFile, hostnameProperty, portProperty);  
	}
	 
}
