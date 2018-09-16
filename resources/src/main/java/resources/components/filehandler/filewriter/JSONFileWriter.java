package resources.components.filehandler.filewriter;

import java.io.File;
import java.io.IOException; 
import java.nio.file.Path;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import resources.components.filehandler.PathManager;
 
public class JSONFileWriter implements IFileWriter{
	
	 private Path m_PathWhereJSONShallBeWritten;
 		 
	 public JSONFileWriter(PathManager managerForDemandedPath) {
			
		 m_PathWhereJSONShallBeWritten = managerForDemandedPath.getPathToOperateOn();
	 }
	 
	 
	@Override
    public void writeToFile(String fileName, Object contentToBeWritten) {
			
			ObjectMapper mapper = new ObjectMapper();
		   	
			File JSONFileHandler = new File(m_PathWhereJSONShallBeWritten.toFile(), fileName);
			
			
			try {
				//Convert object to JSON string and save into file directly 
				mapper.writeValue(JSONFileHandler, contentToBeWritten);
			 	
			} catch (JsonGenerationException e) {
				
				e.printStackTrace();
			} catch (JsonMappingException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
	 			
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
			 	
				e.printStackTrace();
			}  catch (SecurityException e) {
			  
				e.printStackTrace();
			} 
				
		}

}
