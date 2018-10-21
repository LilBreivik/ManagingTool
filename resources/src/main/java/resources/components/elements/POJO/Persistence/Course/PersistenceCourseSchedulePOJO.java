package resources.components.elements.POJO.Persistence.Course;

import java.util.Collection;
import java.util.List;

import resources.components.elements.POJO.Course.CourseSchedulePOJO;
import resources.utils.general.GeneralPurpose;
 
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
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof PersistenceCourseSchedulePOJO) {
			
			PersistenceCourseSchedulePOJO PersistencePojo = (PersistenceCourseSchedulePOJO) obj;
			
			List<CourseSchedulePOJO> schedulePOJOList = GeneralPurpose.CollectionToList(PersistencePojo.getCoursesSchedulePOJO());
			
			List<CourseSchedulePOJO> schedulePOJOListToCompare = GeneralPurpose.CollectionToList( getCoursesSchedulePOJO());
			
			// if the lists do not have the same ammount of CourseSchedulePOJO elements, they cannot be the same 
			
			if(schedulePOJOList.size() == schedulePOJOListToCompare.size()) {
				
				// the lists cannot be same, if they got the same permutation of elements 
				
				boolean flag = true; 
				
				for(int itr = 0; itr < schedulePOJOList.size(); itr += 1) {
					 
					// we check all pojos , after each other
					
					if(!(schedulePOJOList.get(itr).equals(schedulePOJOListToCompare.get(itr)))) {
					
						// if the elements are not the same, the lists cannot be the same, too
						
						 flag = false; 
						 break; 
					}
				}
				
				return flag; 
			}
			else {
			
				return false; 
			}
		}
		else {
			
			return false; 
		}
	}
}
