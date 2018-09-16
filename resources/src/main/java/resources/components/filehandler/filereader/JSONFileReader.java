package resources.components.filehandler.filereader;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
 
import org.codehaus.jackson.map.ObjectMapper;

import resources.components.filehandler.PathManager;
 

public class JSONFileReader {

	 private Path m_PathWhereJSONShallBeWritten;
	
	 private JSONFileReaderManager m_JSONFileReaderManager;
	 
	 public JSONFileReader(PathManager managerForDemandedPath, JSONFileReaderManager requestedJSONFileReaderManager) {
			
		 m_PathWhereJSONShallBeWritten = managerForDemandedPath.getPathToOperateOn();
		 m_JSONFileReaderManager = requestedJSONFileReaderManager; 
	 }
	 
	 
	
    public Object readFile(String fileName) {
				
		ObjectMapper mapper = new ObjectMapper();
		   
		// We need to lease the reader object , to get the class 
		// and to force  "CastException free " readValue result 
		
		Object dummyObject = null;
	 
		try {
			
			dummyObject = m_JSONFileReaderManager.getReaderObject();
			
		 
			File JSONFileThatisRead = new File(m_PathWhereJSONShallBeWritten.toFile(), fileName);
			
			dummyObject = mapper.readValue(JSONFileThatisRead, m_JSONFileReaderManager.getReaderObject().getClass());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException |   IOException cannotReadJSON) {
	
			cannotReadJSON.printStackTrace(); 
			
			if(cannotReadJSON instanceof FileNotFoundException) {
				
				throw new resources.error.InternalError("Cannot read expected JSON " + fileName + " maybe you need to Upload Lecture Files");
			}
			else {

				throw new resources.error.InternalError("Cannot do ");
			}
			
		}
		 
		
		return dummyObject; 			
	}
}
