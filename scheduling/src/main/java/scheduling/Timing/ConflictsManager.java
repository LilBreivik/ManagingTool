package scheduling.Timing;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.HashSet;
import java.util.List; 
import java.util.stream.Collectors;
import java.util.stream.Stream;

import resources.components.elements.POJO.Lecture.LecturePOJO; 
import resources.error.scheduling.CollisionError;
import resources.utils.general.Constants;
import resources.utils.general.GeneralPurpose;
import resources.utils.general.Constants.Days;
import scheduling.POJO.DaysTimeBlocks; 
import scheduling.POJO.ScheduleLectureTimeBlock; 
import scheduling.Utils.UniqueFilter; 

public class ConflictsManager {
 
	private SchedulingWeek scheduleDays; 
	 
	
	private List<ScheduleLectureTimeBlock> collidedLectures; 
	 
  //  private List<ScheduleLectureTimingPOJO> scheduledCourseSemester;
	
	// lectures of the choosen course and semester 
	
    public List<ScheduleLectureTimingPOJO> courseLecturesInFirstSemester;
	
    // practices of the choosen course and semester 
    
    public List<ScheduleLectureTimingPOJO> coursePracticesInFirstSemester;
    
    // lectures of the choosen course and next semester 
    
    public List<ScheduleLectureTimingPOJO> courseLecturesInSecondSemester;
	
    // practices of the choosen course and next semester 
    
    public List<ScheduleLectureTimingPOJO> coursePracticesInSecondSemester;
	
    // lectures of the parallel course and semester 
	
	private List<ScheduleLectureTimingPOJO> paralledCourseLecturesInFirstSemester;
	
	// practices of the parallel course and next semester 
	
	private List<ScheduleLectureTimingPOJO> paralledCoursePracticesInFirstSemester;
	 
	// practices of the parallel course and next semester 
	
	private List<ScheduleLectureTimingPOJO> paralledCourseLecturesInSecondSemester;
	
	// practices of the parallel course and next semester 
	
	private List<ScheduleLectureTimingPOJO> paralledCoursePracticesInSecondSemester;
	
	 

	 
	public void permutateLectures() {
		 
		List<Days> days = GeneralPurpose.ArrayToList(Days.values());
			 
		Collections.shuffle( days);
				
		for(Days day : days ) {
				
			SchedulingDay scheduledDay = scheduleDays.get(GeneralPurpose.ArrayToList(Days.values()).indexOf(day));
				
			DaysTimeBlocks  freeBlocksOfACertainScheduledDay = scheduledDay.getFreeTimeBlocksOfTheDay();
				
			FreeBlocksSchedulingDay freeBlocksOnACertainSchedulingDay = new FreeBlocksSchedulingDay(scheduledDay.getM_scheduleDay(), 
						freeBlocksOfACertainScheduledDay);	
				
				
				for(int itr = 0; itr < collidedLectures.size(); itr += 1) {
					
					// [This TimeBlock null expands between 08:00 : 12:00, This TimeBlock null expands between 14:00 : 20:00]
					ScheduleLectureTimeBlock conflict = collidedLectures.get(itr);
					System.out.println("conflict " + conflict);
					System.out.println("itr " + itr);
					
					try {
						
						freeBlocksOnACertainSchedulingDay.occupy(conflict);
						collidedLectures.remove(conflict);
						
						// we need to decrease the counter, cuase an element got deleted 
						itr -= 1;
					}
					catch(CollisionError collisonNotSolved) {
						
						System.out.println("block did not get solve " + conflict );
						// try next time 
					}
					
				}
				 
				System.out.println("######");
				System.out.println(scheduledDay.getOccupiedTimeBlocksOfTheDay());
				System.out.println(freeBlocksOnACertainSchedulingDay.getOccupiedTimeBlocksOfTheDay());
				System.out.println("######"); 
				System.out.println(freeBlocksOnACertainSchedulingDay.getFreeTimeBlocksOfTheDay() );
				 
				scheduledDay.getOccupiedTimeBlocksOfTheDay().addAll(freeBlocksOnACertainSchedulingDay.getOccupiedTimeBlocksOfTheDay());
	 	
				scheduledDay.setFreeTimeBlocksOfTheDay(freeBlocksOnACertainSchedulingDay.getFreeTimeBlocksOfTheDay());
					 
				scheduleDays.setScheduleDay(day, scheduledDay);				
			}
			
			System.out.println(scheduleDays);
	}
	

