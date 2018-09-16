package resources.constraint.strategies;

import java.util.Collection;

import resources.error.ConstraintViolationError;
import resources.utils.general.GeneralPurpose;

 
public abstract class ConstraintStrategy<T> {
 
	
	// 	private List<String> expectedValues = Arrays.asList("Vorlesung", "Übung", "Vorlesung/Übung");
	
	protected Collection<T> m_valuesThatWillBeConstrained;
	
	protected T m_constrainedValue;
	
	public ConstraintStrategy(T[] valuesThatWillBeConstrained) {
	 
		
		m_valuesThatWillBeConstrained = GeneralPurpose.ArrayToCollection(valuesThatWillBeConstrained);
	}
	
	public boolean checkForConstraint(T constraintValue) {
		
		return m_valuesThatWillBeConstrained.contains(constraintValue);
	}

	 
	
	/**
	 * This Method applies the 
	 * implemented Constraint to 
	 * the added Value ...
	 *
	 * shall be overwritten by the inherits ... 
	 *
	 * @param (String) value to be constrained 
	 * @throws ConstraintViolationError 
	 *  
	 * */
	
	
	public abstract void  applyConstraint(T constraintValue) throws ConstraintViolationError;
	
	/**
	 * This Method returns the value , that 
	 * passed the constraint
	 * 
	 * The Method also nills the last constranied Result... 
	 * */
	
	public T getConstrainedValue() {
		
		T constrainedValue = m_constrainedValue;

		m_constrainedValue = null;
		
		return constrainedValue; 
	}
	
}
