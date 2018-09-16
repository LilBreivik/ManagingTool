package resources.components.filehandler.JSON;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO; 
import resources.components.filehandler.PathManager;
import resources.components.filehandler.assetsmanager.FileAssetsManager;

@Component 
public class SynthesizedCourseScheduleJSONFileHandler extends JSONFileHandler{
  
	@Autowired
	public SynthesizedCourseScheduleJSONFileHandler(@Qualifier("FileAssetsManager for the JSON  Assets") 
	FileAssetsManager fileassetsmanager  ) {
		super(LectureScheduleOfCoursePOJO.class, fileassetsmanager);   

	}
}
 