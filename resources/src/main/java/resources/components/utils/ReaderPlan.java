package resources.components.utils;


/**
 * Interface that is needed to , 
 * describe how a certain file shall be read 
 * 
 * @genericType (TargetClass), needs to define the expected type 
 * of a read content 
 * */


@FunctionalInterface
public interface ReaderPlan<TargetClass> {

	/*
	 * Method that shall describe 
	 * how a certain file, shall be read ... 
	 * 
	 * @return ()
	 * 
	 * 
	 * */
	public TargetClass readSpecifcFile() throws ClassCastException;
}
