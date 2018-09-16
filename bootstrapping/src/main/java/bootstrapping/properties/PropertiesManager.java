package bootstrapping.properties;
 
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream; 
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties; 
import java.util.stream.Collectors; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
 
import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import resources.error.ConfigurationFailedError;
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.general.GeneralPurpose;
 
/**
 * Class that will be used to read and write to propeerty files ...
 *
 * In the application , there will be several possiblities to 
 * add properties , that controll the application... 
 * 
 * The properties  are read from property files (with the ending .configuration)
 * and will be collected, after the properties of a certain "property file"
 * was read they will be written to the "application.properties" file. 
 * 
 * The "application.properties" file is the main "property file" of the Spring Framework
 *
 **/

@Component
public abstract class PropertiesManager {
 
	/**
	 * Handle to the application.properties file
	 * */
	
	@Autowired 
	@Qualifier("Filehandler to Application Properties")
	private  File m_applicationPropertiesFile;
	
 
	 
	/**
	 * Handle to the current configure file
	 * */
	
	protected File m_propertiesFile;
	
	
	/** 
	 * Collection PropertyKey, enum to describe the several properties that 
	 * could be used ... needs to be defined in the inherits ...
	 */
	
	protected Collection<PropertyKey> m_properties;
	
	protected Map<String, String> propertyMapping; 
	
	
	public PropertiesManager(File propertyFile, PropertyKey... keys) {
		
		propertyMapping = new HashMap<>();
		 
		m_properties = GeneralPurpose.ArrayToCollection(keys);
		m_propertiesFile = propertyFile;
		 
	}
	
	 
	public Map<String, String>  getPropertyMapping()  {
		
		return propertyMapping; 
	}
	 
	
	
	
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
					
					System.out.println("---> " + propertyKeyMapping.get((String) propertyKey) );
					
					System.out.println("---> " +  (String) prop.get(propertyKey) );
					
					
					String key = propertyKeyMapping.get((String) propertyKey);
					
					String value = (String) prop.get(propertyKey);
					
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
	
	/**
	 * Method that writes the 
	 * properties from a certain configure 
	 * file, to the application.properties file
	 * */
	
    public void writePropertiesToApplicationPropertiesFile () throws ConfigurationFailedError{
	  
    	writePropertiesToFile(m_applicationPropertiesFile);
    	 
	}
	

    /**
	 * Method that writes the 
	 * properties from a certain configure 
	 * file, to the application.properties file
	 *  
     * @throw ConfigurationFailedError , if the  fileToBeWritten cannnot be accessed 
	 * */
	
    public void writePropertiesToConfigureFile () throws ConfigurationFailedError {
	  	
    	writePropertiesToFile(m_propertiesFile);
  
    }
    
    
    private void writePropertiesToFile(File propertiesFile) throws ConfigurationFailedError{
    	
    	try {
   		 
	   		 List<String> properties =  propertyMapping.keySet()
	 				   .stream()
	 				   .map( key -> key.concat(" : ").concat(propertyMapping.get(key)) )
	 				   .collect(Collectors.toList());
	 	
	   		 CharSink sink = Files.asCharSink(propertiesFile, Charsets.UTF_8, FileWriteMode.APPEND);
	
				 sink.writeLines( properties, "\r\n");
		} 
    	catch (IOException e) {
		 
			throw new ConfigurationFailedError("Cannot write to the application.properties file ");
		}
    }
     
	 
}
