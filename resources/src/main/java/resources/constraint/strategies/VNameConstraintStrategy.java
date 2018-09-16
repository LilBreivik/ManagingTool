package resources.constraint.strategies;
 

import java.util.Arrays;

import org.springframework.stereotype.Component;

import resources.utils.general.GeneralPurpose;
 

/**
 * Constrain that is used to organize the "VName"
 * column in the XLS File 
 * 
 * @Component is used by the LectureConstraint
 * */

@Component 
public class VNameConstraintStrategy  extends ConstraintStrategy<String>{
	
    public VNameConstraintStrategy() {
		super(GeneralPurpose.CollectionToArray(Arrays.asList("VName"))); 
	}

	 
	@Override
	public void applyConstraint(String constraintValue){
		 	
		/* we correct a givin value  ... 
		 * 
		 * because the underlying files, are written by human, there can be mistakes... 
		 * 
		 * it is not recommend, that the "&" is used, especially 
		 * there shall no ne any mixings between german and englisch language ... 
		 *
		 */
		
        if(constraintValue.contains("&")) {
			
        	 m_constrainedValue = constraintValue.replace("&", "und");
		}
		else if(constraintValue.contains("Requirements Enginering") && constraintValue.contains("Management"))
		{
			 m_constrainedValue = constraintValue.replace("und", "and");
		}
		else if(constraintValue.contains("Information Security") && constraintValue.contains("Network"))
		{
			 m_constrainedValue = constraintValue.replace("und", "and");
		}
		else {
			
			 m_constrainedValue = constraintValue;
		}
       
	}
 	
} 
