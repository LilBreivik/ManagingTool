package bootstrapping.bootstrap;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection; 
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bootstrapping.bootstrap.database.DatabaseAssetsBootstrap;
import bootstrapping.bootstrap.greetings.GreetingsAssetsBootstrap;
import bootstrapping.bootstrap.hibernate.HibernateAssetsBootstrap;
import bootstrapping.bootstrap.network.NetworkAssetsBootstrap;
import bootstrapping.properties.PropertiesManager;
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.general.GeneralPurpose;
 
@Component
public class BootstrapManager {

	private Collection<BootstrapJob> bootstrapJobs; 
	
	private  final Logger  BootstrapManagingLogger =  LoggerFactory.getLogger(BootstrapManager.class);
	
	private ConfigurationSucceeded  succededBootstrapProcedere; 
	
	private ConfigurationFailed failedBootstrapProcedere; 
	
	
	@Autowired
	private  CoreApplicationStarter m_corseApplicationStarter;
	
	@Autowired 
	@Qualifier("Filehandler to Application Properties")
	private File m_applicationPropertiesFile;
	
 
	
	
	@Autowired
	public BootstrapManager(GreetingsAssetsBootstrap bootstrapGreeting , 
								ScheduleDirectoryAssetsBootstrap bootstrapDirectories, 
							   		NetworkAssetsBootstrap bootstrapNetwork, 
							   		    DatabaseAssetsBootstrap bootstrapDatabase, 
							   		    	HibernateAssetsBootstrap bootstrapHibernate) {
		 
		
		bootstrapJobs = Arrays.asList(  bootstrapGreeting ,  bootstrapDirectories,  
												bootstrapNetwork, bootstrapDatabase, bootstrapHibernate);
		
		succededBootstrapProcedere = () -> successfullyBootStrap() ;
	
		failedBootstrapProcedere = () -> failedBootStrap();
	}
	
	public void runBootStrapJobs() throws IOException {
		
		GeneralPurpose.clearScreen();
		
		flushApplicationsPropertisFile();
		
		BootstrapManagingLogger.info("Running Bootstrapping Procedere ...");
		
		bootstrapJobs.stream().forEach(job -> job.bootstrapConfiguration());
		
		BootstrapManagingLogger.info("The Bootstapping Procedere was run ... ");
		

		succededBootstrapProcedere.commitSucceed();
		
		failedBootstrapProcedere.commitFail();
		
		
		// we will put the final booting sequence here, cause 
		// we cannot guarantee that all properties can 
		// be successfully confirmed....
		
		CountdownAction action = () -> {
		
			BootstrapManagingLogger.info("The Application starts...");
			GeneralPurpose.clearScreen();
		 
			
		};
		
		try {
		
			new BootstrapCountdown("The Application is able to boot, and will start in  ... ", 
				 action);
			
		} catch (InterruptedException e) {
			
			BootstrapManagingLogger.error("The Application Countdown was Interrupted");
			
		}
		
	}
	
	public void successfullyBootStrap() {
		
		if(bootstrapJobs.stream().filter(job -> !job.didJobFail()) .collect(Collectors.toList()).size() != 0) 
		{
			BootstrapManagingLogger.info("The following Configurations did not fail ... ");
			
			bootstrapJobs.stream().filter(job -> !job.didJobFail()) .collect(Collectors.toList()).forEach(job -> BootstrapManagingLogger.debug(job.getJobName()));
	  
			BootstrapManagingLogger.info("Application.properties File will get updated...");
			
		}
							
	}
	
	public void failedBootStrap() {
		
		if(bootstrapJobs.stream().filter(job -> job.didJobFail()) .collect(Collectors.toList()).size() == 0) 
		{
			return ; 
		}
		else {
			 
			BootstrapManagingLogger.info("The following Configurations did fail ... ");
			
			bootstrapJobs.stream().filter(job -> job.didJobFail()) .collect(Collectors.toList()).forEach(job -> BootstrapManagingLogger.debug(job.getJobName()) );
		
			CountdownAction action = () -> System.exit(0);
			
			try {
			
				new BootstrapCountdown("The Application is not able to boot, and will shutdown in  ... ", 
					 action);
				
			} catch (InterruptedException e) {
				
				BootstrapManagingLogger.error("The Application Countdown was Interrupted");
				
			}
		
		}
							
	}
	

    /**
     * This Method shall flush the current applications.properties file
     * 
     * At the end, there shall be an empty application.properties file
     * */
    
    private  void  flushApplicationsPropertisFile() {
		
		
		if(m_applicationPropertiesFile.exists()) {
			
			OrdinaryFileHandler.deleteFile(m_applicationPropertiesFile);
		}
			
		OrdinaryFileHandler.createFile(m_applicationPropertiesFile);
	}
	
	 
	
	@FunctionalInterface
	public interface CountdownAction {

		void doAction();
	}
	
	class BootstrapCountdown{
		  
		public BootstrapCountdown(String countDownMessage, 
				CountdownAction  actionTakenAfterCountdown) throws InterruptedException{
		
			m_corseApplicationStarter.start();
			
			for(int ctr = 10 ; ctr > -1 ; ctr -= 1) {
				
				System.out.print(countDownMessage + "\r " + ctr + " seconds ");
		        Thread.currentThread().sleep(1000);
			}
			
			actionTakenAfterCountdown.doAction();
			
		}
	}
	
	
}
