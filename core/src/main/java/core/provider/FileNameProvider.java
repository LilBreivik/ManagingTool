package core.provider;

import static core.utils.Constants.UploadFileName.CourseSchedule;
import static core.utils.Constants.UploadFileName.LectureSchedule;

import core.backend.REST.fileasset.download.parameter.request.DownloadCourseLectureFileParameter;
import core.backend.REST.general.request.MasterRESTRequest; 
import core.utils.names.FileNameResolver;
import scheduling.utils.IScheduleParam;

public class FileNameProvider 
								extends MasterProvider<FileNameResolver>{


	public static FileNameResolver provideFileNameResolverForCourseScheduleFile( MasterRESTRequest requestParam) {
		
		
		return new FileNameResolver( (IScheduleParam) requestParam, 
				(resolvedScheduleFileName) -> CourseSchedule.toString().concat( resolvedScheduleFileName )); 
	}

	public static FileNameResolver provideFileNameResolverForLectureScheduleFile( MasterRESTRequest requestParam) {
		 
		return new FileNameResolver( (IScheduleParam) requestParam, 
				(resolvedScheduleFileName) -> LectureSchedule.toString().concat( resolvedScheduleFileName )); 
	} 
	
	
	
}
