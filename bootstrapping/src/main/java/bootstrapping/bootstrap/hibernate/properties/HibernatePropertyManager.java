package bootstrapping.bootstrap.hibernate.properties;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertiesManager;

 
@Component
public class HibernatePropertyManager
						extends PropertiesManager{
 
	@Autowired 
	public HibernatePropertyManager(HibernatePropertyFile  propertyFile,  
											JpaddlProperty jpaddlProperty, 
												HBM2DDLProperty hbm2ddlProperty, 
													ShowSQLProperty show_sqlProperty, 
														EntitiyManagerProperty entityManager)
	{
		super(propertyFile,  jpaddlProperty, 
					hbm2ddlProperty,   show_sqlProperty ,  entityManager);  
	}
	 
 
}
