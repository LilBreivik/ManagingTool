package resources.components.elements.XML;
 

public class XMLElement {

	private String m_elementName; 
	private String m_elementValue; 
	
 	
	public XMLElement(String elementName, String elementValue) {
	
		m_elementName = elementName; 
		m_elementValue = elementValue;
	}

	

	public String getM_elementName() {
		return m_elementName;
	}


	public String getM_elementValue() {
		return m_elementValue;
	}

	
}
