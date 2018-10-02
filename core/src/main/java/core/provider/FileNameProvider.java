package core.provider;
 
import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Scheduling.Utils.IScheduleParam;

public class FileNameProvider 
								extends MasterProvider<FileNameResolver>{
	
	public static FileNameResolver provideFileNameResolverForCourseScheduleFile( IScheduleParam requestParam) {
		
		return new FileNameResolver(  requestParam, 
				NameResolverProvider.provideNameResolverForCourseScheduleFile()); 
	}

	public static FileNameResolver provideFileNameResolverForLectureScheduleFile(  IScheduleParam requestParam) {
		 
		return new FileNameResolver(  requestParam, 
				NameResolverProvider.provideNameResolverForLectureScheduleFile()); 
	} 
}