package resources.constraint.impl;

import java.util.List;

import resources.components.elements.POJO.Lecture.POJOBuilderRecipe;
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO;
import resources.constraint.ConstraintRow;
import resources.constraint.DisAssembledXLSCell;
import resources.constraint.strategies.ConstraintStrategy;
import resources.error.ConstraintViolationError;

/**
 * class that describes the common way, 
 * of a certain contraint needed to read 
 * a certain xls file
 * 
 * 
 * */

public abstract class ConstraintImpl< POJOToBeBuild, Recipe extends POJOBuilderRecipe<POJOToBeBuild>> {


	protected  List<ConstraintStrategy<String>> p_constraints;

	protected  Recipe p_pojoRecipe; 
	
	public ConstraintImpl(List<ConstraintStrategy<String>> constraints, 
								Recipe pojoRecipe ) {
		
		p_constraints =  constraints;
		p_pojoRecipe =  pojoRecipe;
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
	
	
	public POJOToBeBuild applyLectureConstraints(ConstraintRow constraintValue) throws ConstraintViolationError {
	 	
	    // if a constraint cannot bem applied , then the cell will not get collected 
		
		// if a cell hurts a constraint then it will not be collected too.. 
					
		for(DisAssembledXLSCell cell : constraintValue.getCellsFromRow()) {
		
			// We iterate through the available constraints and check, if a 
			// constrain shall be applied to...
			
			for(ConstraintStrategy<String> constraint : p_constraints) {
				 
					if(constraint.checkForConstraint(cell.getCellColumnName())){
				 	 
						constraint.applyConstraint(cell.getCellValue());
			 			 
			 			String value = 	constraint.getConstrainedValue();
			 	 
			 			p_pojoRecipe.put(cell.getCellColumnName(),
								value);
							
						break; 
					}
				
			}
		}
		 
		
		return p_pojoRecipe.buildPOJO();
		 
	}


 
}
