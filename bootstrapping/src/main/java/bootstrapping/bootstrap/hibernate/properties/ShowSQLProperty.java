package bootstrapping.bootstrap.hibernate.properties;

import static bootstrapping.properties.PropertyAlias.Alias.ShowSQL;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;
 
@Component
public class ShowSQLProperty
                           extends PropertyKey {
	
    public ShowSQLProperty( ) {
		super(new PropertyAlias (ShowSQL)); 
	}
  
}