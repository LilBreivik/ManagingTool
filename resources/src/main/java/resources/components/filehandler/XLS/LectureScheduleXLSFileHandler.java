package resources.components.filehandler.XLS;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.components.filehandler.assetsmanager.FileRepositoryManager;
import resources.constraint.LectureConstraint;

@Component
public class LectureScheduleXLSFileHandler extends XLSFileHandler {
 
	@Autowired
	public LectureScheduleXLSFileHandler( LectureConstraint lectureConstraint, 
		@Qualifier("FileRepository Manager for Lectures Assets") FileRepositoryManager filerepositorymanager) {
		super( lectureConstraint, filerepositorymanager); 
	}
  
}

