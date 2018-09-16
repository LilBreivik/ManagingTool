package scheduling.elements.POJO;


public class LecturePOJO extends ScheduledLecturePOJO{

	
	/*
	 * 
	 * [Log] {"semesterNo":["3. Fachsemester"],
	 *        "courses":["Angewandte Informatik"],
	 *         
	 *         "lectureId":"6eeNn","
	 *         lectureName":"Software Engineering (Übung)",
	 *         "startTime":"08:00",
	 *         "endTime":"10:00",
	 *         "isPractice":true,
	 *         "day":"Donnerstag"} 
	 * */
	 
	 

	// SemesterNr of the lecture 
    private String[] semesterNo;
 
    // course  
    private String[] courses;
     
    // degree of the lectures
    private String degree;
       
    // shortcut of the lecture 
    private String lectureNameShortcut;
	
    // identifier if the lecture is a practice or not 
    private boolean isPractice;
 
    // we will get the 
    // semester as a tupel (the first one is the actual choosen one, the other one is the parallel semester)
    public String getCurrentSemester() {
    	
     	return this.semesterNo[0];
    }
    
    public String getParallelSemester() {
    
    	return this.semesterNo[1];
	}

    // we will get the 
    // courses as a tupel (the first one is the actual choosen one, the other one is the parallel course)
    public String getCurrentCourse() {
    	
     	return this.courses[0];
    }
    
    public String getParallelCourse() {
	
		return this.courses[1];
	}
    
  	public String getLectureName() {
		return lectureName;
	}


	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}


	public String getLectureId() {
		return lectureId;
	}


	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}


	public boolean getisPractice() {
		return isPractice;
	}

	
	public void setisPractice(boolean isPractice) {
		this.isPractice = isPractice;
	}


	public String getLectureNameShortcut() {
		return lectureNameShortcut;
	}


	public void setLectureNameShortcut(String lectureNameShortcut) {
		this.lectureNameShortcut = lectureNameShortcut;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String[] getSemesterNo() {
		return semesterNo;
	}


	public void setSemesterNo(String[] semesterNo) {
		this.semesterNo = semesterNo;
	}


	public String[] getCourses() {
		return courses;
	}


	public void setCourses(String[] courses) {
		this.courses = courses;
	}
 
}
