package resources.components.elements.POJO.Lecture;


public class LectureNameIdentifier{
	
	private String m_lectureNameIdentifier; 
	
	public LectureNameIdentifier(String lectureNameIdentifier) {
		
		m_lectureNameIdentifier = lectureNameIdentifier;
	}
	
	public String getlectureNameIdentifier() {
		
		return m_lectureNameIdentifier;
	}
	
	public boolean equals(String possibleIdentifier) {
	
		String identifier = (new StringBuilder(possibleIdentifier.toLowerCase())).reverse().toString();
		
		String identifierToCompare = (new StringBuilder(m_lectureNameIdentifier.toLowerCase())).reverse().toString();
		 
		return identifierToCompare.contains(identifier);
		 
	}
	
}