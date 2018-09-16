package scheduling.elements.POJO;

import resources.utils.general.Constants.Days;
import scheduling.timing.TimeBlock;

public class ScheduledLecturePOJO {
 
	protected String lectureId;
	
	protected String day;
	
	protected String lectureName;
	
	protected String startTime;
	
	protected String endTime;

	public boolean checkIfHappensAtTheSamePeriod(ScheduledLecturePOJO pojo) {
	
		return ((startTime.equals(pojo.getStartTime())) &&  (endTime.equals(pojo.getEndTime())));	
	}
		
	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

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

	
	public static ScheduledLecturePOJO createScheduleLecturePOJO(TimeBlock block, Days day)
	{
		ScheduledLecturePOJO pojo = new ScheduledLecturePOJO();
		
		pojo.setLectureId(block.getIDOfBlock());
		pojo.setLectureName(block.getNameOfBlock());
		pojo.setStartTime(block.getStartTimeAsString());
		pojo.setEndTime(block.getEndTimeAsString());
		pojo.setDay(day.toString());
		
		return pojo;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
 
}
