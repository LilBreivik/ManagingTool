package scheduling.timing;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;  
import resources.utils.general.GeneralPurpose; 

public class DaysTimeBlocks 
								extends ArrayList<TimeBlock>{

	
	protected ArrayList<TimeBlock> p_smallestTimeBlocks;
	
	protected ArrayList<TimeBlock> p_biggestTimeBlocks;
	
	
	/**
	 * @param boolean shallBeInit a flag that detemrines if the 
	 * list shall be 
	 * */
	
	public DaysTimeBlocks (boolean shallBeInit) {
		
		if(shallBeInit) {
			super.add(new initSchedulingDay() );
		}
	} 
	
	// ArrayList<TimeBlock>
	
    public DaysTimeBlocks (ArrayList<TimeBlock> timeBlockArrayList) {
		
		for(TimeBlock block : timeBlockArrayList) {
			
			super.add(block);
		}
	} 
	
	
	 

	public DaysTimeBlocks(List<TimeBlock> timeBlockList) {
		
		for(TimeBlock block : timeBlockList) {
			
			super.add(block);
		}
	}

	@Override
	
	/**
	 * 
	 * @return true, only if the list got changed... 
	 * */
	
	public boolean add(TimeBlock blockToInsert){
		 
		boolean OVERLAPPING = false;
		
		List<TimeBlock> newList = new ArrayList<>();
		System.out.println("################"); 
		System.out.println(this);
		System.out.println("################");
		for(TimeBlock block : this) {
			
			/*
			 * If 2 time blocks overlap, there 
			 * will be three time bloks asterwards .. 
			 * 
			 * 
			 * 
			 *  Timeblock 1    Timeblock 2      Timeblocks after overlapping 
			 * 
			 *  XXXXXX									XXXXXX
			 *  XXXXXX									XXXXXX
			 *  XXXXXX         YYYYYY					YYYYYY
			 *  XXXXXX	       YYYYYY					YYYYYY
			 *  XXXXXX         YYYYYY					YYYYYY
			 *  XXXXXX         YYYYYY					YYYYYY
			 *  XXXXXX									XXXXXX
			 *  XXXXXX									XXXXXX
			 *  XXXXXX									XXXXXX
			 * 
			 * 
			 * */
			 
				System.out.println("block " + block + "  block to insert " + blockToInsert);
				
				if(block.getInterval().overlaps(blockToInsert.getInterval())  ) {
					
					TimeBlock blockBeforeOverlapping = null;
					
					TimeBlock blockAfterOverlapping = null;
					 //  && block.doesBlockFit(blockToInsert
					
					if(((block.getStartTime().equals(blockToInsert.getStartTime())) 
							||
							(block.getEndTime().equals(blockToInsert.getEndTime())))
							
							&&
							block.doesBlockFit(blockToInsert)
							)
					{
						
						
						if(block.getStartTime().equals(blockToInsert.getStartTime()) 
											&&
											!block.equals(blockToInsert)
								) {
							
							blockAfterOverlapping = new TimeBlock(blockToInsert.getEndTime(), 
									
									block.getEndTime());
							
							newList.addAll(Arrays.asList(blockAfterOverlapping  ));
							
							
						}
						else if(block.getEndTime().equals(blockToInsert.getEndTime())
											&&
											!block.equals(blockToInsert)
								) {
							
							blockAfterOverlapping = new TimeBlock(block.getStartTime(), 
									
									blockToInsert.getStartTime());
							
							newList.addAll(Arrays.asList(blockAfterOverlapping  ));
						}
						
						
						if(!block.isIDSet())
				    	{
				    		OVERLAPPING = newList.addAll(Arrays.asList(blockToInsert )); 
				    	} 
 
	  				
				} 
					
		        else {
						     
		        	
		        	if(block.getStartTime().getScheduleTime().isBefore(blockToInsert.getStartTime().getScheduleTime()) 
						    				&&  block.getEndTime().getScheduleTime().isAfter(blockToInsert.getEndTime().getScheduleTime())) {
						    	
						    	 blockBeforeOverlapping  = new TimeBlock(block.getStartTime(), (blockToInsert.getStartTime()));
								 
						    	  blockAfterOverlapping  = new TimeBlock(blockToInsert.getEndTime(), block.getEndTime());
								
								System.out.println(blockBeforeOverlapping );
								System.out.println(blockToInsert);
			  					System.out.println(blockAfterOverlapping );
								
								
									OVERLAPPING = newList.addAll(Arrays.asList(blockBeforeOverlapping ,
											blockToInsert ,  
												blockAfterOverlapping));  
							
									if( OVERLAPPING) {
										
										System.out.println();
									} 
									
						    }
						    else 
						    {
								newList.add(block);
						    }
						     
					} 
			}
			else {
				 
				newList.add(block);
			}
			 
		}
		
		super.clear();
		
		super.addAll(newList); 
		
		System.out.println(newList);
		return OVERLAPPING;
	}
	
	@Override
	public boolean addAll(Collection<? extends TimeBlock> timeBlocks) {
		 
		long min = Long.MAX_VALUE;
		
		long max = Long.MIN_VALUE;
		
		boolean addingFlag = false; 
		
        p_smallestTimeBlocks = new ArrayList<>();
		
		p_biggestTimeBlocks = new ArrayList<>();
		
		for(TimeBlock block : timeBlocks) 
		{ 
			if(block.getDuration().abs().getMillis() > max ) 
			{
				max = block.getDuration().abs().getMillis();
			}
			else if(block.getDuration().abs().getMillis() < min ) 
			{
				min = block.getDuration().abs().getMillis();
			}
			 
			addingFlag = super.add(block); 
		}
		
		for(TimeBlock block : this) 
		{ 
			if(block.getDuration().abs().getMillis() == max ) 
			{
				p_biggestTimeBlocks.add(block);
			}
			else if(block.getDuration().abs().getMillis() == min ) 
			{
				 p_smallestTimeBlocks.add(block);
			}
		}
		 
		return addingFlag;
	}
	 
	
	
	public List<TimeBlock> toTimeBlockList() {
	
		TimeBlock[] blocks = new TimeBlock[this.size()];
	
		return GeneralPurpose.ArrayToList(super.toArray(blocks));

	}
	
	
	public  DaysTimeBlocks removeTimeBlocks(ArrayList<TimeBlock> blocksToDelete) {
		
		ArrayList<TimeBlock>  currentDaysTimeBlocks =    this;
		
		ArrayList<TimeBlock>  removedTimeBlocks  = (ArrayList<TimeBlock>) GeneralPurpose.ListToCollection(currentDaysTimeBlocks)
																						  .stream()
																						  .filter(block -> blocksToDelete.indexOf(block) < 0)
																						  .collect(Collectors.toList());
		   
		return new DaysTimeBlocks(removedTimeBlocks); 
	}
	 

	class initSchedulingDay extends TimeBlock{

		public initSchedulingDay() {
			super("08:00", "20:00");
		}
	}
}
