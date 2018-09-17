package resources.components.elements.POJO.Course;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import resources.components.elements.XML.XMLCourseElement;

/**
 * This Class describes the POJO 
 * that is needed to handle 
 * a the Schedule of all Courses.. 
 * */

public class CourseSchedulePOJO {

	// Course Name
	private String courseName;
	
	// Course Term
	private String courseTerm;
	
	// Course Degree 
	private String courseDegree;
	
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
	
	
	public String getCourseDegree() {
		return courseDegree;
	}
	
	public void setCourseDegree(String courseDegree) {
		this.courseDegree = courseDegree;
	}
	
	public String getCourseTerm() {
		return courseTerm;
	}
	
	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
