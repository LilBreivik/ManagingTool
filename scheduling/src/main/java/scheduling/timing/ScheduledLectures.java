package scheduling.timing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduledLectures
									extends ArrayList<ScheduleLectureTimingPOJO>{

	
	@Override
	public boolean add(ScheduleLectureTimingPOJO lectureTimingPOJO) {
		 
		if((this.indexOf(lectureTimingPOJO) == -1) ){
		 	
			return super.add(lectureTimingPOJO);
			
		}
		else {
			
			return false; 
		}
		 
	}
	 
	@Override
	public boolean addAll(Collection<? extends ScheduleLectureTimingPOJO> lectureTimingPOJOList) {
		
		boolean LISTCHANGED = false; 
		
		for(ScheduleLectureTimingPOJO lectureTimingPOJO : lectureTimingPOJOList) {
			
			 LISTCHANGED = add(lectureTimingPOJO);
		}
		
		return LISTCHANGED;
	}

	/*
	 * we need a way to provide, 
	 * a possibility to add new lectures, 
	 * 
	 * sortd by their name... the other add
	 * does check name with the time .... 
	 */
	
	public void addUniquly(ScheduleLectureTimingPOJO scheduleLectureTimingPOJO) {
		
		final String uniqueLectureName = scheduleLectureTimingPOJO.getLectureName();
		
		List<ScheduleLectureTimingPOJO> subListContainingTheName = this.stream().filter(lecture -> lecture.getLectureName().contains(uniqueLectureName))
																											.collect(Collectors.toList()); 
		 
		if(subListContainingTheName.size() == 0) {
			
			System.out.println("--> " + this.indexOf(scheduleLectureTimingPOJO));
			
			super.add(scheduleLectureTimingPOJO);
		}
		else {
			
			subListContainingTheName.stream().forEach(nameContainingPOJO -> this.remove(nameContainingPOJO));
			 
		}
		
	}
	 
}
