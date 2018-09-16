package bootstrapping.properties;

import bootstrapping.properties.PropertyAlias.Alias;

public abstract class PropertyKey {

	private Alias m_propertyName; 
	
	private String m_propertyValue; 
	
	public PropertyKey(PropertyAlias propertyAlias) {
		
		 m_propertyName = propertyAlias.getM_alias();
		
		 m_propertyValue = PropertyAlias.resolveAlias(m_propertyName);
				 
	}
	 

	public String getM_propertyName() {
		return m_propertyName.toString();
	}
 
	public String getM_propertyValue() {
		return m_propertyValue;
	}
 
}
