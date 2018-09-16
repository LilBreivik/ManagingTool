package bootstrapping.bootstrap.hibernate.properties;

import static bootstrapping.properties.PropertyAlias.Alias.EntityManager;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;
 
@Component
public class EntitiyManagerProperty
                           extends PropertyKey {
	
    public EntitiyManagerProperty( ) {
		super(new PropertyAlias (EntityManager)); 
	}
  
}