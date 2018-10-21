package resources.components.elements.POJO.Schedule;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * This class shall 
 * handle the representation 
 * of time 
 * 
 * 
 * */

public class SchedulingTimeUnit  {
  
	// variable that holds the converted scheduleTime 
    private DateTime m_scheduleTime;
	
	private final String TIMEFORMAT = "HH:mm";
    
	/**
	 * @param schedueTime (String) , time to convert, 
	 * needs to be in this Format : 'HH:mm'
	 * 
	 * */
	
	public SchedulingTimeUnit(String scheduleTime) 
	{	  
		 m_scheduleTime = DateTime.parse(scheduleTime, DateTimeFormat.forPattern(TIMEFORMAT));
	}
	
	@Override
	public String toString() {
		
		return m_scheduleTime.toString(TIMEFORMAT);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		SchedulingTimeUnit unit = (SchedulingTimeUnit) obj; 
		
		return this.toString().equals(unit.toString());
		
	}
	
	public DateTime getScheduleTime() {
		
		return m_scheduleTime;
	}
	
}
