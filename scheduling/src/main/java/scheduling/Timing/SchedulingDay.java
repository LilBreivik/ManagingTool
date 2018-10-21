package scheduling.Timing;
 
import java.util.ArrayList;
import java.util.List;

import resources.components.elements.POJO.Lecture.Timing.TimeBlock;
import resources.error.scheduling.CollisionError;
import resources.utils.general.Constants.Days;
import scheduling.POJO.DaysTimeBlocks; 

/**
 * This class shall handle 
 * 
 * the timeblocks of a certain day in a week
 * 
 * */

public class SchedulingDay {

	// Day of the schedulingDay 
	
	protected Days m_scheduleDay; 
 
	// List of timeBlocks of a certain Day 
	
	protected ArrayList<TimeBlock> m_occupiedTimeBlocksOfTheDay;
	
	
	protected DaysTimeBlocks  m_scheduledtimeBlocksOfTheDay;

	protected DaysTimeBlocks  m_freeTimeBlocksOfTheDay;
	 
	
	
	public  SchedulingDay(Days scheduleDay)
	{
		m_scheduleDay = scheduleDay; 
		m_occupiedTimeBlocksOfTheDay = new ArrayList<TimeBlock> ();
		m_scheduledtimeBlocksOfTheDay =  new DaysTimeBlocks(true); 
		m_freeTimeBlocksOfTheDay = new DaysTimeBlocks(true); 
	}
	
	public void setFreeTimeBlocksOfTheDay(DaysTimeBlocks  freeTimeBlocksOfTheDay){
		
		m_freeTimeBlocksOfTheDay = freeTimeBlocksOfTheDay;
	}
	
	public DaysTimeBlocks  getFreeTimeBlocksOfTheDay(){
		
		return m_freeTimeBlocksOfTheDay;
	}
	
	 
    public List<TimeBlock>  getOccupiedTimeBlocksOfTheDay(){
		
		return  m_occupiedTimeBlocksOfTheDay;
	}
	
    public DaysTimeBlocks  getScheduledtimeBlocksOfTheDay(){
		
		return  m_scheduledtimeBlocksOfTheDay;
	}
	
	
	public void occupy(TimeBlock block) throws CollisionError{
		 
		if(m_scheduledtimeBlocksOfTheDay.add(block)) {
			 
			m_occupiedTimeBlocksOfTheDay .add(block); 
			
			m_freeTimeBlocksOfTheDay =   m_scheduledtimeBlocksOfTheDay.removeTimeBlocks(m_occupiedTimeBlocksOfTheDay);
			
			 
		}
		else {
			
			m_freeTimeBlocksOfTheDay =   m_scheduledtimeBlocksOfTheDay.removeTimeBlocks(m_occupiedTimeBlocksOfTheDay);
			System.out.println(m_freeTimeBlocksOfTheDay);
			throw new CollisionError("Collision detected " + block);
		}
	}

	public Days getM_scheduleDay() {
		return m_scheduleDay;
	}

	public void setM_scheduleDay(Days m_scheduleDay) {
		this.m_scheduleDay = m_scheduleDay;
	}
	
}
