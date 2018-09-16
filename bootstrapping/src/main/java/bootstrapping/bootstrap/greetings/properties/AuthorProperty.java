package bootstrapping.bootstrap.greetings.properties;


import static bootstrapping.properties.PropertyAlias.Alias.Author;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;

@Component
public   class AuthorProperty
						extends PropertyKey {

	public AuthorProperty() {
			
		super(new PropertyAlias (Author)); 
	} 
}
