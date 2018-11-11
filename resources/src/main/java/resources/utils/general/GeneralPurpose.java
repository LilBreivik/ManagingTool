package resources.utils.general;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
 

/**
 * Methods that shall be used , to 
 * handle general operations on data structures ... 
 * */

public class GeneralPurpose {

	/**
	 * 
	 * Method that transforms an Array
	 * to its respecting Collection
	 * 
	 * @param T[] arrayThatShallBetransformed 
	 * @return  respecting Collection
	 * */
	
	public static <T> Collection<T> ArrayToCollection(T[] arrayThatShallBetransformed ) {
		
		Collection<T> arrayAsCollectionToBeTransformed= new ArrayList<>();
		
	    for(int itr = 0; itr < arrayThatShallBetransformed.length; itr += 1) {
	    	
	    	arrayAsCollectionToBeTransformed.add(arrayThatShallBetransformed[itr]);
	    }
		
	    return arrayAsCollectionToBeTransformed; 
	}
	
	
	
	/**
	 * 
	 * Method that transforms an Array
	 * to its respecting List 
	 * 
	 * @param T[] arrayThatShallBetransformed 
	 * @return  respecting List
	 * */
	
	public static <T> List<T> ArrayToList(T[] arrayThatShallBetransformed ) {
		
		List<T> listAsCollectionToBeTransformed= new ArrayList<>();
		
	    for(int itr = 0; itr < arrayThatShallBetransformed.length; itr += 1) {
	    	
	    	listAsCollectionToBeTransformed.add(arrayThatShallBetransformed[itr]);
	    }
		
	    return listAsCollectionToBeTransformed; 
	}
	
	
	/**
	 * 
	 * Method that transforms a List
	 * to its respecting Array
	 * 
	 * @param List<T> listThatShallBetransformed 
	 * @return  respecting Array
	 * */
	
	public static <T> T[] ListToArray(List<T> listThatShallBetransformed ) {
		
	    Collection<T>  tempCollection =  ListToCollection(listThatShallBetransformed);
	    
	    return CollectionToArray(tempCollection);
	}
	
	
	
	/**
	 * 
	 * Method that transforms an Array
	 * to its respecting Collection
	 * 
	 * @param T[] arrayThatShallBetransformed 
	 * @return  respecting Collection
	 * */
	
	public static <T> List<T> SetToList(Set<T> setThatShallBetransformed ) {
		
		List<T> arrayAsCollectionToBeTransformed = new ArrayList<>();
		
		Iterator<T> itr = setThatShallBetransformed.iterator();
		
		while(itr.hasNext()) {
			
			arrayAsCollectionToBeTransformed.add(itr.next());
		}
		
	    return arrayAsCollectionToBeTransformed; 
	}
	
	 
	
	/**
	 * 
	 * Method that transforms a Collection 
	 * to its respecting Array 
	 * 
	 * @param Collection<T> collectionToBeTransformed
	 * @return  respecting Array 
	 * */
	
	public static <T> T[] CollectionToArray(Collection<T> collectionToBeTransformed)  {
		
		List<T> collectionAsListToBeTransformed = new ArrayList<>(collectionToBeTransformed);
		
		// Here we "lease" an Object from the givin Collection, tondetemrine the type of the array that shall be built..
		
		T t = collectionAsListToBeTransformed.get(0);
		
		T[] sortedArray = (T[]) Array.newInstance(t.getClass(), collectionToBeTransformed.size());
		
		for(int itr = 0; itr < collectionAsListToBeTransformed.size(); itr += 1) 
		{
			sortedArray[itr] = collectionAsListToBeTransformed.get(itr);
		}
	
		return sortedArray;
	}
	
	
	/**
	 * 
	 * Method that transforms a Collection 
	 * to its respecting List
	 * 
	 * @param Collection<T> collectionToBeTransformed
	 * @return  respecting List 
	 * */
	
	public static <T> List<T> CollectionToList(Collection<T> collectionToBeTransformed) {
		
		return new ArrayList<T>(collectionToBeTransformed);
	}
	
	/**
	 * 
	 * Method that transforms a List 
	 * to its respecting Collection
	 * 
	 * @param List<T> ListToBeTransformed
	 * @return  respecting Collection 
	 * */
	
	public static <T> Collection<T> ListToCollection(List<T> ListToBeTransformed) {
		
		return new ArrayList<T>(ListToBeTransformed);
	}
	
	
	/**
	 * 
	 * Method to transform one List 
	 * to another typed one
	 * 
	 * @param List<T> ListToBeTransformed
	 * @return  respecting List of the desired Type
	 * */
	
	public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
	   
		return  from.stream().map(func).collect(Collectors.toList());
	}
	
	  
}
