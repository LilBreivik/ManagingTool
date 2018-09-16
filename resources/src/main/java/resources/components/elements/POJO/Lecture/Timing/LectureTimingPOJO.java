package resources.components.elements.POJO.Lecture.Timing;

/**
 * This Class defines the POJO, 
 * that is need to store the start 
 * and endtime of a single event 
 * 
 * */

public class LectureTimingPOJO {
 
	protected String startTime; 
	
	protected String endTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Format: (12:00-14:00)
	 * */
	
	public static LectureTimingPOJO createLectureTimingPOJOFromTimingString(String timingString) {
		
		// timingStringWithoutColumn  Format: 12:00-14:00
		
		String timingStringWithoutColumn =  timingString.replace("(", ")").replace(")", "");
		 
		/*
		 * timing[0] = startzeit 
		 * 
		 * timing[1] = endzeit 
		 * */
		
		String[] timings = timingStringWithoutColumn.split("-");
		
		LectureTimingPOJO pojo = new LectureTimingPOJO();
		
		pojo.setStartTime(timings[0]);
		
		pojo.setEndTime(timings[1]);
		
		
		return pojo;
	}
	 
}
