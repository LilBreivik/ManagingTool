package resources.components.elements.POJO.Lecture;

import java.util.HashMap;
import java.util.List;

import resources.utils.general.GeneralPurpose;
 

/**
 * Class that describes some 
 * building unit, 
 * that shall help to create a 
 * pojo, which contains 
 * some information from a certain file 
 * 
 * @param (POJOToBeBuild), pojo class that shall be build 
 * */

public abstract class POJOBuilderRecipe<POJOToBeBuild> 
									extends HashMap<String,Object>    {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6875898916465116166L;
	protected String[] m_requiredKey; 
	
	public POJOBuilderRecipe (String... requiredKey) {
		
		m_requiredKey = requiredKey;
	}
	


	/**
	 * Method that shall create a certain pojo, 
	 * which contains some data read before ... 
	 * */
	
	public abstract POJOToBeBuild buildPOJO();
	
	@Override
	public Object put(String key, Object value) {
		
		if(GeneralPurpose.ArrayToCollection(m_requiredKey).contains(key)) {
			
			return super.put(key, value);
		}
		else {
		
			return null;
		}
	}
	
}
