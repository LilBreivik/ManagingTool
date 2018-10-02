package resources.components.elements.POJO.Schedule;

import java.util.List;
import resources.components.elements.POJO.Scheduling.Lectures.LecturePOJO;

public class CollisionCheckPOJO 
											extends CoursePOJO{
	
	// list of the scheduled Lectures 
	protected   List<LecturePOJO> lecturesList;

	public List<LecturePOJO> getLecturesList() {
		return lecturesList;
	}

	public void setLecturesList(List<LecturePOJO> lecturesList) {
		this.lecturesList = lecturesList;
	}
	 
}
