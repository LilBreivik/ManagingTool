package core.controller.parameter;
 
/**
 * This class defines 
 * 
 * the common parameter, to handle 
 * a schedule-dependent request
 * */

public class ScheduleParam {

    protected String courseName; 
	
	protected String courseDegree; 
	
	protected String courseTerm;
 
	
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
 
}
