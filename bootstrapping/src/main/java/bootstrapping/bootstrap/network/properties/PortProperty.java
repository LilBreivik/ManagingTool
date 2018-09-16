package bootstrapping.bootstrap.network.properties;

import static bootstrapping.properties.PropertyAlias.Alias.Port;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;


@Component
public class PortProperty extends PropertyKey {
	
	public PortProperty() {
		super(new PropertyAlias (Port)); 
	} 
}


