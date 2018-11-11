package resources.components.elements.XML;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class XMLLectureElement {

	
	private XMLElement m_lectureNameXMLElement; 
	
	private XMLElement m_lectureShortcutXMLElement;
	
	private XMLElement m_lecturePracticeXMLElement;


	private final static Map<String, XMLLectureBuilder> m_lectureInformationNames =
			new HashMap<String, XMLLectureBuilder>() {{
				
				put("name", new  XMLLectureBuilder() {

					@Override
					public XMLLectureElement setValue(XMLLectureElement elementToSet, 
							XMLElement elementToGetValue) {
						
						elementToSet.setM_lectureNameXMLElement(elementToGetValue);
				
						return elementToSet;
					}
						
				});
				
				
				put("shortcut", new  XMLLectureBuilder() {

					@Override
					public XMLLectureElement setValue(XMLLectureElement elementToSet, 
							XMLElement elementToGetValue) {
						
						elementToSet.setM_lectureShortcutXMLElement(elementToGetValue);
				
						return elementToSet;
					}
						
				});
				
				put("practice", new  XMLLectureBuilder() {

					@Override
					public XMLLectureElement setValue(XMLLectureElement elementToSet, 
							XMLElement elementToGetValue) {
						
						elementToSet.setM_lecturePracticeXMLElement( elementToGetValue);
				
						return elementToSet;
					}
						
				});
				

			}};
		 
	public static  XMLLectureElement  createLectureElement(Collection<XMLElement> xmlElements) {
			
		XMLLectureElement createdXMLLectureElement = new XMLLectureElement();
	
		for(XMLElement xmlElement :  xmlElements) {
			 
			try {
				
				createdXMLLectureElement =  m_lectureInformationNames.get(xmlElement.getM_elementName()).setValue(createdXMLLectureElement,
						xmlElement);
				
			}
			catch(NullPointerException nullEx) {
				nullEx.printStackTrace(); 
			}
			
			
	
		}
	  
		return createdXMLLectureElement ; 
	}
	
	public static Collection<XMLLectureElement> createListOfLectureElements(List<XMLElement> listOfElementsToBeConverteToPOJO ){
		 
		// filter just the needed elements for building the xmlElements needed for the lectures
		
	    List<XMLElement> listOfLectureElements = listOfElementsToBeConverteToPOJO
	    											.stream()
	    											.filter(element ->  (
	    													
	    													element.getM_elementName().equals("name")
	    													||
	    													element.getM_elementName().equals("shortcut")
	    													||
	    													element.getM_elementName().equals("practice")
	    													
	    													) )
	    											.collect(Collectors.toList());
	    
	    
	 
	   return Lists.partition(listOfLectureElements, 3)
	    							.stream()
	    							.map(XMLNeededToBuildSingleLectureXMLElement -> createLectureElement(XMLNeededToBuildSingleLectureXMLElement))
	    							.collect(Collectors.toList());
	   
	}
	
	
	
	public XMLElement getM_lectureNameXMLElement() {
		return m_lectureNameXMLElement;
	}

	public void setM_lectureNameXMLElement(XMLElement m_lectureNameXMLElement) {
		this.m_lectureNameXMLElement = m_lectureNameXMLElement;
	}

	public XMLElement getM_lectureShortcutXMLElement() {
		return m_lectureShortcutXMLElement;
	}

	public void setM_lectureShortcutXMLElement(XMLElement m_lectureShortcutXMLElement) {
		this.m_lectureShortcutXMLElement = m_lectureShortcutXMLElement;
	}

	public XMLElement getM_lecturePracticeXMLElement() {
		return m_lecturePracticeXMLElement;
	}
	
	public boolean doesLectureHaveSomePractice() {
		 
		return m_lecturePracticeXMLElement.getM_elementValue().equals("Ja") ? true : false;
	}

	public void setM_lecturePracticeXMLElement(XMLElement m_lecturePracticeXMLElement) {
		this.m_lecturePracticeXMLElement = m_lecturePracticeXMLElement;
	} 

	
	interface XMLLectureBuilder {
 	  
	   	public XMLLectureElement setValue(XMLLectureElement  elementToSet, XMLElement elementToGetValue);
    }
	 
	
}