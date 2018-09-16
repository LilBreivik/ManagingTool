package bootstrapping.bootstrap.network.properties;
 
import static bootstrapping.properties.PropertyAlias.Alias.Hostname;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;

@Component
public class HostnameProperty extends PropertyKey {
	
	public HostnameProperty() {
		super(new PropertyAlias (Hostname)); 
	} 
}