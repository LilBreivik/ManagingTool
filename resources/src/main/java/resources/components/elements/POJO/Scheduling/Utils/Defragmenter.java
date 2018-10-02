package resources.components.elements.POJO.Scheduling.Utils;
 
import java.util.ArrayList;
import java.util.List;

import resources.components.elements.POJO.Scheduling.Timing.DaysTimeBlocks;
import resources.components.elements.POJO.Scheduling.Timing.TimeBlock; 

public class Defragmenter {

	private DaysTimeBlocks  m_defragmentedFreeTimeBlocks; 
	
	private ArrayList<TimeBlock>  m_occupiedTimeBlocksOfTheDay;

	
	public  void defragFreeTimesBlocks(DaysTimeBlocks  freetimeBlocksOfTheDay,
			List<TimeBlock>  occupiedTimeBlocksOfTheDay) {
	 
		List<TimeBlock> possiblyFragmentedTimeBlocks = freetimeBlocksOfTheDay.toTimeBlockList();
		
		
		for(TimeBlock occupiedTimeBlock : occupiedTimeBlocksOfTheDay) {
			
			
			for(int itr = 0; itr < possiblyFragmentedTimeBlocks.size() - 1; itr += 1 ) {
				
				System.out.println("itr " + possiblyFragmentedTimeBlocks.get(itr));
				
				System.out.println("itr++ " + possiblyFragmentedTimeBlocks.get(itr + 1));
				
				if(possiblyFragmentedTimeBlocks.get(itr).getEndTime().equals(occupiedTimeBlock .getStartTime())
						&& 
						possiblyFragmentedTimeBlocks.get(itr + 1).getStartTime().equals(occupiedTimeBlock .getEndTime()))
						
				{
				
					// we use the free block , after the insersted block , to get the needed duration ...
					TimeBlock blockToAdd = possiblyFragmentedTimeBlocks.get(itr + 1);	
					
					// the free block before the inserted block, will be increased .. 
					TimeBlock freeBlockToMove =  possiblyFragmentedTimeBlocks.get(itr);
	
					
					// we delete the fragmented free blocks.. 
					possiblyFragmentedTimeBlocks.remove(freeBlockToMove );
					possiblyFragmentedTimeBlocks.remove(blockToAdd);
					
					// we add the defragmented free block 
					
					TimeBlock newBlock = freeBlockToMove.addDuration(blockToAdd);  
					possiblyFragmentedTimeBlocks.add( newBlock);
					 
					
					 
					// we must change the start and end point of the occupied block manually, cause 
					// we use a for each loop as the outer loop 
					
					
					TimeBlock movedOccupiedBlock = occupiedTimeBlock.moveBlockForwared(blockToAdd);
					 
					occupiedTimeBlock.setStartTime(movedOccupiedBlock.getStartTime());
					occupiedTimeBlock.setEndTime(movedOccupiedBlock.getEndTime());
					 
					break;
				}
				 
			}
		}
		
		System.out.println("-----"); 
		System.out.println( possiblyFragmentedTimeBlocks);
		System.out.println( occupiedTimeBlocksOfTheDay);
		System.out.println("-----");
		
		m_defragmentedFreeTimeBlocks = new DaysTimeBlocks(possiblyFragmentedTimeBlocks);
		
		m_occupiedTimeBlocksOfTheDay =  (ArrayList<TimeBlock>) occupiedTimeBlocksOfTheDay;
	}

	
	
	public DaysTimeBlocks getDefragmentedFreeTimeBlocks() {
		return m_defragmentedFreeTimeBlocks;
	}

	public ArrayList<TimeBlock> getOccupiedTimeBlocksOfTheDay() {
		return m_occupiedTimeBlocksOfTheDay;
	}
 

	 
}
