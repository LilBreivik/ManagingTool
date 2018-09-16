package resources.components.filehandler.filecreator;

import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
 
import org.codehaus.jackson.map.ObjectMapper;

import resources.components.filehandler.PathManager;
 
public class JSONFileCreator implements IFileCreator{
	  
	 
	 private Path m_PathWhereJSONShallBeBuild;

	 private JSONFileCreatorManager m_requestedJSONFileCreatorManager;
				 
	 public JSONFileCreator(PathManager managerForDemandedPath, JSONFileCreatorManager requestedJSONFileCreatorManager) {
			
		 m_PathWhereJSONShallBeBuild = managerForDemandedPath.getPathToOperateOn();
	 	  
		 m_requestedJSONFileCreatorManager = requestedJSONFileCreatorManager; 
	 }
	 
	 
	 @Override
     public void createFile(String fileName) {
			
			ObjectMapper mapper = new ObjectMapper();
		   	
			File JSONFileHandler = new File(m_PathWhereJSONShallBeBuild.toFile(), fileName);
		 
				try {
					
					mapper.writeValue(JSONFileHandler,  m_requestedJSONFileCreatorManager.getPOJOObject());
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
		}
}
