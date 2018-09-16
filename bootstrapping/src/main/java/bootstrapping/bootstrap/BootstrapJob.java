package bootstrapping.bootstrap;
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bootstrapping.properties.PropertiesManager;
import resources.error.ConfigurationFailedError;
 


public abstract class BootstrapJob {
 
	protected final Logger  BootstrapJobLogger =  LoggerFactory.getLogger(BootstrapJob.class);
	
	protected boolean m_jobFailed; 
	
	protected String m_jobName;
	
	protected PropertiesManager m_propertyManager;
	
	
	public BootstrapJob(String jobName ) {

		m_jobName = jobName; 
		m_jobFailed = true;  
	}
	
	
	public BootstrapJob(String jobName, 
							PropertiesManager propertyManager) {
		
		m_jobName = jobName; 
		m_jobFailed = true; 
		m_propertyManager = propertyManager;
	}
	
	public boolean didJobFail() {
		
		return m_jobFailed; 
	}
	
	public abstract void bootstrapConfiguration();
	
	 
	protected void configurationSucceeded(ConfigurationSucceeded succeededOperation) {
	
	
		try {
			
			succeededOperation.commitSucceed();
			
			m_propertyManager.writePropertiesToApplicationPropertiesFile();
			m_jobFailed = false;
		
		} catch (ConfigurationFailedError configFailed) {
			
			// If we cannot update the application.properties File
			
			BootstrapJobLogger.error("We cannot update the application.properties File, look at  further Exception " + configFailed.getMessage());
	
			// We will see the job as failed...
			
			m_jobFailed = true; 
		}	 
	}
	
	protected void configurationFailed(ConfigurationFailed failedOperation) {
		
		failedOperation.commitFail();
		m_jobFailed = true;  
	}
	
	public String getJobName() {
		
		return m_jobName;
	}
}
