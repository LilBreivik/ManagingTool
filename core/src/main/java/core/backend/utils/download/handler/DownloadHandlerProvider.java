package core.backend.utils.download.handler;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.io.Files;

import core.backend.utils.download.handlerimpl.CourseScheduleTemplateDownloadHandler;
import core.backend.utils.download.handlerimpl.GeneralXLSFileDownloadHandler;
import core.backend.utils.download.handlerimpl.GeneralXMLFileDownloadHandler;

@Configuration
public class DownloadHandlerProvider {

	@Bean
	@Qualifier("provide DownloadHandler for GeneralCourseScheduleTemplateFile")
	@Autowired
	public DownloadHandler provideDownloadHandlerForGeneralCourseScheduleTemplateFile(
			CourseScheduleTemplateDownloadHandler GeneralCourseScheduleTemplateFileDownloadHandler) {
		
	 
		return GeneralCourseScheduleTemplateFileDownloadHandler;
	}
	
	@Bean
	@Qualifier("provide DownloadHandler for CourseScheduleFile")
	public DownloadHandler provideDownloadHandlerForGeneralCourseScheduleFile() {
		 
		return new GeneralXMLFileDownloadHandler();
	}
	
	@Bean
	@Qualifier("provide DownloadHandler for LectureScheduleFile")
	public DownloadHandler provideDownloadHandlerForLectureScheduleFile() {
		 
		 
		
		
	  return new GeneralXLSFileDownloadHandler();
	}
	 
}
