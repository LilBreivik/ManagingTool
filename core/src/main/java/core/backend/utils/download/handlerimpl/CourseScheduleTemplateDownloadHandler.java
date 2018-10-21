package core.backend.utils.download.handlerimpl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.error.FileIsMissingError;
import resources.error.InternalError;
import resources.utils.pathmanager.PathManager;

@Component 
public class CourseScheduleTemplateDownloadHandler 
									extends GeneralXMLFileDownloadHandler{

	@Autowired  
	@Qualifier("PathManager to Lecture Assets")
	private PathManager p_PathManager; 
	
	
	@Override
	public void processDownload(HttpServletResponse fileToDownload) {
		
		final String generalCourseScheduleTemplateFile = "GeneralCourseScheduleTemplateFile"; 
		
		
		p_PathManager.getPathOfFile(generalCourseScheduleTemplateFile);

		try {
			
			super.processDownload(p_PathManager.getPathOfFile(generalCourseScheduleTemplateFile).toFile(), 
					fileToDownload);
		}
		catch(InternalError templateFileErrorMissing) {
			
			FileIsMissingError missingFileError = new FileIsMissingError("Die Datei " + generalCourseScheduleTemplateFile+ " ist nicht vorhanden");
			
			missingFileError.missingFileName = generalCourseScheduleTemplateFile; 
				
	    	missingFileError.missingFileCause = "heruntergeladen"; 
				
			throw missingFileError;
			
		}
		
	}
}
