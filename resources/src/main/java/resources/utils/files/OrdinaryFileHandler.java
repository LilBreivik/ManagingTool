package resources.utils.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException; 
import java.nio.file.Path;
import java.nio.file.Paths; 

public class OrdinaryFileHandler {

	/**
	 * Ordinary Method to move a file 
	 * 
	 * A moved File, will overwrite any existing file
	 * 
	 * @param (File) file, File that shall be moved 
	 * 			(Path) targetPath, Path where the file shall be moved to 
	 * */
	
	public static Path buildPath(Path targetPath, String fileName) throws InternalError{
	 
		try {
			
			 
			return Paths.get(targetPath.toAbsolutePath().toString(),
					fileName); 
		}
		catch(InvalidPathException invaliedPath) 
		{
			
			throw new resources.error.InternalError("Cannot build path");
		}
		 
	}
	

	
	/**
	 * Ordinary Method to move a file 
	 * 
	 * A moved File, will overwrite any existing file
	 * 
	 * @param (File) file, File that shall be moved 
	 * 			(Path) targetPath, Path where the file shall be moved to 
	 * */
	
	public static void moveFile(File file, Path targetPath ) throws IOException{
	  
		
		if(Paths.get(targetPath.toString() , file.getName()).toFile().exists()) {
			
			Files.delete(Paths.get(targetPath.toString(), file.getName()));
		} 
			
	    com.google.common.io.Files.move(file, targetPath.toFile()); 
		
	}
	
	
	
	/**
	 * Ordinary Method to move a file 
	 * 
	 * A moved File, will overwrite any existing file
	 * 
	 * @param (File) file, File that shall be moved 
	 * 			(Path) targetPath, Path where the file shall be moved to 
	 * */
	
	public static void copyFile(File file, Path targetPath ) throws IOException{
	 
		if(Paths.get(targetPath.toString() , file.getName()).toFile().exists()) {
			
			Files.delete(Paths.get(targetPath.toString(), file.getName()));
		} 
		
		
		com.google.common.io.Files.copy(file, targetPath.toFile()); 
	}
	
	
	
	

	/**
	 * Ordinary Method to create a file 
	 * 
	 * This function creates a file
	 * 
	 * it it does exist, it will throw an InternalError 
	 *  
	 * 
	 * @param (File) file, File that shall be deleted 
	 * 
	 * (unchecked)
	 * @throws InternalError
	 * */
	
	public static void createFile(File file) {
		
		  
		try {
			  
			Path newFilePath = Paths.get(file.getAbsolutePath().toString());
				
			Files.createFile(newFilePath);
		} 
		catch(SecurityException isnotAllowedToDelete) {
		    	
		    throw  new InternalError("The Permissons do not permit the create of" + file.getName() + " at " + file.getPath().toString());
		}
        catch (IOException  e) {
			
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Ordinary Method to read a file 
	 * 
	 * This method will just return the RAW Content 
	 * of a file. 
	 * 
	 * If you want to read a file, with a certain format, 
	 * you have to use/inherit the GeneralFileHandler
	 * 
	 * @param (File) file, File that shall be read
	 * 
	 * @return (byte[]), File read as byte array 
	 * (unchecked)
	 * @throws InternalError
	 * */
	
	public static byte[]  readFile(File file) {
		
		try {
		
			return Files.readAllBytes(file.toPath());
		
		} catch (IOException e) {
			
			throw  new InternalError("Cannnot read file at " + file.toPath().toString());
		}

	}
	
	
	/**
	 * Ordinary Method to delete a file 
	 * 
	 * This function deletes a file, if it does exist.... 
	 * 
	 * Paths cannot be deleted if they are not empty... 
	 * 
	 * 
	 * @param (File) file, File that shall be deleted 
	 * 
	 * (unchecked)
	 * @throws InternalError
	 * */
	
	public static void deleteFile(File file) {
		
		
	    try {
	    	
			Files.delete(file.toPath());
	    } 
	    catch(DirectoryNotEmptyException cannotDeletePath) {
	    	
	    	throw  new InternalError("Cannnot delete path " + file.toPath().toString());
	    }
	    catch(SecurityException isnotAllowedToDelete) {
	    	
	    	throw  new InternalError("The Permissons do not permit the delete of" + file.getName() + " at " + file.getPath().toString());
	    }
	    catch (IOException  e) {
		
	    	// happens if the file does not exist ..
	    	
			e.printStackTrace();
		}
	}
	
}


