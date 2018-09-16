package bootstrapping.bootstrap.database.properties;
 
import static bootstrapping.properties.PropertyAlias.Alias.DatabasePassword;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;


@Component
public class DatabasePasswordProperty 
									extends PropertyKey {

	public DatabasePasswordProperty() {
		super(new PropertyAlias (DatabasePassword)); 
	} 
}
