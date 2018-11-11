package core.provider;

import static core.utils.Constants.UploadFileName.CourseSchedule;
import static core.utils.Constants.UploadFileName.LectureSchedule; 

import resources.utils.names.INameResolver;

public class NameResolverProvider extends MasterProvider<INameResolver>{
	 

	public static INameResolver provideNameResolverForCourseScheduleFile() {
		
		return (resolvedScheduleFileName) -> CourseSchedule.toString().concat( resolvedScheduleFileName ); 
	}

	public static INameResolver provideNameResolverForLectureScheduleFile( ) {
		 
		return (resolvedScheduleFileName) -> LectureSchedule.toString().concat( resolvedScheduleFileName ); 
	}
 
}
