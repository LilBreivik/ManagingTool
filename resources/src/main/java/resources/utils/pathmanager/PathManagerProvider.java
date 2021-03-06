package resources.utils.pathmanager;
 

import static resources.utils.general.Constants.ScheduleRootDiectory;
import static resources.utils.general.Constants.Directory.CoursesAssets;
import static resources.utils.general.Constants.Directory.Database;
import static resources.utils.general.Constants.Directory.Greetings;
import static resources.utils.general.Constants.Directory.Hibernate;
import static resources.utils.general.Constants.Directory.JSONFiles;
import static resources.utils.general.Constants.Directory.LecturesAssets;
import static resources.utils.general.Constants.Directory.Network;
import static resources.utils.general.Constants.Directory.Properties; 
import static resources.utils.general.Constants.Directory.main;
import static resources.utils.general.Constants.Directory.resources; 
import static resources.utils.general.Constants.Directory.src;
import static resources.utils.general.Constants.Directory.test;
import static resources.utils.general.Constants.Directory.Users; 
import static resources.utils.general.Constants.Directory.Files; 
import static resources.utils.general.Constants.Directory.ChangeLog; 
import static resources.utils.general.Constants.Directory.Logging;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
 
 

/**
 * Class that globally
 * contains Bean methods to supply 
 * PathManger Objects, to build Components
 * 
 * */
 
@Configuration
public class PathManagerProvider {

	@Bean
	@Qualifier("PathManager to Working Directory")
	public PathManager pathToWorkingDirectory(){
		
		return new PathManager(new String[0]);
	}
	
	@Bean
	@Qualifier("PathManager to ChangeLog")
	public PathManager configurePathMangagerForChangeLog(){
		
		return new PathManager(Logging, ChangeLog);
	}
	
	
	@Bean
	@Qualifier("PathManager to Lecture Assets")
	public PathManager configurePathMangagerForLecturesAssets(){
		
		return new PathManager(ScheduleRootDiectory, LecturesAssets);
	}
	
	@Bean
	@Qualifier("PathManager to Course Assets")
	public PathManager configurePathMangagerForCoursesAssets(){
		 
		return new PathManager(ScheduleRootDiectory, CoursesAssets);
	}
	 
	
	@Bean
	@Qualifier("PathManager to JSONFiles")
	public PathManager configurePathMangagerForJSONFilesAssets(){
		 
		return new PathManager(ScheduleRootDiectory, JSONFiles);
	}
	
	
	@Bean
	@Qualifier("PathManager to Greetings Properties")
	public PathManager configurePathMangagerForGreetingsProperties(){
		
		return new PathManager(Properties, Greetings);
	}
	
	@Bean
	@Qualifier("PathManager to Network Properties")
	public PathManager configurePathMangagerForNetworkProperties(){
		 
		return new PathManager(Properties, Network);
	}
	

	@Bean
	@Qualifier("PathManager to Database Properties")
	public PathManager configurePathMangagerForDatabaseProperties(){
		 
		return new PathManager(Properties, Database);
	}
	
	
	@Bean
	@Qualifier("PathManager to Hibernate Properties")
	public PathManager configurePathMangagerForHibernateProperties(){
		 
		return new PathManager(Properties, Hibernate);
	}
	 
	@Bean
	@Qualifier("PathManager to Resource")
	public PathManager configurePathMangagerForResources(){
		 
		return new PathManager(src, main, resources);
	}
	
	@Bean
	@Qualifier("Path to Test Resources")
	public PathManager configurePathMangagerForTestResources(){
		  
		return new PathManager(src, test, resources, Files);
	} 
	   
	 
	@Bean
	@Qualifier("Path to User Notices")
	public PathManager configurePathMangagerForUserNotices(){
		
		return new UserPathManager(ScheduleRootDiectory, JSONFiles, Users);
	}
}
