package resources.components.elements.POJO.Pom;

import java.util.Collection;
import java.util.NoSuchElementException;

import resources.components.elements.XML.XMLElement;  
public class PomXMLPOJO {

	private String versionName;

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	} 
	
	public static PomXMLPOJO createPomXMLPOJO(Collection<XMLElement> elements) {
		
		PomXMLPOJO pojo = new PomXMLPOJO(); 
		
		try {
		
			XMLElement version = elements.stream()
					  .filter(element -> element.getM_elementName().equals("version"))
					  .findFirst().get();
			
			pojo.setVersionName(version.getM_elementValue());
			
			return pojo;
		}
		catch(NoSuchElementException noVersionName) {
			
			return null; 
		}
		
	}
}
