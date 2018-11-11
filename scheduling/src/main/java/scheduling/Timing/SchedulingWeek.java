package scheduling.Timing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import resources.utils.general.Constants.Days;
import resources.components.elements.POJO.Lecture.Timing.TimeBlock;
import resources.components.elements.POJO.Schedule.ScheduledLecturePOJO;
import resources.utils.general.GeneralPurpose;

public class SchedulingWeek 
							extends ArrayList<SchedulingDay>{
 
	
    private Map<Days, List<TimeBlock>> m_scheduledTimeBlocks;
	
	public SchedulingWeek (List<SchedulingDay> scheduleDays) {
		
		 scheduleDays.stream().forEach(day -> {super.add(day);});
	}
	
	public SchedulingDay getSchedulingDay(Days day) {
		 
		int index = GeneralPurpose.ArrayToList(Days.values()).indexOf(day);
		
		return this.get(index);
	}

	public void setScheduleDay(Days day, SchedulingDay scheduledDay) {
		
        int index = GeneralPurpose.ArrayToList(Days.values()).indexOf(day);
		
        this.set(index, scheduledDay);
	}
	
	public  List<ScheduledLecturePOJO>  buildScheduledTimeBlocksOfTheWeek (){
		
		// scheduledTimeBlocks, that will be returned to the client
		m_scheduledTimeBlocks = new HashMap<Days, List<TimeBlock>>() {{
										put(Days.Montag, null);
										put(Days.Dienstag, null);
										put(Days.Mittwoch, null);
										put(Days.Donnerstag, null);
				     					put(Days.Freitag, null);
									}};
		
		
		
		for(Days day : Days.values()) {
			
			SchedulingDay scheduleDay = getSchedulingDay(day);
	  
			System.out.println(scheduleDay.getM_scheduleDay().toString());
			System.out.println(scheduleDay.getFreeTimeBlocksOfTheDay());
			System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay());
			System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay().size());
			// load the scheduled time blocks.. 
			m_scheduledTimeBlocks.put(scheduleDay.getM_scheduleDay(), scheduleDay.getOccupiedTimeBlocksOfTheDay());
		}
		
		
		return getScheduledTimeBlock();
	}
	
	

	private  List<ScheduledLecturePOJO> getScheduledTimeBlock(){
		
		List<ScheduledLecturePOJO> scheduledTimeBlocks = new ArrayList<>();
		 
		for(Days day : m_scheduledTimeBlocks.keySet()) {
			
			scheduledTimeBlocks.addAll(m_scheduledTimeBlocks.get(day)
											.stream()
								 			.map(block -> ScheduledLecturePOJO.createScheduleLecturePOJO(block, day))
											.collect(Collectors.toList()));
		}
		
		return scheduledTimeBlocks;
	}
}