	public void initializeCourses(List<LecturePOJO> lecturesList) {
	 	 
		List<String> courses = new ArrayList<String>(new HashSet<String>(lecturesList.stream().map(lecture -> lecture.currentCourse()).collect(Collectors.toList())));
	 	
		List<String> semesters = new ArrayList<String>(new HashSet<String>(lecturesList.stream().map(lecture -> lecture.currentSemester()).collect(Collectors.toList())));
		
		 
		this.courseLecturesInFirstSemester =  lecturesList.stream().filter(lecture ->  
														 lecture.currentCourse().equals(courses.get(0)) && lecture.currentSemester().equals(semesters.get(0)) )
															    .collect(Collectors.toList())
															    	.stream()
															    	.filter(lecture -> lecture.getisPractice() == false)
															    	.map(lecture -> {
															    		 
															    	return	new ScheduleLectureTimingPOJO(lecture.getLectureName(), 
																						    			lecture.getLectureId(),
													    											lecture.getDay(), 
													    										lecture.getStartTime(), 
													    									lecture.getEndTime());})
																						    	
															    	.collect(Collectors.toList());
		
		
		
		this.coursePracticesInFirstSemester =  lecturesList.stream().filter(lecture ->  
														 lecture.currentCourse().equals(courses.get(0)) && lecture.currentSemester().equals(semesters.get(0)) )
															    .collect(Collectors.toList())
															    	.stream()
															    	.filter(lecture -> lecture.getisPractice() == true)
															    	.filter(UniqueFilter.distinctByKey(lecture ->   (lecturesList.stream().filter(subLecture -> subLecture.getLectureName().equals(lecture.getLectureName())).collect(Collectors.toList())).size() == 1)
															    							)
															    	.map(lecture -> {
															    		 
															    	return	new ScheduleLectureTimingPOJO(lecture.getLectureName(), 
															    										lecture.getLectureId(),
															    									lecture.getDay(), 
															    								lecture.getStartTime(), 
																							lecture.getEndTime());})
															    	
															    	.collect(Collectors.toList());
  
		
		
		
		this.courseLecturesInSecondSemester =  lecturesList.stream().filter(lecture -> lecture.currentCourse().equals(courses.get(0)) && lecture.currentSemester().equals(semesters.get(2))
	
			
															
														)
															    .collect(Collectors.toList())
															    	.stream()
															    	.filter(lecture -> lecture.getisPractice() == false)
															    	.map(lecture -> {
															    		 
															    	return	new ScheduleLectureTimingPOJO(lecture.getLectureName(), 
																						    			lecture.getLectureId(),
													    											lecture.getDay(), 
													    										lecture.getStartTime(), 
													    									lecture.getEndTime());})
																						    	
															    	.collect(Collectors.toList());



		this.coursePracticesInSecondSemester =  lecturesList.stream().filter(lecture ->  
														 lecture.currentCourse().equals(courses.get(0)) && lecture.currentSemester().equals(semesters.get(2)) )
															    .collect(Collectors.toList())
															    	.stream()
															    	.filter(lecture -> lecture.getisPractice() == true)
															    	.filter(UniqueFilter.distinctByKey(lecture ->   (lecturesList.stream().filter(subLecture -> subLecture.getLectureName().equals(lecture.getLectureName())).collect(Collectors.toList())).size() == 1)
															    							)
															    	.map(lecture -> {
															    		 
															    	return	new ScheduleLectureTimingPOJO(lecture.getLectureName(), 
															    										lecture.getLectureId(),
															    									lecture.getDay(), 
															    								lecture.getStartTime(), 
																							lecture.getEndTime());})
															    	
															    	.collect(Collectors.toList());
														
			 
		 
		try {
			
			this.paralledCourseLecturesInFirstSemester =  lecturesList.stream().filter(lecture ->  
																	 lecture.currentCourse().equals(courses.get(1)) && lecture.currentSemester().equals(semesters.get(0)) )
																		    .collect(Collectors.toList())
																		    	.stream()
																		    	.filter(lecture -> lecture.getisPractice() == false)
																		    	.map(lecture -> new ScheduleLectureTimingPOJO(lecture.getLectureName(),
																		    												lecture.getLectureId(),
																		    											lecture.getDay(), 
																		    										lecture.getStartTime(), 
																		    									lecture.getEndTime()))
																	
																		    	.collect(Collectors.toList());


			

			this.paralledCoursePracticesInFirstSemester =  lecturesList.stream().filter(lecture ->  
													
																										lecture.currentCourse().equals(courses.get(1)) && lecture.currentSemester().equals(semesters.get(0)) )
																										    .collect(Collectors.toList())
																										    	.stream()
																										    	.filter(lecture -> lecture.getisPractice() == true)
																										    	.filter(UniqueFilter.distinctByKey(lecture ->   (lecturesList.stream().filter(subLecture -> subLecture.getLectureName().equals(lecture.getLectureName())).collect(Collectors.toList())).size() == 1)
																										    							)
																										    	.map(lecture -> {
																										    		 
																										    	return	new ScheduleLectureTimingPOJO(lecture.getLectureName(), 
																										    									lecture.getLectureId(),
																										    								lecture.getDay(), 
																										    							lecture.getStartTime(), 
																										    						lecture.getEndTime());})
																										    	
																										    	.collect(Collectors.toList());
											  
			
			
             this.paralledCourseLecturesInSecondSemester =  lecturesList.stream().filter(lecture ->  
																	 lecture.currentCourse().equals(courses.get(1)) && lecture.currentSemester().equals(semesters.get(2)) )
																		    .collect(Collectors.toList())
																		    	.stream()
																		    	.filter(lecture -> lecture.getisPractice() == false)
																		    	.map(lecture -> new ScheduleLectureTimingPOJO(lecture.getLectureName(), 
																		    											lecture.getLectureId(),
																		    										lecture.getDay(), 
																		    									lecture.getStartTime(), 
																		    								lecture.getEndTime()))
																		    	
																		    	.collect(Collectors.toList());

             this.paralledCoursePracticesInSecondSemester =  lecturesList.stream().filter(lecture ->  
				
																							lecture.currentCourse().equals(courses.get(1)) && lecture.currentSemester().equals(semesters.get(2)) )
																							    .collect(Collectors.toList())
																							    	.stream()
																							    	.filter(lecture -> lecture.getisPractice() == true)
																							    	.filter(UniqueFilter.distinctByKey(lecture ->   (lecturesList.stream().filter(subLecture -> subLecture.getLectureName().equals(lecture.getLectureName())).collect(Collectors.toList())).size() == 1)
																							    							)
																							    	.map(lecture -> {
																							    		 
																							    	return	new ScheduleLectureTimingPOJO(lecture.getLectureName(), 
																							    									lecture.getLectureId(),
																							    								lecture.getDay(), 
																							    							lecture.getStartTime(), 
																							    						lecture.getEndTime());})
																							    	.collect(Collectors.toList());
              

		}
		// if the parallel lecture was not defined ... 
		catch(IndexOutOfBoundsException noParallelLectures) {
			
			System.out.println();
			
			
			noParallelLectures.printStackTrace();
			
			  this.paralledCourseLecturesInFirstSemester  =  new ArrayList<>();

			  this.paralledCoursePracticesInFirstSemester  =  new ArrayList<>();

			  this.paralledCourseLecturesInSecondSemester  =  new ArrayList<>(); 
			  
			  this.paralledCoursePracticesInSecondSemester  =  new ArrayList<>();
		}
		 
	}  
	 
	
	public void checkConflicts(List<ScheduleLectureTimingPOJO> lecturesInChoosenCourseAndSemester, 
			 List<ScheduleLectureTimingPOJO>  praticesInChoosenCourseAndSemester ){ 


		scheduleDays =  new SchedulingWeek(GeneralPurpose.ArrayToList(Constants.Days.values())
					.stream()
					.map(scheduleDay -> new SchedulingDay(scheduleDay))
					.collect(Collectors.toList())); 
	       
		findConflicts( lecturesInChoosenCourseAndSemester,  praticesInChoosenCourseAndSemester );
		
		if(conflictsThere()) {
			
			permutateLectures();
			// conflicts still there ? 
			if(conflictsThere()) {
	
				//@FIXME: Error Message 			
				
				//m_ConflictsManager.overrideLectures();
			} 
		} 
	}
	
	
	
