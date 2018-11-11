package scheduling;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Lecture.LecturePOJO;
import resources.components.elements.POJO.Lecture.Timing.TimeBlock;
import resources.components.elements.POJO.Schedule.ScheduledLecturePOJO;
import resources.utils.general.Constants.Days;
import scheduling.Correction.CorrectionMessage; 
import scheduling.POJO.ScheduledLecturesPOJO; 
import scheduling.Timing.ConflictsManager;
import scheduling.Timing.SchedulingDay;
import scheduling.Timing.SchedulingWeek; 

@Component
public class SchedulingCollisionManager {

	private ConflictsManager m_ConflictsManager; 

	private SchedulingWeek scheduleWeekForChooseCourseAndNextSemester;
	
	private SchedulingWeek scheduleWeekForChooseCourseAndSemester;
	 
	/*
	 * 
	 * 	 * one Course that was actually changed, and the other "parallel one"
	 * that needs to be monitored, if we do check the first one ...
	 * 
	 * */ 
	
	public void checkCollisons(List<LecturePOJO> lecturesList) {
		 
		
		
		m_ConflictsManager = new ConflictsManager();
		
		// at first we initalize lists , that contains the lectures, and practices 
		// of the certain courses and semesters 
		
		m_ConflictsManager.initializeCourses(lecturesList);
	 
		
		// current choosen course and semester 
		
		 
		m_ConflictsManager.checkConflicts(m_ConflictsManager.courseLecturesInFirstSemester, m_ConflictsManager.coursePracticesInFirstSemester);
		 
		scheduleWeekForChooseCourseAndSemester = m_ConflictsManager.getScheduleDays();
		
		
		m_ConflictsManager.checkConflicts(m_ConflictsManager.courseLecturesInSecondSemester, m_ConflictsManager.coursePracticesInSecondSemester);
		 
		
		scheduleWeekForChooseCourseAndNextSemester  = m_ConflictsManager.getScheduleDays();
	}

  
	
	private  List<String> createCorrectionMessage(List<LecturePOJO> lecturesList, 
															List<ScheduledLecturePOJO> scheduledTimeBlock){
		 
		List<CorrectionMessage> correctionMessageList = new ArrayList<CorrectionMessage>();
		
		for(LecturePOJO lecturePOJO: lecturesList) {
			
			if(scheduledTimeBlock.stream().filter(block -> 
			
						
						{ 
										return 	block.getLectureId()
										     .equals(lecturePOJO.getLectureId());
							
						}).collect(Collectors.toList()).size() == 1) 
			{
				
				ScheduledLecturePOJO scheduledLecturePOJO  = scheduledTimeBlock.stream(
																		).filter(block -> block.getLectureId().equals(lecturePOJO.getLectureId())).collect(Collectors.toList()).get(0);
				
				
				// we will just need the events that are rearanged ....
				
				if(!scheduledLecturePOJO.checkIfHappensAtTheSamePeriod(lecturePOJO)) {
					
					correctionMessageList .add(new CorrectionMessage(lecturePOJO, scheduledLecturePOJO));
				}		
			}
		}
	
	 	return correctionMessageList.stream().map(correction -> correction.toString()).collect(Collectors.toList());
	}


	public ScheduledLecturesPOJO buildCollisionCheckResponse(List<LecturePOJO> lecturesList) {
		
		List<String> correctionMessagesForChooseCourseAndSemester = createCorrectionMessage(lecturesList, scheduleWeekForChooseCourseAndSemester.buildScheduledTimeBlocksOfTheWeek());
		 
		List<String> correctionMessagesForChooseCourseAndNextSemester = createCorrectionMessage(lecturesList, scheduleWeekForChooseCourseAndNextSemester.buildScheduledTimeBlocksOfTheWeek());
		
		 
		return new ScheduledLecturesPOJO(Stream.concat(scheduleWeekForChooseCourseAndSemester.buildScheduledTimeBlocksOfTheWeek().stream(),
				 scheduleWeekForChooseCourseAndNextSemester.buildScheduledTimeBlocksOfTheWeek().stream()).collect(Collectors.toList()), 
				
				Stream.concat(correctionMessagesForChooseCourseAndSemester.stream(),
			    		correctionMessagesForChooseCourseAndNextSemester.stream()).collect(Collectors.toList()));
	} 
}
