package bootstrapping.bootstrap.greetings.properties;
 
import static bootstrapping.properties.PropertyAlias.Alias.Version;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;

@Component
public class VersionProperty
	extends PropertyKey {
	
	public VersionProperty() {
		super(new PropertyAlias (Version)); 
	} 
}
