package scheduling.test.timing;
 
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import static  resources.utils.general.Constants.Days.Montag;
import static  resources.utils.general.Constants.Days.Dienstag;
import static  resources.utils.general.Constants.Days.Mittwoch;
import static  resources.utils.general.Constants.Days.Donnerstag;
import static  resources.utils.general.Constants.Days.Freitag;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import com.google.common.collect.Collections2;

import resources.components.elements.POJO.Lecture.Timing.TimeBlock;
import resources.utils.general.Constants.Days; 
import scheduling.Timing.SchedulingDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScheduleDayTests {

	 /*private static SchedulingDay scheduleDay; 
	
	private static TimeBlock timeBlock; 
	
	@BeforeClass
	public  static void initializeScheduleDay() {
		
		scheduleDay = new SchedulingDay(Montag);
	}*/
	
	@Rule
	public TestName testName = new TestName();
 
	
	@Test
	public void TestA_SubTest_A_checkIfWeCanCalculateFreeAndOccupiedTimeBlocks() {
		
		SchedulingDay scheduleDay = new SchedulingDay(Montag);
		
		TimeBlock timeBlock = new TimeBlock("12:00", "14:00"); 
	
		/**
		 * Timeblock 12:00 - 14:00
		 * */
		
		scheduleDay.occupy(timeBlock);
	 
		/**
		 * Expected: 
		 * 
		 * occupied: 
		 * 
		 * 12:00 - 14:00
		 */
		
		/* free: 
		 * 
		 * 8:00 - 12:00 ; 14:00 - 20:00 
		 * 
		 * */
		
		TimeBlock freeTimeBlock1 = new TimeBlock("08:00", "12:00");
		
		TimeBlock freeTimeBlock2 = new TimeBlock("14:00", "20:00");
		
		TimeBlock occupiedTimeBlock = (TimeBlock) scheduleDay.getOccupiedTimeBlocksOfTheDay().get(0);
		
		assertThat("freeTimeBlock 1 does not match the expected one ", not(scheduleDay.getFreeTimeBlocksOfTheDay().get(0).equals(freeTimeBlock1)));
		
		assertThat("freeTimeBlock 2 does not match the expected one ", not(scheduleDay.getFreeTimeBlocksOfTheDay().get(1).equals(freeTimeBlock2)));
		
		assertThat("occupiedTimeBlock does not match the expected one ", not(timeBlock.equals(freeTimeBlock2)));
		
	}
	 
	@Test
	public void TestB_SubTest_A_checkIfWeCanCalculateFreeAndOccupiedTimeBlocks(){
		
		TimeBlock startTimeBlock = new TimeBlock("07:30", "08:00"); 
		
		TimeBlock durationBlock = new TimeBlock("08:00", "08:30"); 
		
		List<TimeBlock> timeBlocks = new ArrayList<TimeBlock>();
		
		List<TimeBlock> freeTimeBlocks = new ArrayList<TimeBlock>();
		
		TimeBlock tempBlock = startTimeBlock; 
		
		TimeBlock freeTimeBlock = null; 
		
		for(int itr = 0; itr < 6; itr += 1) {
			 
			 tempBlock =  tempBlock.moveBlockForwared(durationBlock).moveBlockForwared(durationBlock);
			
			 freeTimeBlock = tempBlock.moveBlockBackward(durationBlock);
			 
			 timeBlocks.add(tempBlock); 
			 
			 freeTimeBlocks.add(freeTimeBlock);
		} 
		
	    Collection<List<TimeBlock>> allPossibleTimeBlocks = Collections2.permutations(timeBlocks);
		
		
		for(Days day : Arrays.asList(Montag, Dienstag, Mittwoch, Donnerstag, Freitag )) {
			
			for(List<TimeBlock> timeBlockList : allPossibleTimeBlocks) {
				
				SchedulingDay scheduleDay = new SchedulingDay(day);
				
				timeBlockList.stream().forEach(block ->  scheduleDay.occupy(block) );
				
				freeTimeBlocks.stream().forEach(block -> {
					
				 
					scheduleDay.getFreeTimeBlocksOfTheDay().get( scheduleDay.getFreeTimeBlocksOfTheDay().indexOf(block)).equals(block);
					
				}     );
			
				System.out.println();
			}
		}
		
		 
	}
	
	@Test
	public void TestA_SubTest_B_checkIfWeCanCalculateFreeAndOccupiedTimeBlocks() {
		 
		
		 	
		TimeBlock timeBlock1 = new TimeBlock("08:00", "08:45"); 
		 
		TimeBlock timeBlock2 = new TimeBlock("10:30", "12:35"); 
		 
		TimeBlock timeBlock3 = new TimeBlock("13:00", "14:00"); 
		 
		TimeBlock timeBlock4 = new TimeBlock("17:45", "19:00"); 
	 
	
		List<TimeBlock> timeBlocks = Arrays.asList(timeBlock1, timeBlock2, 
													timeBlock3, timeBlock4);
		
		TimeBlock freeTimeBlock1 = new TimeBlock("08:45" , "10:30");
		
		TimeBlock freeTimeBlock2 = new TimeBlock("12:35" , "13:00");
		
		TimeBlock freeTimeBlock3 = new TimeBlock("14:00" , "17:45");
		
		TimeBlock freeTimeBlock4 = new TimeBlock("19:00" , "20:00");
		 
		
		Collection<List<TimeBlock>> allPossibleTimeBlocks = Collections2.permutations(timeBlocks);
		
		
		for(Days day : Arrays.asList(Montag, Dienstag, Mittwoch, Donnerstag, Freitag )) {
			
			for(List<TimeBlock> timeBlockList : allPossibleTimeBlocks) {
				
				SchedulingDay scheduleDay = new SchedulingDay(day);
				
				timeBlockList.stream().forEach(block -> scheduleDay.occupy(block) );
				
				assertThat("freeTimeBlock 1 does not match the expected one ", not(scheduleDay.getFreeTimeBlocksOfTheDay().get(0).equals(freeTimeBlock1)));
				
				assertThat("freeTimeBlock 2 does not match the expected one ", not(scheduleDay.getFreeTimeBlocksOfTheDay().get(1).equals(freeTimeBlock2)));
				
				assertThat("freeTimeBlock 3 does not match the expected one ", not(scheduleDay.getFreeTimeBlocksOfTheDay().get(2).equals(freeTimeBlock3)));
				
				assertThat("freeTimeBlock 4 does not match the expected one ", not(scheduleDay.getFreeTimeBlocksOfTheDay().get(3).equals(freeTimeBlock4)));
			
			}
		}
		
	}
	
	 
}
