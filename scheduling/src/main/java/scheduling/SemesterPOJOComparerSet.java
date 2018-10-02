package scheduling;

import java.util.ArrayList;
import java.util.Arrays; 
import java.util.List;

import resources.components.elements.POJO.Schedule.CoursePOJO;
import resources.components.elements.POJO.Schedule.LectureSchedulePOJOalt;
import resources.components.elements.POJO.Schedule.SemesterPOJO;
import resources.utils.general.GeneralPurpose;
import resources.utils.names.NameResolver;

public class SemesterPOJOComparerSet {

	private String courseNameOfScheduledSemester;
	
	private SemesterPOJO scheduledSemester;
	
	private  String courseNameOfParallelSemester;
	
	private List<SemesterPOJO> parallelSemester;

	public String getCourseNameOfScheduledSemester() {
		return courseNameOfScheduledSemester;
	}

	public void setCourseNameOfScheduledSemester(CoursePOJO coursePOJOfScheduledSemester) {
	
		this.courseNameOfScheduledSemester =  (new  CoursePOJONameResolver(coursePOJOfScheduledSemester)).resolveFileName();
	}

	public SemesterPOJO getScheduledSemester() {
		return scheduledSemester;
	}

	public void setScheduledSemester(SemesterPOJO scheduledSemester) {
		this.scheduledSemester = scheduledSemester;
	}

	public String getCourseNameOfParallelSemester() {
		return courseNameOfParallelSemester;
	}

	public void setCourseNameOfParallelSemester(CoursePOJO coursePOJOfParallelSemester) {
	 
		this.courseNameOfParallelSemester=  (new  CoursePOJONameResolver(  coursePOJOfParallelSemester )).resolveFileName();
	}

	public List<SemesterPOJO> getParallelSemester() {
		return parallelSemester;
	}

	public void setParallelSemester(List<SemesterPOJO> parallelSemester) {
		this.parallelSemester = parallelSemester;
	}
	
	
	public static List<SemesterPOJOComparerSet> buildSemesterPOJOComparerSet(CoursePOJO scheduledCourse, 
													CoursePOJO parallelCourse) {
 
		 
		 List<SemesterPOJOComparerSet> comparingSets = new ArrayList<SemesterPOJOComparerSet >();
		  
		 // determine Lectures of requestes
			
		 List<SemesterPOJO> scheduledSemesterPOJO = GeneralPurpose.CollectionToList(scheduledCourse.getSemesters());
			
		 List<SemesterPOJO> parallelSemesterPOJO = GeneralPurpose.CollectionToList(parallelCourse.getSemesters());
			
		 
		 for(int itr = 0; itr < scheduledSemesterPOJO.size(); itr += 1 ) {
			 
			 SemesterPOJOComparerSet comparerSet = new  SemesterPOJOComparerSet();
			 
			 // determine Names of Semesters 
			 
			 comparerSet.setCourseNameOfScheduledSemester(scheduledCourse );
				 
			 comparerSet.setCourseNameOfParallelSemester(parallelCourse);
			 
			 SemesterPOJO semesterPOJO =  scheduledSemesterPOJO.get(itr);
			   
			 List<SemesterPOJO> parallelSemesterPOJOS = Arrays.asList(parallelSemesterPOJO.get(itr  ) ,  
					 parallelSemesterPOJO.get(((itr  ) + (scheduledSemesterPOJO.size() / 2 )) % scheduledSemesterPOJO.size() ));
			  
			 comparerSet.setScheduledSemester(semesterPOJO);
			 
			 comparerSet.setParallelSemester(parallelSemesterPOJOS);
			 
			 comparingSets.add(comparerSet);
		 }
		
		 return comparingSets; 
	}
	 
 
	
	class CoursePOJONameResolver 
								extends NameResolver< CoursePOJO>{

		
		public CoursePOJONameResolver ( CoursePOJO  coursePOJO){
			
			p_resolvingSource = coursePOJO;
		}
		
		
		@Override
		protected String resolveFileName() {
			
			StringBuilder resolvedCoursePOJOName = new StringBuilder();
			
			
			resolvedCoursePOJOName.append(p_resolvingSource.getCourseName());
			resolvedCoursePOJOName.append(" ");
			resolvedCoursePOJOName.append(p_resolvingSource.getCourseDegree());
			resolvedCoursePOJOName.append(" ");
			resolvedCoursePOJOName.append(p_resolvingSource.getCourseTerm());
			
			return resolvedCoursePOJOName.toString();
		}
		
		
	}
}
