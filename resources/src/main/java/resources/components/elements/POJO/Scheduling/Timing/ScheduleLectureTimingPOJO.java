package resources.components.elements.POJO.Scheduling.Timing;

import resources.components.elements.POJO.Lecture.Timing.LectureTimingPOJO;

/**
 * Class that shall 
 * contain the needed information
 * 
 * for handling the scheduling of lectures
 * 
 * */

public class ScheduleLectureTimingPOJO 
						
									extends LectureTimingPOJO {

	
	private String m_lectureName; 
	
	private String m_lectureDay; 
	
	private String m_lectureID; 
	
	 
	public ScheduleLectureTimingPOJO(String lectureName,
										String lectureID, 
											String Day, 
												String startTime, 
													String endTime) {
	 
		m_lectureID = lectureID;
		m_lectureName = lectureName;
		m_lectureDay = Day; 
		
		super.startTime = startTime;
		super.endTime = endTime;
	}

	@Override
	public String toString() {
		
		StringBuilder valueOfScheduleLectureTimingPOJO = new StringBuilder();
		
		valueOfScheduleLectureTimingPOJO.append(m_lectureID);
		valueOfScheduleLectureTimingPOJO.append(m_lectureName);
		valueOfScheduleLectureTimingPOJO.append(m_lectureDay);
		valueOfScheduleLectureTimingPOJO.append(super.startTime);
		valueOfScheduleLectureTimingPOJO.append(super.endTime);
		
		return valueOfScheduleLectureTimingPOJO.toString();	
	}
	
	@Override
	public boolean equals(Object o) {
		
		ScheduleLectureTimingPOJO tmpScheduleLectureTimingPOJO = (ScheduleLectureTimingPOJO) o; 
		
	 
		return toString().equals(tmpScheduleLectureTimingPOJO.toString());
	}
	
    public boolean equalName(ScheduleLectureTimingPOJO tmpScheduleLectureTimingPOJO ) {
		 
	 
		return m_lectureName.equals(tmpScheduleLectureTimingPOJO.getLectureName());
	}
	
	
	public String getLectureName() {
		return m_lectureName;
	}

	
	public String getLectureDay() {
		return m_lectureDay;
	}
 
	
	public String getLectureID() {
		return m_lectureID;
	}
 
}
