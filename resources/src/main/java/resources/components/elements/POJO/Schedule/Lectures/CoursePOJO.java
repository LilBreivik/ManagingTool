package resources.components.elements.POJO.Schedule.Lectures;

import java.util.ArrayList;
import java.util.Collection;

public class CoursePOJO {

	private String courseName;
		
	private String courseDegree;
		
	private String courseTerm;

	private Collection<SemesterPOJO> semesters;
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public Collection<SemesterPOJO> getSemesters() {
		return semesters;
	}

	public void setSemesters(Collection<SemesterPOJO> semesters) {
		this.semesters = semesters;
	}
	
	
	public void addSemester(SemesterPOJO semester ) {
		
        try {
			
        	semesters.add(semester);
		}
		catch(UnsupportedOperationException | NullPointerException cannotAddEx) {
			
			semesters = new ArrayList<SemesterPOJO>();
			
			semesters.add(semester);
		}
	}
}
