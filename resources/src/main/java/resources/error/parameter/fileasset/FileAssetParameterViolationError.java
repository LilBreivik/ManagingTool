package resources.error.parameter.fileasset;

import java.io.File;

import resources.error.parameter.ParameterViolationError;

/**
 * Exception that is thrown, if 
 * a file was delivered in a parameter, 
 * and these file is corrupted, 
 * or does not match the expceted format 
 * */

public class FileAssetParameterViolationError 
												extends ParameterViolationError{
	
	public enum FileExtension {XML, XLS}

	private FileExtension m_extension; 
	
	private File m_file; 
	
	public FileAssetParameterViolationError(File file, FileExtension ext) {
		super("The Uploaded " + ext.toString() + " file " + file.getName() + "seems to be corrupted" );
		
		m_file = file; 
		m_extension = ext; 
	}

	
	public FileExtension getFileExtension() {
		
		return m_extension;
	}


	public File getM_file() {
		return m_file;
	}

 
	
}
