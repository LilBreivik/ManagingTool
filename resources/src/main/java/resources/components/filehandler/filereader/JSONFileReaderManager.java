package resources.components.filehandler.filereader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JSONFileReaderManager {

	private Class<? extends Object> m_classOfPOJO; 
	
	public JSONFileReaderManager(Class<? extends Object> classOfPOJO) {
		
		m_classOfPOJO = classOfPOJO;
	}
 
	public Object getReaderObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Constructor<?> ctor = m_classOfPOJO.getConstructor();
		Object object = ctor.newInstance();
		
		return object; 
	}
}
