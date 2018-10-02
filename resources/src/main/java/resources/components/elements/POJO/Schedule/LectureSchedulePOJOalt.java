package resources.components.elements.POJO.Schedule;

import java.util.List;

import resources.components.elements.POJO.Lecture.Timing.LectureDayTimingsPOJO;
 
public class LectureSchedulePOJOalt {

	private String lectureName;
	
	private String lectureNameShortcut;
		
	private boolean isPractice;
	
	private List<LectureDayTimingsPOJO> timingsForALecture;
 
	public String getLectureNameShortcut() {
		return lectureNameShortcut;
	}

	public void setLectureNameShortcut(String lectureNameShortcut) {
		this.lectureNameShortcut = lectureNameShortcut;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public List<LectureDayTimingsPOJO> getTimingsForALecture() {
		return timingsForALecture;
	}

	public void setTimingsForALecture(List<LectureDayTimingsPOJO> timingsForALecture) {
		 
		this.timingsForALecture = timingsForALecture;
	}

	public boolean isPractice() {
		return isPractice;
	}

	public void setPractice(boolean isPractice) {
		this.isPractice = isPractice;
	}
}
