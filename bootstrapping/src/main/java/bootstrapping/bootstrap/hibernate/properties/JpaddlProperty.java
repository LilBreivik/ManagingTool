package bootstrapping.bootstrap.hibernate.properties;

import static bootstrapping.properties.PropertyAlias.Alias.HibernateJpaddl;

import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertyAlias;
import bootstrapping.properties.PropertyKey;
 
@Component
public class JpaddlProperty
                           extends PropertyKey {
	
    public JpaddlProperty( ) {
		super(new PropertyAlias (HibernateJpaddl)); 
	}
  
}