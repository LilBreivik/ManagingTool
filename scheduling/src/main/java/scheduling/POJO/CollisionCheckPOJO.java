package scheduling.POJO;

import java.util.List;

import resources.components.elements.POJO.Course.CoursePOJO;
import resources.components.elements.POJO.Lecture.LecturePOJO;
 

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
