package scheduling;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Scheduling.Lectures.LecturePOJO;
import resources.components.elements.POJO.Scheduling.Lectures.ScheduledLecturePOJO;
import resources.components.elements.POJO.Scheduling.Lectures.ScheduledLecturesPOJO;
import resources.components.elements.POJO.Scheduling.Timing.ConflictsManager;
import resources.components.elements.POJO.Scheduling.Timing.SchedulingDay;
import resources.components.elements.POJO.Scheduling.Timing.TimeBlock;
import resources.components.elements.corrections.CorrectionMessage;
import resources.utils.general.Constants.Days; 

@Component
public class SchedulingCollisionManager {

	private ConflictsManager m_ConflictsManager; 
		
	private Map<Days, List<TimeBlock>> m_scheduledTimeBlocks;
	 
	/*
	 * 
	 * 	 * one Course that was actually changed, and the other "parallel one"
	 * that needs to be monitored, if we do check the first one ...
	 * 
	 * */
	
	public void checkConflicts(List<LecturePOJO> lecturesList) {
		 
		// scheduledTimeBlocks, that will be returned to the client
		m_scheduledTimeBlocks = new HashMap<Days, List<TimeBlock>>() {{
											put(Days.Montag, null);
											put(Days.Dienstag, null);
			 								put(Days.Mittwoch, null);
											put(Days.Donnerstag, null);
											put(Days.Freitag, null);
									}};
		
		m_ConflictsManager = new ConflictsManager();
		
		m_ConflictsManager.initializeCourses(lecturesList);
		
		m_ConflictsManager.findConflicts( );
		
		if(m_ConflictsManager.conflictsThere()) {
			
			m_ConflictsManager.permutateLectures();
			// conflicts still there ? 
			if(m_ConflictsManager.conflictsThere()) {
				
				m_ConflictsManager.overrideLectures();
			}
			else {
				
			} 
		}
		else {
			
			
		} 
	  
		
		for(Days day : Days.values()) {
			
			SchedulingDay scheduleDay = m_ConflictsManager.getScheduleDays().getSchedulingDay(day);
	  
			System.out.println(scheduleDay.getM_scheduleDay().toString());
			System.out.println(scheduleDay.getFreeTimeBlocksOfTheDay());
			System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay());
			System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay().size());
			// load the scheduled time blocks.. 
			m_scheduledTimeBlocks.put(scheduleDay.getM_scheduleDay(), scheduleDay.getOccupiedTimeBlocksOfTheDay());
		}
		
	 
		
		System.out.println(m_ConflictsManager.getScheduleDays());
		System.out.println(""); 
	}

 
	public List<ScheduledLecturePOJO> getScheduledTimeBlock(){
		
		List<ScheduledLecturePOJO> scheduledTimeBlocks = new ArrayList<>();
		 
		for(Days day : m_scheduledTimeBlocks.keySet()) {
			
			scheduledTimeBlocks.addAll(m_scheduledTimeBlocks.get(day)
											.stream()
								 			.map(block -> ScheduledLecturePOJO.createScheduleLecturePOJO(block, day))
											.collect(Collectors.toList()));
		}
		
		return scheduledTimeBlocks;
	}
	
	private  List<String> createSchedulingMessage(List<LecturePOJO> lecturesList, 
															List<ScheduledLecturePOJO> scheduledTimeBlock){
		 
		List<CorrectionMessage> correctionMessageList = new ArrayList<CorrectionMessage>();
		
		for(LecturePOJO lecturePOJO: lecturesList) {
			
			if(scheduledTimeBlock.stream().filter(block -> block.getLectureId()
							     .equals(lecturePOJO.getLectureId()))
										  .collect(Collectors.toList()).size() == 1) 
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


	public ScheduledLecturesPOJO buildCollisionCheckResponse(List<ScheduledLecturePOJO> scheduledTimeBlock,
			List<LecturePOJO> lecturesList) {
		
		return new ScheduledLecturesPOJO(scheduledTimeBlock, createSchedulingMessage(lecturesList, scheduledTimeBlock));
	} 
}
