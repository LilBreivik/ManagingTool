package bootstrapping.bootstrap.greetings.properties;

 
import static bootstrapping.properties.PropertyAlias.Alias.Advisor;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;

@Component
public class AdvisorProperty
				extends PropertyKey {
					
			public AdvisorProperty() {
		
				super(new PropertyAlias (Advisor)); 
			} 
}