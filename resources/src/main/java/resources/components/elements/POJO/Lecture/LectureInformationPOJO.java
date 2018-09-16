package resources.components.elements.POJO.Lecture;

import org.codehaus.jackson.annotate.JsonIgnore;

import resources.components.elements.XML.XMLLectureElement;

/**
 * This class is some kind 
 * of an adapter for handling Lecture Schedule
 * from the original surce file
 * */

public class LectureInformationPOJO 
										extends PersistentLectureInformation{

	private String name; 
	
	private String shortcut; 
	
	private String practice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	} 
	 
	public static LectureInformationPOJO createLectureInformationPOJO(XMLLectureElement lectureElement){
		 
		 LectureInformationPOJO pojo = new LectureInformationPOJO();
		
	     pojo.setName(lectureElement.getM_lectureNameXMLElement().getM_elementValue());
		 pojo.setPractice(lectureElement.getM_lecturePracticeXMLElement().getM_elementValue() );
		 pojo.setShortcut(lectureElement.getM_lectureShortcutXMLElement().getM_elementValue() );
		 
		 return pojo; 
	}
	
	public boolean isEquivalent(LectureInformationPOJO pojo) {
        
		return this.getName().equals(pojo.getName());
    }

    @Override
    public boolean equals(Object obj) {
       
        if (obj instanceof LectureInformationPOJO) {
         
        	return this.isEquivalent((LectureInformationPOJO) obj); 
        }
        return false;
    }

	@Override
	@JsonIgnore
	public LectureNameIdentifier getLectureNameIdentifier() {
		 
		return new LectureNameIdentifier(name);
	}
}
