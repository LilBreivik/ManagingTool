package core.utils.names;


import static core.utils.Constants.UploadFileName.GeneralCourseScheduleTemplateFile;

public class CourseScheduleTemplateFileNameResolver
													extends  TemplateFileNameResolver {

	
	public CourseScheduleTemplateFileNameResolver() {
		
		p_templaeFileName = GeneralCourseScheduleTemplateFile.toString();
	}
}
