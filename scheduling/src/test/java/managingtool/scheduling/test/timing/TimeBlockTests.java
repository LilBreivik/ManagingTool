package managingtool.scheduling.test.timing;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import scheduling.timing.TimeBlock;

public class TimeBlockTests {

	private static TimeBlock timeBlock; 
	
	private static String startTime = "08:00";
	
	private static String endTime = "20:00";
	
	@BeforeClass
	public  static void initializeNeededTimeBlock() {
		
		timeBlock = new TimeBlock(startTime,  endTime ); 
	}
	
	
	@Test
	public void TestA_checkIfWeCanInitializeTimeBlocks() {
		
		assertTrue(timeBlock != null);
	}
}
