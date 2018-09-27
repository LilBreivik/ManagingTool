package resources.utils.general;
 
/**
 * This class defines 
 * Constants that are used in the 
 * whole Application 
 * */

public class Constants {

	public static enum Days {Montag, Dienstag , Mittwoch , Donnerstag , Freitag}
	
	
	
	// The possible directories (sub directories), where files shall be stored 
	
    public static enum Directory{
		
		ScheduleFiles, Properties, Greetings, Network,
		JSONFiles, CoursesAssets, LecturesAssets, Database, Hibernate,
		src, main, resources, core, test, Files 
	}

    // The root directory , where the sub directories exist 
    
    public static Directory ScheduleRootDiectory = Directory.ScheduleFiles;
    
    public static Directory PropertiesRootDiectory = Directory.Properties;
    
    
    
    /*
     * Possbile Nodes xml node names, that 
     * are used to filter contents
     * */
    
    public static enum XMLScheduleFilesRootNodes{
		
		courses, course, project
	}
    
    public static enum XMLCourseScheduleFilesNodes{
		
    	courseName, courseTerm, courseDegree
	}
    
     
}
