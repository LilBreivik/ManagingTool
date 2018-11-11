package resources.components.filewriter.general;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

public interface IFileWriter<WrittenObjectType> {

	void writeToFile(File file, WrittenObjectType contentToBeWritten) throws JsonGenerationException, JsonMappingException, IOException;
 
}
