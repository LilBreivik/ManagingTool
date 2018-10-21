package resources.components.filereader.general;

import resources.fileconnection.GeneralFileConnection;

public  abstract class GeneralFileReader < Connection extends GeneralFileConnection, ReadObjectType>
							
											implements IFileReader<ReadObjectType> {
	
	protected Connection p_Connection; 
	
	public  GeneralFileReader(Connection connection) {
		
		p_Connection = connection;  
	}
}
