package resources.components.filehandler.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component; 
import resources.components.elements.POJO.Schedule.Lectures.CoursePOJO; 
import resources.components.filehandler.assetsmanager.FileAssetsManager;

@Component 
public class CourseScheduleJSONFileHandler extends JSONFileHandler{
 
	@Autowired
	public CourseScheduleJSONFileHandler(@Qualifier("FileAssetsManager for the JSON  Assets") 
											FileAssetsManager fileassetsmanager ) {
		super(CoursePOJO.class,  fileassetsmanager);   

	}
}
 