package resources.fileconnection;
 
import java.io.File;
import java.nio.file.Path;

/**
 * This class describes the general 
 * File-Connection Object. 
 * 
 * This Object shall handle the connection to a 
 * certain File 
 * 
 * 
 * 
 * */

public abstract class GeneralFileConnection {

	// The path, where a certain file shall be
	
	protected Path p_path; 
	 
	private File connectedFile;
	 
	public GeneralFileConnection(Path path ) {
		
		p_path =  path; 
	}
	
	public Path getPath() {
		
		return p_path;
	}
	
	public abstract void buildConnectionToAFile(File file);

	public File getConnectedFile() {
		return connectedFile;
	}

	public void setConnectedFile(File connectedFile) {
		this.connectedFile = connectedFile;
	}
 
}
