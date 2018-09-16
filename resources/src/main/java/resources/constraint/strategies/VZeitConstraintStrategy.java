package resources.constraint.strategies;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.springframework.stereotype.Component;
import resources.error.ConstraintViolationError;
import resources.utils.general.GeneralPurpose;

import static  resources.utils.general.Constants.Days.Montag;
import static  resources.utils.general.Constants.Days.Dienstag;
import static  resources.utils.general.Constants.Days.Mittwoch;
import static  resources.utils.general.Constants.Days.Donnerstag;
import static  resources.utils.general.Constants.Days.Freitag;

@Component 
public class VZeitConstraintStrategy extends ConstraintStrategy<String> {

	
	
	/*
	 * HashMap that will contain
	 * the shortcuts of the week days, mapped with its fully 
	 * qualified names....
	 */
	
	
	
	private Map<String, String> expectedValues = new HashMap<String, String>();
	
 	/*
 	 * Hash Map that 
 	 * will contain the days, with the expected timing when an event happens
 	 * 
 	 * we need to use a List<String> because , on some days there could be more than one practice seesion 
 	 * */
	private Map<String, List<String>> resultingValues  = new HashMap<String, List<String>>();
	
	
	public VZeitConstraintStrategy() {
		super(GeneralPurpose.CollectionToArray(Arrays.asList("VZeit"))); 
		reloadValues(); 
	}
 
	
	/**
	 * This function initalizes the needed "Value" - Hashmaps
	 * */
	
	private void reloadValues() {
		
		 /*
		  * Hashmap that contains the mapping 
		  * between the day shortcuts and their fully
		  * qualified names...
		  * */
		
		
		/**
		 * @FIXME: Do not forget to chanhe the typ of expectedValues
		 * to a hashmap that automaitcally handles the transiton from this 
		 * enu to a string
		 * */
		
		 expectedValues = new HashMap<String, String>() {{
			
				put("Mo", Montag.toString());
				put("Di", Dienstag.toString());
				put("Mi", Mittwoch.toString());
				put("Do", Donnerstag.toString());
				put("Fr", Freitag.toString());				        
		 }};
		
		
		 /*
		  * Hashmap that contains the mapping 
		  * between the day and the timings 
		  * of an event 
		  * */
		 
		 resultingValues  = new HashMap<String, List<String>>() {{
			    
				put("Montag", new ArrayList<String>());
		        put("Dienstag", new ArrayList<String>());
		        put("Mittwoch",  new ArrayList<String>());
		        put("Donnerstag",  new ArrayList<String>());
		        put("Freitag",  new ArrayList<String>());
		        
		  }};
	}
	
	
	@Override
	public void applyConstraint(String constraintValue) throws ConstraintViolationError {
		
		 	
		// timing , contains a string that combines the day with the times, where a 
		// a lecture happens ... 
		
		for(String timing : constraintValue.split(";")) {
			
			// First we will check if a lecture will 
			// happen on an illegal date, like Saturday... 
			
			for(String key : expectedValues.keySet()) {
			
				// if we find a timing for a day , we will try to parse its start and end times 
				
				if(timing .contains(key)) { 
					
					try {
 
						// we first need to order the results, 
						// because in the real exisitng list, that timiings are not sorted, 
						// it is possible, that there could be permutation, whether the days 
						// are cross distributed 
						
						 		resultingValues.get(expectedValues.get(key)).add(timing.replace(" ", "").replace(key, ""));	 
						
						
						//resultingValues.get(expectedValues.get(key)).add(timing.replace(" ", "").replace(key, expectedValues.get(key).concat("§")));	 
					}
					catch(NullPointerException nullError) 
					{
						// we will throw this Exception if Somone types "Sa" or "So "
						throw new ConstraintViolationError("The Constraint VZeit got violated the key " + key + " is not part of the expected ones "  + 
								"  { \"Mo\", \"Di\", \"Mi\" , \"Do\", \"Fr\", } ");
					}
					 
				}
				
			}
			
			// if a day short cut was not met, we will ignore the timing .... 
		}
		 
	}
	
	
	// function that builds a "Tming-String", a String that is later handled "
	// to get the needed Timing Schedule .. 
	
	// @FixMe: maybe needes to be corrected to an own Object ?? 
	
	public String assembleTimingsString(){
		
		List<String> constrainedValues = new ArrayList<>();
		
		// Montag§(12:00-14:00)
		 		
		for(String day :  GeneralPurpose.SetToList(resultingValues.keySet()) ) {
			
			StringBuilder lectureEventTimings = new StringBuilder(); 
			
			lectureEventTimings.append(day.concat("%"));
			
			for(int itr = 0; itr < resultingValues.get(day).size() ; itr += 1) {
			
				
				// here we add the different timings, of an event, that happen on the same day 
				// Format firstTiming*secondTiming*......lastTiming
				// timing = (startzeit : endzeit)
			
				// after all we add it to a String represnting the timings  ... 
				
				lectureEventTimings.append(resultingValues.get(day).get(itr).concat( ( itr != (resultingValues.get(day).size() - 1) ? "%" : "")));
			
				if(itr == (resultingValues.get(day).size() - 1)) {
					
					if(GeneralPurpose.SetToList(resultingValues.keySet()).indexOf(day) !=  GeneralPurpose.SetToList(resultingValues.keySet()).size() - 1)
					{
						lectureEventTimings.append("§");
					}
					
					constrainedValues.add(lectureEventTimings.toString());
				}
			}
				
		}
		
		return constrainedValues.stream().reduce("", (a,b) ->  a + b);
	}

	@Override
	public String getConstrainedValue() {
		 
		m_constrainedValue  =  assembleTimingsString(); 
		
		reloadValues();
		return  m_constrainedValue; 
	}
	
	 
	
 
}
