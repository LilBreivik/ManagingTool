package resources.components.filehandler.JSON;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component; 
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO; 
import resources.components.filehandler.assetsmanager.FileAssetsManager;

@Component 
public class PersistenceCourseScheduleJSONFileHandler extends  JSONFileHandler{

	@Autowired
	public PersistenceCourseScheduleJSONFileHandler(@Qualifier("FileAssetsManager for the JSON  Assets") 
	FileAssetsManager fileassetsmanager ) {
	  
		super(PersistenceCourseSchedulePOJO.class, fileassetsmanager);
	}
	 
}

