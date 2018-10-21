package scheduling.test.timing;

import static org.junit.Assert.assertTrue; 
import java.io.IOException; 
import java.nio.file.Paths; 
import java.util.HashMap; 
import java.util.Map; 
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import resources.components.elements.POJO.Course.CoursePOJO;
import scheduling.SchedulingCollisionManager; 

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoadScheduleDayWithCourseAssetsTest {

	private static CoursePOJO angewandteInformatikCoursePOJOAsset; 
	
	private static CoursePOJO wirtschaftsInformatikCoursePOJOAsset; 
	 
	private static Map<String, CoursePOJO> coursePOJOs; 
	
	private static final String AISE = "Angewandte Informatik";  
	
	private static final String WiInf = "Wirtschaftsinformatik";  
	
	@BeforeClass
	public static void setCoursePOJOs() {
	
		coursePOJOs = new HashMap<>();
		
		coursePOJOs.put( AISE , angewandteInformatikCoursePOJOAsset);
	
		coursePOJOs.put( WiInf  , wirtschaftsInformatikCoursePOJOAsset); 
	}
	
	@Rule
	public TestName testName = new TestName();	
	 
	@Before
	public void setUpWrongFileAsset() {
	 
	    	
		if(testName.getMethodName().equals("TestA_check_IfWeCanInitializeCollisionChecks")){
	    	 
		
			coursePOJOs.put( AISE , loadJSONFile("courseangewandteinformatik.json"));
		
			coursePOJOs.put( WiInf  , loadJSONFile("coursewirtschaftsinformatik.json")); 
			 
		}
		else if(testName.getMethodName().equals("TestC_check_IfWeCanGetCollision")){
	    	 
			
			coursePOJOs.put( AISE , loadJSONFile("courseangewandteinformatikCollisionOnFridayCollisionBetweenProgrammingAndDBMSAt1000_1200.json"));
		
			coursePOJOs.put( WiInf  , loadJSONFile("coursewirtschaftsinformatik.json")); 
			 
		}
	
		else if(testName.getMethodName().equals("TestD_check_IfWeCanGetCollisions")){
	    	 
			
			coursePOJOs.put( AISE , loadJSONFile("courseangewandteinformatikCollisionOnFridayCollisionBetweenProgrammingAndDBMSAt1000_1200WithAnaAndEsAt1600_1800.json"));
		
			coursePOJOs.put( WiInf  , loadJSONFile("coursewirtschaftsinformatik.json")); 
			 
		}
		
		else if(testName.getMethodName().equals("TestE_check_IfWeCanGetCollisions")){
	    	 
			
			coursePOJOs.put( AISE , loadJSONFile("courseangewandteinformatikCollisionOnFridaySEP18_20AndDBMS18_20AndAna14_16AndES14_16.json"));
		
			coursePOJOs.put( WiInf  , loadJSONFile("coursewirtschaftsinformatik.json")); 
			 
		}
		
		
		
		
	}
	
	
	
	private static CoursePOJO loadJSONFile(String jsonFileName ) {
		
		
		ObjectMapper mapper = new ObjectMapper();
		   
		CoursePOJO pojo = null; 
		
		try {
			 
			pojo  =  (CoursePOJO)  mapper.readValue( Paths.get(System.getProperty("user.dir"), "/src/test/resources/Files/".concat(jsonFileName)).toFile(), CoursePOJO.class);
	
			
		} catch (  IllegalArgumentException |   IOException cannotReadJSON) {
	
			cannotReadJSON.printStackTrace(); 
			
		
			assertTrue(false);
		}
		
		return pojo; 
	}
	
	
	
	
	
	 
	public void TestA_check_IfWeCanInitializeCollisionChecks()
	{  
		
		assertTrue(coursePOJOs.get(AISE) != null);
		
		assertTrue( coursePOJOs.get(WiInf)  != null);
	}
	
 
	/*public void TestB_check_IfWeCanInvitializeCollisionChecks()
	{
		
		SchedulingCollisionManager schedulingMatrix = new SchedulingCollisionManager() ;
		
		// At first we read the standard configuration from the data repository, that does not have 
		// any collisions, with interests .. 
		
		List<ScheduleLectureTimeBlock> collidedLectures = schedulingMatrix.checkConflicts(coursePOJOs.get(AISE), 
				coursePOJOs.get(WiInf));
		
		// So if anything does work as expected, there shall not be any collision...
		
		assertTrue(collidedLectures.size() == 0);
	}
	
 
	
	
	@Test
	public void TestC_check_IfWeCanGetCollision()
	{
		
		SchedulingCollisionManager schedulingMatrix = new SchedulingCollisionManager() ;
		
		// At first we read the standard configuration from the data repository, that does not have 
		// any collisions, with interests .. 
		
		List<ScheduleLectureTimeBlock> collidedLectures = schedulingMatrix.checkConflicts(coursePOJOs.get(AISE), 
				coursePOJOs.get(WiInf));
		
		// So if anything does work as expected, there shall not be any collision...
		

		System.out.println("-->  " + collidedLectures);
		
		
		assertTrue(collidedLectures.size() == 3);
			
		assertTrue(collidedLectures.toString().equals("[This TimeBlock Programmierung expands between 10:00 : 12:00, This TimeBlock Software Engineering expands between 18:00 : 20:00, This TimeBlock Datenbankmanagementsysteme expands between 12:00 : 14:00]"));
	
	}*/
	
	@Test 
	public void TestD_check_IfWeCanGetCollisions()
	{
		
		SchedulingCollisionManager schedulingMatrix = new SchedulingCollisionManager() ;
		
		// At first we read the standard configuration from the data repository, that does not have 
		// any collisions, with interests .. 
		
		/* schedulingMatrix.checkConflicts(coursePOJOs.get(AISE), 
				coursePOJOs.get(WiInf));*/
		
		// So if anything does work as expected, there shall not be any collision...
		
		//assertTrue(collidedLectures.size() == 4);
	 
		//assertTrue(collidedLectures.toString().equals("[This TimeBlock Programmierung expands between 10:00 : 12:00, This TimeBlock Analysis für Informatiker und Wirtschaftsinformatiker expands between 16:00 : 18:00, This TimeBlock Software Engineering expands between 18:00 : 20:00, This TimeBlock Datenbankmanagementsysteme expands between 12:00 : 14:00]"));	 
	}
	
	@Test
	public void TestE_check_IfWeCanGetCollisions()
	{
		
		SchedulingCollisionManager schedulingMatrix = new SchedulingCollisionManager() ;
		
		// At first we read the standard configuration from the data repository, that does not have 
		// any collisions, with interests .. 
		
	/*	 schedulingMatrix.checkConflicts(coursePOJOs.get(AISE), 
				coursePOJOs.get(WiInf));*/
		
		// So if anything does work as expected, there shall not be any collision...
		
		//assertTrue(collidedLectures.size() == 4);
	 
		//assertTrue(collidedLectures.toString().equals("[This TimeBlock Programmierung expands between 10:00 : 12:00, This TimeBlock Analysis für Informatiker und Wirtschaftsinformatiker expands between 16:00 : 18:00, This TimeBlock Software Engineering expands between 18:00 : 20:00, This TimeBlock Datenbankmanagementsysteme expands between 12:00 : 14:00]"));	 
	}
}
