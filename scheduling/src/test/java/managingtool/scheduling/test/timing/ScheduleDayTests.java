package managingtool.scheduling.test.timing;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import resources.components.elements.POJO.Scheduling.Timing.SchedulingDay;
import resources.components.elements.POJO.Scheduling.Timing.TimeBlock;
import resources.error.scheduling.CollisionError;

import static  resources.utils.general.Constants.Days.Montag;
import static  resources.utils.general.Constants.Days.Dienstag;
 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScheduleDayTests {

	private static SchedulingDay scheduleDay; 
	
	private static TimeBlock timeBlock; 
	
	@BeforeClass
	public  static void initializeScheduleDay() {
		
		scheduleDay = new SchedulingDay(Montag);
	}
	
	@Rule
	public TestName testName = new TestName();

	 
	@Before
	public void setUpWrongFileAsset() {
	      
	    	
		if(testName.getMethodName().equals("TestC_checkIfWeCanGetCorrectFreeAndOccupiedTimeAfterANewBlockWasInserted")){
	    		 
			timeBlock = new TimeBlock("12:00", "14:00"); 
			scheduleDay.occupy(timeBlock);
		}
		else if(testName.getMethodName().equals("TestD_checkIfWeCanAddSeveralTimeBlocks")){
   		 
			timeBlock = new TimeBlock("12:00", "13:00"); 
			scheduleDay.occupy(timeBlock); 
			timeBlock = new TimeBlock("18:00", "20:00"); 
			scheduleDay.occupy(timeBlock);
			timeBlock = new TimeBlock("19:00", "20:00"); 
			scheduleDay.occupy(timeBlock); 
		}
		else if(testName.getMethodName().equals("TestE_checkIfWeCanAddSeveralTimeBlocksInAnotherPermutation")){
	   		 
			scheduleDay = new SchedulingDay(Dienstag);
	
			 
			timeBlock = new TimeBlock("12:00", "13:00"); 
			scheduleDay.occupy(timeBlock); 
			timeBlock = new TimeBlock("13:00", "14:00"); 
			scheduleDay.occupy(timeBlock);
			timeBlock = new TimeBlock("16:00", "18:00"); 
			scheduleDay.occupy(timeBlock);
			timeBlock = new TimeBlock("11:00", "11:45"); 
			scheduleDay.occupy(timeBlock); 
			timeBlock = new TimeBlock("15:00", "15:45"); 
			scheduleDay.occupy(timeBlock);
			timeBlock = new TimeBlock("18:00", "19:45"); 
			scheduleDay.occupy(timeBlock);
		}
		else if(testName.getMethodName().equals("TestF_checkIfWeCanCatchACollision")){
	   		 
			scheduleDay = new SchedulingDay(Dienstag);
	 
			try {
				
				timeBlock = new TimeBlock("13:00", "14:00"); 
				scheduleDay.occupy(timeBlock);
				timeBlock = new TimeBlock("16:00", "18:00"); 
				scheduleDay.occupy(timeBlock);
				timeBlock = new TimeBlock("11:00", "11:45"); 
				scheduleDay.occupy(timeBlock); 
				timeBlock = new TimeBlock("08:45", "09:35"); 
				scheduleDay.occupy(timeBlock);
				timeBlock = new TimeBlock("15:00", "15:45"); 
				scheduleDay.occupy(timeBlock); 
				timeBlock = new TimeBlock("18:00", "19:45"); 
				scheduleDay.occupy(timeBlock);
				timeBlock = new TimeBlock("14:00", "19:45"); 
				scheduleDay.occupy(timeBlock); 
				timeBlock = new TimeBlock("12:00", "13:00"); 
				scheduleDay.occupy(timeBlock); 
				
	 
				
			}
			catch( CollisionError collision) {
				
				assertTrue(true);
			}
		} 
	   	      
	}
	      
	
	public void TestA_checkIfWeCanInitializeAScheduleDay() {
		
		assertTrue(scheduleDay != null);
	}
	
	 
	public void TestB_checkIfWeCanGetCorrectFreeAndOccupiedTime() {
		
		System.out.println(scheduleDay.getFreeTimeBlocksOfTheDay());
		System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay());
		 
	}
	
	public void TestC_checkIfWeCanGetCorrectFreeAndOccupiedTimeAfterANewBlockWasInserted() {
		
		
		System.out.println(scheduleDay.getFreeTimeBlocksOfTheDay());
		System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay());
	 
	}
	
	
	public void TestD_checkIfWeCanAddSeveralTimeBlocks() {
		
		
		System.out.println(scheduleDay.getFreeTimeBlocksOfTheDay());
		System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay());
	 
	}
	
	 
	public void TestE_checkIfWeCanAddSeveralTimeBlocksInAnotherPermutation() {
		
		
		System.out.println( scheduleDay.getFreeTimeBlocksOfTheDay()); 
	 
	}
	
	@Test
	public void TestF_checkIfWeCanCatchACollision() {
		
		System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay()); 
		System.out.println( scheduleDay.getFreeTimeBlocksOfTheDay()); 
		System.out.println(scheduleDay.getScheduledtimeBlocksOfTheDay());
		 
	}
 	public void TestG_checkIfWeGetTheMinAndMaxBlocks() {
	
		
		System.out.println(scheduleDay.getOccupiedTimeBlocksOfTheDay()); 
		System.out.println(scheduleDay.getFreeTimeBlocksOfTheDay()); 
		
	}
}
