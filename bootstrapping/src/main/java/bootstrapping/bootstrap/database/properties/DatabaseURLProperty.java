package bootstrapping.bootstrap.database.properties;
 

import static bootstrapping.properties.PropertyAlias.Alias.DatabaseURL;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;

@Component
public class DatabaseURLProperty extends PropertyKey {

	public DatabaseURLProperty() {
		super(new PropertyAlias (DatabaseURL)); 
	} 
}