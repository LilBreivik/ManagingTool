package resources.utils.user;


import java.nio.charset.StandardCharsets;
import java.util.Arrays; 
import java.util.List; 


public class DelimeteredPassword{
	
	private static final String DELIMETER = "DELIMETER"; 

	public static List<String> translatedDelimeteredPassword(String delimeteredPassword) {
		
		return Arrays.asList(delimeteredPassword.split(DELIMETER));	 
	}
	
	public static String getDelimeteredPassword(byte[]... passwordHashes) {
		
		List<byte[]> m_passwordHashes = Arrays.asList(passwordHashes); 
		
		StringBuilder delimeteredPassword = new StringBuilder(); 
	
		
		for(byte[] passwordHash : m_passwordHashes) {
			 
			
			delimeteredPassword.append((new String(passwordHash, StandardCharsets.UTF_8)).concat(DELIMETER));
		}
		 
		
		return delimeteredPassword.toString(); 
	}
}