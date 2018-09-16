package resources.components.elements.POJO.Lecture;

import java.util.HashMap;

import resources.utils.general.GeneralPurpose;
 
public abstract class POJOBuilderRecipe 
									extends HashMap<String,Object>    {

	protected String[] m_requiredKey; 
	
	public POJOBuilderRecipe (String... requiredKey) {
		
		m_requiredKey = requiredKey;
	}

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
