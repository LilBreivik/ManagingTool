package resources.components.elements.POJO.Persistence.Course;

import java.util.Collection;

import resources.components.elements.POJO.Course.CourseSchedulePOJO;
 
public class PersistenceCourseSchedulePOJO {

	//  all courses as a POJO Collection
	
	private Collection<CourseSchedulePOJO> CoursesSchedulePOJO;
	
	public Collection<CourseSchedulePOJO> getCoursesSchedulePOJO() {
		return CoursesSchedulePOJO;
	}

	public void setCoursesSchedulePOJO(Collection<CourseSchedulePOJO> m_CoursesPOJO) {
		this.CoursesSchedulePOJO = m_CoursesPOJO;
	}
	
	public static PersistenceCourseSchedulePOJO createCourseSchedulePOJO(Collection<CourseSchedulePOJO> coursesSchedulePOJO) {
	
		PersistenceCourseSchedulePOJO courseSchedulePOJO = new PersistenceCourseSchedulePOJO(); 
		
		courseSchedulePOJO.setCoursesSchedulePOJO(coursesSchedulePOJO);
		
		return courseSchedulePOJO;
	}
}
