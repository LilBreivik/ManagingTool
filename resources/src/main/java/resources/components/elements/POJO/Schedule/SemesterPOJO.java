package resources.components.elements.POJO.Schedule;

import java.util.ArrayList;
import java.util.Collection;
 

public class SemesterPOJO {

    private String semesterNr; 
	
	private Collection<LectureSchedulePOJOalt>  LecturesInSemester;

	public String getSemesterNr() {
		return semesterNr;
	}

	public void setSemesterNr(String semesterNr) {
		this.semesterNr = semesterNr;
	}

	public Collection<LectureSchedulePOJOalt> getLecturesInSemester() {
		return LecturesInSemester;
	}

	public void setLecturesInSemester(Collection<LectureSchedulePOJOalt> lecturesInSemester) {
		LecturesInSemester = lecturesInSemester;
	}

	public void addLectureToSemester(LectureSchedulePOJOalt lecturePOJO) {
		  
		
		try {
			
			LecturesInSemester.add(lecturePOJO);
		}
		catch(UnsupportedOperationException | NullPointerException cannotAddEx) {
			
			LecturesInSemester = new ArrayList<>();
			
			LecturesInSemester.add(lecturePOJO);
		}
		 
	}
}
