package resources.error;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class describes the 
 * "Main" Custom Excpetion class, 
 * ever yother custom exception inherits from .. 
 * 
 * CAUTION: The Exceptions are unchecked, so you need to
 * pay intetion, whether handle them or not 
 * */

public class MasterError extends RuntimeException {

	private String m_errorMessage; 
	
	
	public MasterError(String errorMessage)
	{
		m_errorMessage = errorMessage; 
		
		// print the error Message on the Screen 
		//@Fixme: put a logger 
		
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		
		return m_errorMessage;
		 
	}
}
