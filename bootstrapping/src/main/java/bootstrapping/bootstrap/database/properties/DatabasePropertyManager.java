package bootstrapping.bootstrap.database.properties;
  

import static bootstrapping.properties.PropertyAlias.Alias.DatabaseURL;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream; 
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootstrapping.properties.PropertiesManager;
import bootstrapping.properties.PropertyKey;
import resources.error.ConfigurationFailedError;
 
@Component
public class DatabasePropertyManager
						extends PropertiesManager{

	
	public final String LEGAL_PROTOCOL = "http://"; 
	
	public final String DatabaseProtocol = "jdbc:mysql://";
	  
	@Autowired 
	public DatabasePropertyManager(DatabasePropertyFile propertyFile,  
			 							DatabaseURLProperty databaseURLProperty, 
									       	DatabaseUserProperty databaseUserProperty, 
									       		DatabaseDriverProperty databaseDriverProperty, 
									       			DatabaseDialectProperty databaseDialectProperty,
									       				DatabasePasswordProperty databasePasswordProperty)
	{
		super(propertyFile,  databaseURLProperty, 
			        databaseUserProperty, databaseDriverProperty, 
			       		 databaseDialectProperty,   databasePasswordProperty ); 
	 
	}
	
	 
	@Override
	public void readProperties () throws ConfigurationFailedError  {

		Properties prop = new Properties();
		
		InputStream input = null;

			try {

				input = new FileInputStream(m_propertiesFile);

				// load a properties file
				prop.load(input);
  
				Map<String, String> propertyKeyMapping = m_properties.stream().collect(Collectors.toMap(PropertyKey::getM_propertyName,
																						PropertyKey::getM_propertyValue));
   
				for(String propertyKey : propertyKeyMapping.keySet()) {
			 	 	
					System.out.println(propertyKey);
					// {Port=server.port, Hostname=server.address}
					
					System.out.println(propertyKeyMapping.get((String) propertyKey) );
					
					System.out.println((String) prop.get(propertyKey) );
					
					
					String key = propertyKeyMapping.get((String) propertyKey);
					
					String value = (String) prop.get(propertyKey);
					
					if(propertyKey.equals(DatabaseURL.toString())) {
						
						
						 
						value = DatabaseProtocol.concat( value);
					}
					
					 
					
					// prop = {spring.jpa.hibernate.ddl-auto=create}
					propertyMapping.put(key, value);
				}
				
				 
				 
			} catch (IOException ex) {
				 ex.printStackTrace();
				throw new ConfigurationFailedError("Cannot access properties from " + m_propertiesFile.getName().toString());
			
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

    }
	
 
}
