package scheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.ListUtils;

import resources.error.scheduling.CollisionError;
import resources.utils.general.Constants.Days;
import scheduling.timing.DaysTimeBlocks;
import scheduling.timing.Defragmenter;
import scheduling.timing.TimeBlock;

public class FreeBlocksSchedulingDay 
										extends  SchedulingDay {
	
	private Defragmenter m_defragmenter; 
	
	public FreeBlocksSchedulingDay(Days scheduleDay, 
			DaysTimeBlocks  freeBlocksOfACertainScheduledDay ) {
		super(scheduleDay); 
		
		// we initalioze the FreeBlocksSchedulingDay with precalculated list of free blocks 
		
		m_defragmenter = new Defragmenter();
		
		m_scheduledtimeBlocksOfTheDay =  freeBlocksOfACertainScheduledDay; 
		
		m_freeTimeBlocksOfTheDay = m_scheduledtimeBlocksOfTheDay;
	}
	
	@Override 
	public void occupy(TimeBlock block) throws CollisionError{
		 
		if(m_scheduledtimeBlocksOfTheDay.add(block)) {
			
			System.out.println(m_occupiedTimeBlocksOfTheDay);
			m_occupiedTimeBlocksOfTheDay .add(block); 
			System.out.println(m_occupiedTimeBlocksOfTheDay);
			
			
			m_defragmenter.defragFreeTimesBlocks(m_scheduledtimeBlocksOfTheDay.removeTimeBlocks(m_occupiedTimeBlocksOfTheDay),
																												m_occupiedTimeBlocksOfTheDay);		
		 
			
			
			m_freeTimeBlocksOfTheDay = m_defragmenter.getDefragmentedFreeTimeBlocks();
			
			m_occupiedTimeBlocksOfTheDay = m_defragmenter.getOccupiedTimeBlocksOfTheDay();
			
			
			
			m_scheduledtimeBlocksOfTheDay.clear();
			
			m_scheduledtimeBlocksOfTheDay.addAll(m_defragmenter.getDefragmentedFreeTimeBlocks());
			
			m_scheduledtimeBlocksOfTheDay.addAll(m_defragmenter.getOccupiedTimeBlocksOfTheDay());
			 
			
			System.out.println("---> " + m_freeTimeBlocksOfTheDay);
			 
			System.out.println();
		}
		else {
			 
				boolean CONFLICTSOLVED = false; 
			
			
				for(int itr = 0 ; itr < m_freeTimeBlocksOfTheDay.size(); itr += 1) 
				{
					TimeBlock freeBlock =  m_freeTimeBlocksOfTheDay.get(itr);
					
					if(freeBlock .doesBlockFit(block)) {
						 
						
						if(freeBlock.getDuration().getMillis() == block.getDuration().getMillis())
						{ 

							block.placeBlock(freeBlock);
							 
							m_occupiedTimeBlocksOfTheDay .add(block); 
							
							m_freeTimeBlocksOfTheDay.remove(itr);
						}
						else {
							
							TimeBlock rest = freeBlock.divide(block);
							
							m_occupiedTimeBlocksOfTheDay .add(rest); 
							
							m_freeTimeBlocksOfTheDay.remove(itr);
							
							m_freeTimeBlocksOfTheDay.addAll(Arrays.asList(freeBlock));
							
							System.out.println("##");
						}
						  
						CONFLICTSOLVED  = true; 
						break;
					}
				}
				 
				m_freeTimeBlocksOfTheDay = m_freeTimeBlocksOfTheDay.removeTimeBlocks(m_occupiedTimeBlocksOfTheDay);
				
				m_scheduledtimeBlocksOfTheDay.clear();
				
				m_scheduledtimeBlocksOfTheDay.addAll(m_freeTimeBlocksOfTheDay);
				
				m_scheduledtimeBlocksOfTheDay.addAll(m_occupiedTimeBlocksOfTheDay);
		
				
				System.out.println(m_freeTimeBlocksOfTheDay);
				System.out.println(m_occupiedTimeBlocksOfTheDay);
				System.out.println("XXX");
				System.out.println(m_scheduledtimeBlocksOfTheDay);
				System.out.println("XXX");
				
				if(!CONFLICTSOLVED ) {
					
					throw new CollisionError("Collision detected " + block);
				}
		//	throw new CollisionError("Collision detected " + block);
		}
	}

	
	
	 

}