	/**x
	 * @return 
	 * @return (List<ScheduleLectureTimeBlock>) list of TimeBlocks , that led to a collision
	 * */
	
	private void findConflicts(List<ScheduleLectureTimingPOJO> lecturesInChoosenCourseAndSemester, 
			 List<ScheduleLectureTimingPOJO>  praticesInChoosenCourseAndSemester ){
		  
		
		 List<ScheduleLectureTimingPOJO> paralledEventsInFirstSemester = Stream.concat(paralledCourseLecturesInFirstSemester.stream(), paralledCoursePracticesInFirstSemester.stream()).collect(Collectors.toList());
		
		 List<ScheduleLectureTimingPOJO> paralledEventsInSecondSemester = Stream.concat(paralledCourseLecturesInSecondSemester.stream(), paralledCoursePracticesInSecondSemester.stream()).collect(Collectors.toList());
		
		
	 	 List<ScheduleLectureTimingPOJO> paralledEvents = Stream.concat(paralledEventsInFirstSemester.stream(), paralledEventsInSecondSemester.stream()).collect(Collectors.toList());
		
		
		 // test purposes we do just check the first tripel 
		
		 /// ScheduledLectures scheduledLectures = Arrays.asList(scheduledCourseSemester.buildScheduledLectures(),  paralledlCourseFirstSemester.buildScheduledLectures(),  paralledlCourseSecondSemester.buildScheduledLectures());
		  
		 // list of lectures between a certan semester and its paralle counterpart
		  
	     collidedLectures = new ArrayList<ScheduleLectureTimeBlock>();
		  
	     
	     List<ScheduleLectureTimingPOJO> lecturesList = new ArrayList<ScheduleLectureTimingPOJO>(new HashSet<ScheduleLectureTimingPOJO>(Stream.concat(Stream.concat(lecturesInChoosenCourseAndSemester.stream(), praticesInChoosenCourseAndSemester.stream())
															               .collect(Collectors.toList()).stream(), paralledEvents.stream())
															               .collect(Collectors.toList())));
	     
		
	     for( ScheduleLectureTimingPOJO singleLecture:  lecturesList ) {
			 
			 
			 	 ScheduleLectureTimeBlock block = new ScheduleLectureTimeBlock( singleLecture);
				  
				 try {
					    
					 	 System.out.println("Day " + block.getLectureDay());
						 scheduleDays.getSchedulingDay(block.getLectureDay()).occupy(block);
				  
				 }
				 catch(CollisionError lectureCollides) {
			
					 System.out.println("Collision with " + block);
					 collidedLectures.add(block);
				 }
		 }
		  
		System.out.println(scheduleDays);  
		System.out.println(collidedLectures); 
		 
	}
	 
	public  List<ScheduleLectureTimeBlock>  getCollidedLectures(){
		
		return collidedLectures;
	}

	public boolean conflictsThere() {
		
		return collidedLectures.size() == 0 ? false : true; 
	}

 
	public SchedulingWeek getScheduleDays() {
		
		return scheduleDays;
	}


}
