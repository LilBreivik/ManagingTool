package resources.constraint;
  
import java.util.Arrays; 
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Lecture.LecturePOJOBuilderRecipe;
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO;
import resources.constraint.strategies.ConstraintStrategy;
import resources.constraint.strategies.VDTypConstraintStrategy;
import resources.constraint.strategies.VNameConstraintStrategy;
import resources.constraint.strategies.VZeitConstraintStrategy;
import resources.error.ConstraintViolationError;
 
 


/**
 * This class contains every constraint
 * that is needed to parse the contents of 
 * the xls file, to be more precisely, 
 * to parse the lectures correctly with their 
 * timings and paractice names...  
 * 
 * 
 * */

@Component
public class LectureConstraint {
  
	private List<ConstraintStrategy<String>> m_constraints;

	private LecturePOJOBuilderRecipe m_lectureRecipe; 
	
	@Autowired
	public LectureConstraint(VNameConstraintStrategy VNameConstraint, 
								VDTypConstraintStrategy VDTypConstraint,
									VZeitConstraintStrategy VZeitConstraint) { 
		 
		 
		m_constraints = Arrays.asList( VNameConstraint, 
									   		VDTypConstraint, 
									   			VZeitConstraint); 
		
	}
 
 
	/**
	 * This function, takes a row with possible contents, that can 
	 * be added. 
	 * 
	 * If a Row contains an illegal Value, it will get rejected 
	 * 
	 * @param  (ConstraintRow) constraintValue
	 * @return (DisAssembledXLSRow) row that just contains constrained values  
	 * */
	
	
	public LectureSchedulePOJO applyLectureConstraints(ConstraintRow constraintValue) throws ConstraintViolationError {
	 	 
		m_lectureRecipe = new LecturePOJOBuilderRecipe(); 
		
	    // if a constraint cannot bem applied , then the cell will not get collected 
		
		// if a cell hurts a constraint then it will not be collected too.. 
					
		for(DisAssembledXLSCell cell : constraintValue.getCellsFromRow()) {
		
			// We iterate through the available constraints and check, if a 
			// constrain shall be applied to...
			
			for(ConstraintStrategy<String> constraint : m_constraints) {
				
			 	
					if(constraint.checkForConstraint(cell.getCellColumnName())){
				 	
				 
						constraint.applyConstraint(cell.getCellValue());
			 			 
			 			String value = 	constraint.getConstrainedValue();
			 	 
			 			m_lectureRecipe.put(cell.getCellColumnName(),
								value);
							
						break; 
					}
				
			}
		}
		 
		
		return m_lectureRecipe.buildPOJO();
		 
	}
			
}
