package resources.components.filewriter.JSON;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import resources.components.filewriter.general.IFileWriter; 

public class  GeneralJSONFileWriter < WrittenObjectTyp > 
												implements IFileWriter< WrittenObjectTyp >{
 	  
	@Override
	public  void writeToFile(File file, WrittenObjectTyp contentToBeWritten)  throws JsonGenerationException, JsonMappingException, IOException {
		    
		ObjectMapper mapper = new ObjectMapper();
		   	 
		mapper.writeValue(file, contentToBeWritten);
		 	 
	}

}
