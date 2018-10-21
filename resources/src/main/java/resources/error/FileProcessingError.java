package resources.error;

import java.io.File;

public class FileProcessingError extends InternalError{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2502264156886714219L;

	private static final String FILE_NAME = "FILE_NAME";
	
	private static final String errorMessage = "Die Datei " + FILE_NAME + " kann nicht verarbeitet werden";
	
	
	public FileProcessingError(String fileName) {
		super(errorMessage.replace(FILE_NAME, fileName));
	}

	public FileProcessingError(File file) {
		super(errorMessage.replace(FILE_NAME, file.getName()));
	}

}
