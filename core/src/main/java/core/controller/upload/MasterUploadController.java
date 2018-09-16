package core.controller.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path; 

import core.controller.handler.upload.UploadHandler;
import core.controller.processor.upload.UploadProcessor;
import resources.components.filehandler.FileHandler;
import resources.error.parameter.ParameterViolationError; 

public abstract class MasterUploadController{
 
	private FileHandler m_filehandler; 
	
	public MasterUploadController(FileHandler filehandler) {
		
		m_filehandler = filehandler;
	}
	
	/**
	 * 
	 * @param UploadVerifier verifier, verifies the upload Parameter, will throw ParameterViolation Exception  
	 * @param UploadHanlder handler, handles the uploaded File 
	   @param UploadProcessing processor, processes the uploaded File , it will handle it to the 
	 * @return 
	 * 
	 * (Unchecked Exceptions... needs to be caught by the inherits)
	 * 
	 * @throws ParameterViolationError 
	 * @throws FileNotFoundException 
	 * @throws ClassCastException 
	 * 
	 * */
	
	protected void uploadFile(UploadHandler handler, 
								UploadProcessor processor) {
	
			// At first we check the upload Parameter 
		
		//	verifier.verifyUploadParameter();
		
			// Secondly, we need to craft the file to the expected directory 
			
			handler.handleUploadedFile();
	
			// and then we will procede the file 
			
	 
			processor.processUploadedFile();
			
			// If they pass, we will add the file to the repo 
	 
	}
	
	protected void moveUploadedFile(File file, Path targetPath){
		
		this.m_filehandler.moveFile(file, targetPath);
		 
	}
	 
}
