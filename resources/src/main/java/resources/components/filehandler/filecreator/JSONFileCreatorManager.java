package resources.components.filehandler.filecreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JSONFileCreatorManager {

private Class<? extends Object> m_classOfPOJO; 
	
	public JSONFileCreatorManager(Class<? extends Object> classOfPOJO) {
		
		m_classOfPOJO = classOfPOJO;
	}

	public Class<? extends Object> getM_classOfPOJO() {
		return m_classOfPOJO;
	}

	public Object getPOJOObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Constructor<?> ctor = m_classOfPOJO.getConstructor();
		Object object = ctor.newInstance();
		
		return object; 
	}
}
