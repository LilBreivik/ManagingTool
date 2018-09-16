package resources.constraint;

import java.util.Collection;

import resources.constraint.strategies.ConstraintStrategy;
import resources.utils.general.GeneralPurpose;


public abstract class Constraint<T> {

	protected Collection<T> m_itemsThatAreConstrained;
	
	private ConstraintStrategy<T>  m_constraintStrategy; 
	
	public Constraint(ConstraintStrategy<T> constraintStrategy ) {
	
		m_constraintStrategy =  constraintStrategy;
	}
	 
	protected void loadConstrainedItems(T... items ) {
		
		m_itemsThatAreConstrained = GeneralPurpose.ArrayToCollection(items);
	}
	
	public void applyConstraint(T constraintValue) {
		
		if(m_itemsThatAreConstrained.contains(constraintValue)) {
			
			m_constraintStrategy.checkForConstraint(constraintValue);
		}
		 
	}
	 
}
