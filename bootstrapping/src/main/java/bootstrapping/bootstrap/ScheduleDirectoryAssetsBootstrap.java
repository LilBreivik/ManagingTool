package bootstrapping.bootstrap;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.components.filehandler.PathManager;
import resources.error.ConfigurationFailedError;
 

/**
 * This class shall handle the test
 * if the application directories exist ...
 * */
 
@Component
public class ScheduleDirectoryAssetsBootstrap 
								extends BootstrapJob{

	 /**
	 * Schema of the needed Directores: 
	 * 
	 * -> ScheduleFiles 
	 * 			...
	 * 			-> CourseAssets
	 * 			-> JSONFiles
	 * 			-> LecturesAssets
	 *			
	 * */	
	
	private Collection<PathManager> m_pathsToBootstrap;

	@Autowired
	public ScheduleDirectoryAssetsBootstrap(@Qualifier("PathManager to Course Assets") PathManager pathToCourseAssets, 
										@Qualifier("PathManager to Lecture Assets") PathManager pathToLectureAssets,
									@Qualifier("PathManager to JSONFiles") PathManager pathToJSONFilesAssets) 
	
	{
		super("Schedule Directory Bootstrap Job");
		
		m_pathsToBootstrap =  Arrays.asList(pathToCourseAssets,  pathToLectureAssets, pathToJSONFilesAssets);
				 
	}
	

	@Override
	public void bootstrapConfiguration() {
		
		BootstrapJobLogger.info("running ... Bootstrap DirectoryAssets Procedere ");
 
		 
		for(PathManager pathToBootstrap : m_pathsToBootstrap) {
			
			BootstrapJobLogger.debug("Check Directory " + pathToBootstrap.getPathToOperateOn().toString());
			
			pathToBootstrap.createPath();

			if(!pathToBootstrap.doesPathExist()) {
			 	
				configurationFailed(new ConfigurationFailed() {
					
									@Override
									public void commitFail() {
										BootstrapJobLogger.error("This is Error message", new ConfigurationFailedError("Could not create " + pathToBootstrap.getPathToOperateOn().toFile().getName()));		
									}	
								}
				); 
				
				break; 
			}
		
			else {
				
				configurationSucceeded(new ConfigurationSucceeded() {
								
								@Override
								public void commitSucceed() {
									BootstrapJobLogger.info("Directory " + pathToBootstrap.getPathToOperateOn().toString() + " exists");
								}	
							}
				); 
				
				// jsut to make the output easier to read 
				System.out.println("\n");
				
				 
			}
		
		}
		
    }
	
	// We override this succeed method, cause we would Throw Null Pointer due to missing 
	// m_propertyManager..
	
	@Override
	public void configurationSucceeded(ConfigurationSucceeded succeededOperation) {
		
			succeededOperation.commitSucceed();
					
			m_jobFailed = false;
	
	}
	
}
	

