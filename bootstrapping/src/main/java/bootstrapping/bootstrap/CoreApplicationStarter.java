package bootstrapping.bootstrap;
   
import java.util.Arrays;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.components.filehandler.PathManager;

@Component
public class CoreApplicationStarter extends Thread{

	@Autowired
	@Qualifier("PathManager to Core Working Directory")
	private PathManager PathMangagerForCoreWorkingDirectory;
	
	private final Logger  CoreApplicationStarterLogger =  LoggerFactory.getLogger(CoreApplicationStarter.class);
	
	 
	@Override
	public void run() {
		
		final String applicationStartCommand  = "mvn clean install";
		
	    final String switchToCoreApplicationDirectory = "cd " + PathMangagerForCoreWorkingDirectory.getPathToOperateOn().toString(); 
		
		
	   
	    try {
	    	
	    	InvocationRequest request = new DefaultInvocationRequest();
	 	    request.setPomFile( PathMangagerForCoreWorkingDirectory.getPathOfFile("pom.xml").toFile()  );
	 	    request.setGoals( Arrays.asList( "clean", "install" ) );

	 	    Invoker invoker = new DefaultInvoker();
	    	
			invoker.execute( request );
		} catch (MavenInvocationException e) {
		
			e.printStackTrace();
			
			CoreApplicationStarterLogger.error("Cannot build Application");
		} 
	    catch (InternalError e) {
		
			e.printStackTrace();
			
			CoreApplicationStarterLogger.error("Cannot Find POM File");
		}
	    
	    
		
	}
}
