package scheduling.timing;

import resources.utils.general.Constants.Days;

public class ScheduleLectureTimeBlock 
										extends TimeBlock{

	private ScheduleLectureTimingPOJO m_pojo; 
	
	public ScheduleLectureTimeBlock(ScheduleLectureTimingPOJO lectureTimingPOJO) {
		super(lectureTimingPOJO.getStartTime(), lectureTimingPOJO.getEndTime());
		
		nameOfBlock = lectureTimingPOJO.getLectureName();
		idOfBlock = lectureTimingPOJO.getLectureID();
		m_pojo = lectureTimingPOJO; 
	}

	public Days getLectureDay() {
		
		return  Days.valueOf(m_pojo.getLectureDay());
	}
	 
	
}
