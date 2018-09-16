package bootstrapping.bootstrap.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootstrapping.bootstrap.BootstrapJob;
import bootstrapping.bootstrap.ConfigurationFailed;
import bootstrapping.bootstrap.ConfigurationSucceeded;
import bootstrapping.bootstrap.database.properties.DatabasePropertyManager;
import bootstrapping.properties.PropertyAlias;
import resources.error.ConfigurationFailedError;
import resources.utils.network.ConfigurePurposes;

import static bootstrapping.properties.PropertyAlias.Alias.DatabasePassword;
import static bootstrapping.properties.PropertyAlias.Alias.DatabaseURL;
import static bootstrapping.properties.PropertyAlias.Alias.DatabaseUser;
import static bootstrapping.properties.PropertyAlias.Alias.Hostname;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class shall handle the test of the 
 * Network Configuration Asset
 * */
 
@Component
public class DatabaseAssetsBootstrap 
								extends BootstrapJob{
	 
	 
	
	// We must put an http protocal as a Prefix, due to JAVA 
	// URL's lack of recognzing certain protocols
	
	
	@Autowired
	public DatabaseAssetsBootstrap(DatabasePropertyManager databaseManager) {
		super("Database Bootstrap Job", databaseManager);		 
	}
	

	@Override
	public void bootstrapConfiguration() {
		 
		BootstrapJobLogger.info("running ... Bootstrap Database Procedere ");
 
		try {
			
			m_propertyManager.readProperties();
			 
			
			
			DatabasePropertyManager castedPropertyManager = (DatabasePropertyManager) m_propertyManager;
			
			final String m_LEGAL_PROTOCOL = castedPropertyManager.LEGAL_PROTOCOL; 
			
		    final String m_DatabaseProtocol = castedPropertyManager.DatabaseProtocol;
			
			try {
			
				// At first we try to figure out, if the obtained Database URL is wrong 
				
				URL databaseURL = new URL(m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(DatabaseURL)).replaceAll(m_DatabaseProtocol, m_LEGAL_PROTOCOL) );
		
				try {
				
					// note we cannot check the port number in test methods.... we must put these later on the server 
					
					ConfigurePurposes.checkIfServiceIsOnline(databaseURL );
				
				
					System.out.println("hä");
					try {
						
						checkIfDatabaseCredentials(m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(DatabaseURL)) , 
																m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(DatabaseUser)),
																	m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(DatabasePassword))); 
					
					
					
						configurationSucceeded(new ConfigurationSucceeded() {
							
							@Override
							public void commitSucceed() {
								BootstrapJobLogger.info("Database Related Information");
								
								BootstrapJobLogger.info("Database URL : " + m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(DatabaseURL)));
								
								BootstrapJobLogger.info("Database User : " + m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(DatabaseUser)));
								
								BootstrapJobLogger.info("Database Password : " +  m_propertyManager.getPropertyMapping().get(PropertyAlias.resolveAlias(DatabasePassword)));
						
								
								
								BootstrapJobLogger.info("Database Connection is possible");
								
							}
						});
					 
					}
					
					catch(ConfigurationFailedError configFailed) {
						
						configurationFailed(new ConfigurationFailed() {
							
							@Override
							public void commitFail() {

								BootstrapJobLogger.error("Cannot get access to the Database  ", configFailed);
							}
						});
					}
				
				}
				catch(ConfigurationFailedError configFailed) {
					
					configurationFailed(new ConfigurationFailed() {
						
						@Override
						public void commitFail() {
							BootstrapJobLogger.error("Service not available ", configFailed);
						}
					});
					
				}
				 
			} catch (MalformedURLException faultyURL) {
				
				configurationFailed(new ConfigurationFailed() {
					
					@Override
					public void commitFail() {
						BootstrapJobLogger.error("DatabaseURL is wrong  ", faultyURL);
					}
				});
			}
			 
			
		} catch (ConfigurationFailedError configFailed) {
			
			configurationFailed(new ConfigurationFailed() {
				
				@Override
				public void commitFail() {

					BootstrapJobLogger.error("Cannot configure the Database Connection due to :  ", configFailed);
				}
			});
		}
	
	 
 
		
	}
	 
	private void checkIfDatabaseCredentials(String databaseURL , 
												String databaseUser, 
													String databasePassword) throws ConfigurationFailedError {
	 
	   try {
				
				
			Class.forName("com.mysql.cj.jdbc.Driver");
				   
		 	
			Connection con = DriverManager.getConnection(databaseURL,
																	databaseUser,
																			databasePassword);
			
			con.close();
	   } 	
	   catch (ClassNotFoundException e) {
				  
			throw new ConfigurationFailedError("Cannot find MySQL Driver");
		} 	
		catch (SQLException e) {
			 
		
			throw new ConfigurationFailedError("Cannot obtain access to the MySQL Server, look at the further Exception... " + e.getMessage());
		}
			
		 
	}
	
}