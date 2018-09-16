package bootstrapping.bootstrap.database.properties;
 

import static bootstrapping.properties.PropertyAlias.Alias.DatabaseDialect;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;

@Component
public class DatabaseDialectProperty extends PropertyKey {

	public DatabaseDialectProperty() {
		super(new PropertyAlias ( DatabaseDialect)); 
	} 
}