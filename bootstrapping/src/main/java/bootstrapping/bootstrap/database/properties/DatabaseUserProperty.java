package bootstrapping.bootstrap.database.properties; 

import static bootstrapping.properties.PropertyAlias.Alias.DatabaseUser;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;

@Component
public class DatabaseUserProperty
                           extends PropertyKey {
	
    public DatabaseUserProperty() {
	
	    super(new PropertyAlias (DatabaseUser)); 
    }  
}