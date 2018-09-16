package bootstrapping.bootstrap.database.properties; 

import static bootstrapping.properties.PropertyAlias.Alias.DatabaseDriver;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;

@Component
public class DatabaseDriverProperty
                           extends PropertyKey {
	
    public DatabaseDriverProperty() {
	
	    super(new PropertyAlias (DatabaseDriver)); 
    }  
}