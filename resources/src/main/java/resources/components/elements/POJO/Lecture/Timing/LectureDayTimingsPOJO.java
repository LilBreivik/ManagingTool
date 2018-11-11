package resources.components.elements.POJO.Lecture.Timing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import resources.utils.general.GeneralPurpose;
 

/**
 * This class defines a POJO, 
 * that contains a day, together with 
 * timings data ... 
 * */

public class LectureDayTimingsPOJO {

	private String day;

	private List<LectureTimingPOJO> vTimings; 
	
	 
	public List<LectureTimingPOJO> getvTimings() {
		return vTimings;
	}
	
	public void setvTimings(List<LectureTimingPOJO> vTimings) {
		this.vTimings = vTimings;
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	} 
	
	 
	
	public static List<LectureDayTimingsPOJO> createLectureDayTimingsPOJOList (String lectureString){
		
		List<String> daysWithTimes =  GeneralPurpose.ArrayToList(lectureString.split("§"));
		
		List<LectureDayTimingsPOJO> vTimnigs = new ArrayList<>();
		
		for(String dayWithTimings : daysWithTimes) {
			 
			 vTimnigs.add(createLectureDayTimingsPOJO(dayWithTimings));		
		}
		
		return vTimnigs;
	}
	
	/**
	 * Format: lectureString (String) Montag%(08:00-10:00)%(10:00-12:00)%(10:00-12:00)%(16:00-18:00)%(16:00-18:00)
	 * */
	
	public static LectureDayTimingsPOJO createLectureDayTimingsPOJO(String lectureString){
		
		
		    List<String>  dayWithTimings = GeneralPurpose.ArrayToList(lectureString.split("%"));
			 
			String day = dayWithTimings.get(0);
			
			List<LectureTimingPOJO> vTimings = dayWithTimings.subList(1, dayWithTimings.size())
																		.stream()
																		.map(timingString -> LectureTimingPOJO.createLectureTimingPOJOFromTimingString(timingString))
																		.collect(Collectors.toList());
				
			LectureDayTimingsPOJO pojo = new LectureDayTimingsPOJO();
			
			pojo.setDay(day);
			pojo.setvTimings(vTimings);
			
			return pojo;
	}
}
