package resources.constraint.strategies;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import resources.error.ConstraintViolationError;
import resources.utils.general.GeneralPurpose;
 
/**
 * Constrain that is used to organize the "VDTyp"
 * column in the XLS File 
 * 
 * @Component is used by the LectureConstraint
 * */

@Component
public class VDTypConstraintStrategy extends ConstraintStrategy<String> {

	public final static String incorrectLabel = "Vorlesung/Übung"; 
	
	public final static String lectureLabel = "Vorlesung"; 
	
	public final static String practiceLabel = "Übung"; 
	
	// here we define the keys that we are expecting, at the column "VDTyp"
	
	private final String[]  VDTypLegalValues = {lectureLabel ,  practiceLabel,  incorrectLabel };
		
	
	public VDTypConstraintStrategy( ) {
		super(GeneralPurpose.CollectionToArray(Arrays.asList("VDTyp")));
	}
 
	@Override
	public void applyConstraint(String constraintValue) throws ConstraintViolationError {
		 
	 
		// we check if a givin Value is part of the expected ones ...
		
		if(Arrays.asList(VDTypLegalValues).contains(constraintValue))
		{
			m_constrainedValue = constraintValue;
		}
		else {
			
			throw new ConstraintViolationError("Constraint VDTyp got violated with the value " + constraintValue + 
					" This value is not part of the expected ones  { \"Vorlesung\", \"Übung\", \"Vorlesung/Übung\" } ");
		}
		 	
	}
 
}
