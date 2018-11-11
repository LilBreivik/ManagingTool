package resources.components.filereader.JSON;
  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; 
import org.codehaus.jackson.map.ObjectMapper; 
import resources.components.filereader.general.IFileReader;
import resources.error.FileProcessingError;  

public class GeneralJSONFileReader< ReadObjectType > 
										implements IFileReader< ReadObjectType > {
  
	private Class<?> m_castingClass;
	  
	public GeneralJSONFileReader(Class<?> castingClass) {
	 
		m_castingClass = castingClass; 
	}
 
	@Override
	@SuppressWarnings("unchecked")
	public ReadObjectType readFile(File file) throws FileNotFoundException, ClassCastException, IOException {
		

        ReadObjectType dummyObject = null;
		
		try {
			
			// initalizing mapper for JSON Library 
					
			ObjectMapper mapper = new ObjectMapper();
				  
			// we read the file via its fileName, thorugh the underlying path 
			// an do a typecasting 
			 
			
			dummyObject =  (ReadObjectType) mapper.readValue(file, m_castingClass);
			
		} catch (Exception readError) {
		 
			if(readError instanceof FileNotFoundException) {
			
				throw new FileNotFoundException();
			}
			else if(readError instanceof IOException) {
			
				throw new FileProcessingError(file);
			}
			else if(readError instanceof ClassCastException) {
			
				throw new ClassCastException();
			}
			 
		}
		 
		return dummyObject;
	}
 
}
