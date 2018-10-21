package resources.components.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * This class shall provide a 
 * way to create Components on runtime
 * */

@Component
public class ComponentsManufactory {

	private static ApplicationContext m_context;
	 	
	@Autowired
	public ComponentsManufactory(ApplicationContext context) {
		m_context = context;
	}
	
	public static ApplicationContext getContext() {
	        return m_context;
	}
	
	/**
	 * This Method will create the demanded 
	 * Component 
	 * 
	 * @param (String) beanName, (Class Name with the first letter in lowerCase)
	 * @param (Class<T>) classObject , (Class Object of the demanded Component)
	 * 
	 * Unchecked Exceptions: 
	 * 
	 * @throws (InternalError), thworn cause of an ClassCastEception
	 * */
	
	public static <T> T createComponent(String beanName, Class<T> clazz) {
		
		System.out.println();
		System.out.println("bean name " + beanName);
		System.out.println("class " + clazz);
		try {
			 
			
			return clazz.cast(m_context.getBean(beanName));
		}
		catch (ClassCastException cannotCastToComponent){
			
			throw new InternalError("Cannot Create Component " + beanName );
		}
		
	} 
}
