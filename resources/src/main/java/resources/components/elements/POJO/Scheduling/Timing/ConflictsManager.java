package resources.components.elements.POJO.Scheduling.Timing;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.HashSet;
import java.util.List; 
import java.util.stream.Collectors;
import java.util.stream.Stream;  
import resources.components.elements.POJO.Scheduling.Lectures.LecturePOJO;
import resources.components.elements.POJO.Scheduling.Utils.UniqueFilter;
import resources.error.scheduling.CollisionError;
import resources.utils.general.Constants;
import resources.utils.general.GeneralPurpose;
import resources.utils.general.Constants.Days; 

public class ConflictsManager {
 
	private SchedulingWeek scheduleDays; 
	 
	
	private List<ScheduleLectureTimeBlock> collidedLectures; 
	 
    private List<ScheduleLectureTimingPOJO> scheduledCourseSemester;
	
	private List<ScheduleLectureTimingPOJO> paralledCourseFirstSemester;
	
	private List<ScheduleLectureTimingPOJO> paralledCourseSecondSemester;
	
	
	public ConflictsManager(){
		
		scheduleDays =  new SchedulingWeek(GeneralPurpose.ArrayToList(Constants.Days.values())
				.stream()
				.map(scheduleDay -> new SchedulingDay(scheduleDay))
				.collect(Collectors.toList())); 
		 
	}
	

	public void overrideLectures() {
		 
		for(int itr = 0; itr < collidedLectures.size(); itr += 1) {
			
			// [This TimeBlock null expands between 08:00 : 12:00, This TimeBlock null expands between 14:00 : 20:00]
			ScheduleLectureTimeBlock conflict = collidedLectures.get(itr);
			
			List<Days> days = GeneralPurpose.ArrayToList(Days.values());
			 
			Collections.shuffle( days);
					
			for(Days day : days ) {
				
				SchedulingDay scheduledDay = scheduleDays.get(GeneralPurpose.ArrayToList(Days.values()).indexOf(day));

				for(int timeBlockCtr = 0 ; timeBlockCtr < scheduledDay.getOccupiedTimeBlocksOfTheDay().size(); timeBlockCtr +=1) {
			
					TimeBlock block =  scheduledDay.getOccupiedTimeBlocksOfTheDay().get(timeBlockCtr);
			  
					if(block.doesBlockFit( conflict )) {
						
						if(!(scheduledCourseSemester.indexOf(block) > -1
									&&
									scheduledCourseSemester.indexOf(conflict)  > -1)
									||
									(paralledCourseFirstSemester.indexOf(block) > -1
											&&
											paralledCourseFirstSemester.indexOf(conflict)  > -1)
											||
											(paralledCourseSecondSemester.indexOf(block) > -1
													&&
													paralledCourseSecondSemester.indexOf(conflict)  > -1))
						{
						
							scheduledDay.getOccupiedTimeBlocksOfTheDay().add(conflict);
						}
						
					}
					
				} 
			}
			
		}
	}
	
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
		
		 
		this.scheduledCourseSemester =  lecturesList.stream().filter(lecture ->  
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
		
		
		
		List<ScheduleLectureTimingPOJO> scheduledPracticeCourseSemester =  lecturesList.stream().filter(lecture ->  
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
  
		
		this.scheduledCourseSemester.addAll(scheduledPracticeCourseSemester);
		
		 
		
		try {
			
			this.paralledCourseFirstSemester =  lecturesList.stream().filter(lecture ->  
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


			

			List<ScheduleLectureTimingPOJO> scheduledParalledCourseFirstSemester =  lecturesList.stream().filter(lecture ->  
													
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
											 
			
			 this.scheduledCourseSemester.addAll(scheduledParalledCourseFirstSemester);
			
			
			
             this.paralledCourseSecondSemester =  lecturesList.stream().filter(lecture ->  
																	 lecture.currentCourse().equals(courses.get(1)) && lecture.currentSemester().equals(semesters.get(1)) )
																		    .collect(Collectors.toList())
																		    	.stream()
																		    	.filter(lecture -> lecture.getisPractice() == false)
																		    	.map(lecture -> new ScheduleLectureTimingPOJO(lecture.getLectureName(), 
																		    											lecture.getLectureId(),
																		    										lecture.getDay(), 
																		    									lecture.getStartTime(), 
																		    								lecture.getEndTime()))
																		    	
																		    	.collect(Collectors.toList());

             List<ScheduleLectureTimingPOJO> scheduledParalledCourseSecondSemester =  lecturesList.stream().filter(lecture ->  
				
																							lecture.currentCourse().equals(courses.get(1)) && lecture.currentSemester().equals(semesters.get(1)) )
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
             
             this.scheduledCourseSemester.addAll(scheduledParalledCourseSecondSemester);
             

		}
		// if the parallel lecture was not defined ... 
		catch(IndexOutOfBoundsException noParallelLectures) {
			
			  this.paralledCourseFirstSemester =  new ArrayList<>();


              this.paralledCourseSecondSemester =  new ArrayList<>(); 
		}
		
		this.scheduledCourseSemester.forEach(lecture -> System.out.println(lecture.toString()));
		
		System.out.println("Stop");
		 
	}  
	
	/**
	 * @return (List<ScheduleLectureTimeBlock>) list of TimeBlocks , that led to a collision
	 * */
	
	public void findConflicts( ){
		  
		 // test purposes we do just check the first tripel 
		
		 /// ScheduledLectures scheduledLectures = Arrays.asList(scheduledCourseSemester.buildScheduledLectures(),  paralledlCourseFirstSemester.buildScheduledLectures(),  paralledlCourseSecondSemester.buildScheduledLectures());
		  
		 // list of lectures between a certan semester and its paralle counterpart
		  
	     collidedLectures = new ArrayList<ScheduleLectureTimeBlock>();
		  
	     
	     List<ScheduleLectureTimingPOJO> lecturesList = new ArrayList<ScheduleLectureTimingPOJO>(new HashSet<ScheduleLectureTimingPOJO>(Stream.concat(Stream.concat(scheduledCourseSemester.stream(), paralledCourseFirstSemester.stream())
															               .collect(Collectors.toList()).stream(), paralledCourseSecondSemester.stream())
															               .collect(Collectors.toList())));
	     
	      
	     System.out.println(scheduleDays);
	     
	     System.out.println("stop");
		
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
