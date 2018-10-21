package resources.components.elements.POJO.Course;
 
 
import resources.components.elements.XML.XMLCourseElement; 
/**
 * This Class describes the POJO 
 * that is needed to handle 
 * a the Schedule of all Courses.. 
 * */

public class CourseSchedulePOJO 
									extends CoursePOJO{

	
	
	// user name that changed a COurse XLS File
	private String changedBy;
	
	// time when a COurse XLS File was changed ....
	private long changedAt;
	
	public static CourseSchedulePOJO createCoursePOJO(XMLCourseElement xmlCourseElement) {
		
		CourseSchedulePOJO courseSchedulePOJO = new CourseSchedulePOJO();
		
		// @FixMe change with applicationContext -> get current user... 
		// @Fix eliminate person who did the change ....
		 
		
		courseSchedulePOJO.setChangedBy("Rainer Winkler");
		
		courseSchedulePOJO.setChangedAt(System.currentTimeMillis());
		
		courseSchedulePOJO.setCourseDegree(xmlCourseElement.getM_courseDegreeXMLElement().getM_elementValue());
		
		courseSchedulePOJO.setCourseName(xmlCourseElement.getM_courseNameXMLElement().getM_elementValue());
		
		courseSchedulePOJO.setCourseTerm(xmlCourseElement.getM_courseTermXMLElement().getM_elementValue());
		
		 
		return courseSchedulePOJO; 
	}
	
	

	@Override
	public boolean equals(Object obj) {
		

		if(obj instanceof CourseSchedulePOJO) {
			
			CourseSchedulePOJO pojo = (CourseSchedulePOJO) obj; 
			
			return ((pojo.getCourseName().equals(getCourseName())) && 
									(pojo.getCourseTerm().equals(getCourseTerm())) && 
										(pojo.getCourseDegree().equals(getCourseDegree())));
		}
		else {
			
			return false; 
		}
	}
	


	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public long getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(long changedAt) {
		this.changedAt = changedAt;
	}
	
}
