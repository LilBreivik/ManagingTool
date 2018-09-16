package resources.components.elements.XML;

 
import static resources.utils.general.Constants.XMLCourseScheduleFilesNodes.courseDegree;
import static resources.utils.general.Constants.XMLCourseScheduleFilesNodes.courseName;
import static resources.utils.general.Constants.XMLCourseScheduleFilesNodes.courseTerm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import resources.utils.general.Constants.XMLCourseScheduleFilesNodes;

public class XMLCourseElement {


	private XMLElement m_courseNameXMLElement; 
	
	private XMLElement m_courseTermXMLElement;
	
	private XMLElement m_courseDegreeXMLElement;

	public XMLElement getM_courseNameXMLElement() {
		return m_courseNameXMLElement;
	}

	private final static Map<XMLCourseScheduleFilesNodes, XMLCourseElementBuilder> m_courseInformationNames =
			
			new HashMap<XMLCourseScheduleFilesNodes, XMLCourseElementBuilder>() {{
				
				put(courseName, new  XMLCourseElementBuilder() {

					@Override
					public XMLCourseElement setValue(XMLCourseElement elementToSet, 
							XMLElement elementToGetValue) {
						
						elementToSet.setM_courseNameXMLElement(elementToGetValue);
						 
						return elementToSet;
					}
						
				});
				
				
				put(courseTerm, new  XMLCourseElementBuilder() {

					@Override
					public XMLCourseElement setValue(XMLCourseElement elementToSet, 
							XMLElement elementToGetValue) {
						
						elementToSet.setM_courseTermXMLElement(elementToGetValue);
				
						return elementToSet;
					}
						
				});
				
				put(courseDegree, new  XMLCourseElementBuilder() {

					@Override
					public XMLCourseElement setValue(XMLCourseElement elementToSet, 
							XMLElement elementToGetValue) {
						
						elementToSet.setM_courseDegreeXMLElement(elementToGetValue); 
				
						return elementToSet;
					}
						
				});
				

			}};
		 
 	public static  XMLCourseElement  createCourseElement(Collection<XMLElement> xmlElements) {
			
 		XMLCourseElement createdXMLCourseElement = new XMLCourseElement();
	
		for(XMLElement xmlElement :  xmlElements) {
			  
			
			createdXMLCourseElement  =  m_courseInformationNames.
											get(XMLCourseScheduleFilesNodes.
													valueOf(xmlElement.getM_elementName())).
														setValue(createdXMLCourseElement ,
																xmlElement);
									
	
		}
	  
		return createdXMLCourseElement  ; 
	}
	
 	
	interface XMLCourseElementBuilder {
  	  
    	public XMLCourseElement setValue(XMLCourseElement elementToSet, XMLElement elementToGetValue);
    }
	
	private void setM_courseNameXMLElement(XMLElement m_courseNameXMLElement) {
		this.m_courseNameXMLElement = m_courseNameXMLElement;
	}

	public XMLElement getM_courseTermXMLElement() {
		return m_courseTermXMLElement;
	}

	private  void setM_courseTermXMLElement(XMLElement m_courseTermXMLElement) {
		this.m_courseTermXMLElement = m_courseTermXMLElement;
	}

	public XMLElement getM_courseDegreeXMLElement() {
		return m_courseDegreeXMLElement;
	}

	private  void setM_courseDegreeXMLElement(XMLElement m_courseDegreeXMLElement) {
		this.m_courseDegreeXMLElement = m_courseDegreeXMLElement;
	}
 
}
