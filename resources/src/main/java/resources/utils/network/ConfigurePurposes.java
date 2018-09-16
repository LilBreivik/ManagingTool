package resources.utils.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

import resources.error.ConfigurationFailedError;
 
/**
 * This clas contains methods , to 
 * check the port and hostname , of network 
 * related purposes .. 
 * */

public class ConfigurePurposes {

	

	/**
	 * Checks to see if a specific port is available.
	 *
	 * @param port the port to check for availability
	 */
	public static boolean checkPortAvailability(int port) {
	    
		if(port < 0 || port > 65535) {
			return false;
	
		}
		else {
			

		    ServerSocket ss = null; 
		    try {
		        ss = new ServerSocket(port);
		        ss.setReuseAddress(true); 
		        return true;
		    } 
		    catch (IOException e) {
		    } 
		    
		    finally {
		       
		    	
		        if (ss != null) {
		            try {
		                ss.close();
		            } catch (IOException e) {
		                /* should not be thrown */
		            }
		        }
		    }

		    return false;
		}
	}
	
	/**
	 * Checks the availability of a hostname
	 * 
	 * (If the hostname shall be localhost, hostname must be empty....)
	 *
	 * @param hostname (String) the hostname, that will gets cheked if its available 
	 */
	
	public static  boolean checkHostnameAvailability(String hostname) {
			
		try {
			
			InetAddress addr = InetAddress.getByName(hostname);
			
			 // Check if the address is a valid special local or loop back
		    if (addr.isAnyLocalAddress() || addr.isLoopbackAddress())
		        return true;

		    // Check if the address is defined on any interface
		    try {
		    	
		        return NetworkInterface.getByInetAddress(addr) != null;
		    } 
		    catch (SocketException e) {
		        return false;
		    }
		}
		catch(UnknownHostException unknownHost) {
			
			return false; 
		}		
	}
	 
	public static void checkIfServiceIsOnline(URL urlToService) throws ConfigurationFailedError {
		
		 
			try {
				
				Socket sockToCheckService = new Socket();
				sockToCheckService.connect(new InetSocketAddress(InetAddress.getByName(urlToService.getHost()),
																		urlToService.getPort()),
																			3000);
				
				
			} 
			catch (UnknownHostException e) {
				
				throw new ConfigurationFailedError("Cannot find the hostname");
			} 
			catch (IOException e) {
				
				throw new ConfigurationFailedError("Port Number seems to be wrong");
			}
		 
	}
	
}
