package managingtool.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters; 
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.utils.general.GeneralPurpose;
import testcontext.ArrayCollectionTestApplicationContext;
 

 
@ContextConfiguration( classes =  ArrayCollectionTestApplicationContext.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) 
public class ArrayCollectionTransformingTest {


	private static String[] givenInput;
	
	private static Collection<String> expectedResult; 
	
	
	@BeforeClass
	public static void initTestRequirements() {
		
		String[] testInput = {"Rita", "Ramona", "Rudi"};
		 
		expectedResult = Arrays.asList("Rita", "Ramona", "Rudi");
		
		givenInput =  testInput;
	}
	

	@Test
	public void TESTA_testArrayToCollection() {
		
		Collection<String> resultToCompare = GeneralPurpose.ArrayToCollection(givenInput);
		
		assertThat("The Lists are not equal " , resultToCompare  , is(expectedResult));
	}
	
	@Test
	public void TESTB_testCollectionToArray() {
		
		String[] resultToCompare = GeneralPurpose.CollectionToArray(expectedResult);
		
		assertThat("The Lists are not equal " , resultToCompare  , is( givenInput ));
	}
}
