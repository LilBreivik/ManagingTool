package scheduling.Correction;
 
import resources.components.elements.POJO.Lecture.LecturePOJO;
import resources.components.elements.POJO.Schedule.ScheduledLecturePOJO; 


public class CorrectionMessage  {

	final String lectureTOKEN = "LECTURE";
	final String dayTOKEN = "DAY";
	final String newDayTOKEN = "NEWDAY";
    final String starttimeTOKEN = "STARTTIME";
    final String newStarttimeTOKEN = "NEWSTARTTIME";
	final String endtimeTOKEN = "ENDTIME";
	final String newEndtimeTOKEN = "NEWENDTIME";
	
	final String beforeCorrection = "Die Veranstaltung " + lectureTOKEN 
								  + " wurde von " + dayTOKEN + " " + starttimeTOKEN + " : " + endtimeTOKEN;
	
	final String afterCorrection  = " auf " + newDayTOKEN + " " + newStarttimeTOKEN + " : " +  newEndtimeTOKEN + " verschoben";
	
	private StringBuilder m_correctionMessage;
	private LecturePOJO m_lecture;
	private ScheduledLecturePOJO m_scheduledLecture;
	
	public CorrectionMessage(LecturePOJO lecture, ScheduledLecturePOJO scheduledLecture) {
		
		m_lecture = lecture; 
		m_scheduledLecture = scheduledLecture;
		m_correctionMessage = new StringBuilder();
	}
	
	
	@Override
	public String toString() {
		
		m_correctionMessage.append(beforeCorrection.replace(lectureTOKEN, m_lecture.getLectureName())
												   .replace(dayTOKEN, m_lecture.getDay())
												   .replace(starttimeTOKEN, m_lecture.getStartTime())
												   .replace(endtimeTOKEN, m_lecture.getEndTime()));
											
		m_correctionMessage.append("\r\n");
		
		
		m_correctionMessage.append(afterCorrection.replace(newDayTOKEN, m_scheduledLecture.getDay())
												  .replace(newStarttimeTOKEN, m_scheduledLecture.getStartTime())
												  .replace(newEndtimeTOKEN, m_scheduledLecture.getEndTime()));
		
		return m_correctionMessage.toString();
	}
	
}
