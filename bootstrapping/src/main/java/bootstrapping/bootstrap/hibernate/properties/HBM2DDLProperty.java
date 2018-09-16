package bootstrapping.bootstrap.hibernate.properties;

import static bootstrapping.properties.PropertyAlias.Alias.HBM2DDL;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;
 
@Component
public class HBM2DDLProperty
                           extends PropertyKey {
	
    public HBM2DDLProperty( ) {
		super(new PropertyAlias (HBM2DDL)); 
	}
  
}