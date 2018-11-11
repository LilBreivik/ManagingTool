package core.provider;
 
import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Course.CoursePOJO; 

public class FileNameProvider 
								extends MasterProvider<FileNameResolver>{
	
	public static FileNameResolver provideFileNameResolverForCourseScheduleFile( CoursePOJO pojo) {
		
		return new FileNameResolver( pojo, 
				NameResolverProvider.provideNameResolverForCourseScheduleFile()); 
	}

	public static FileNameResolver provideFileNameResolverForLectureScheduleFile( CoursePOJO pojo) {
		 
		return new FileNameResolver( pojo, 
				NameResolverProvider.provideNameResolverForLectureScheduleFile()); 
	} 
}
