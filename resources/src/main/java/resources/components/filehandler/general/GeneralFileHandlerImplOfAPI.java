package resources.components.filehandler.general;

import java.io.File;
import java.io.FileNotFoundException; 
import java.io.IOException;

import resources.components.filereader.general.IFileReader;
import resources.components.filereader.utils.FileNameTranslator;
import resources.components.filewriter.general.IFileWriter;
import resources.error.FileIsMissingError;
import resources.error.InternalError;
import resources.utils.pathmanager.PathManager;

/**
 * 
 * This class implements the 
 * File handling API methods of 
 * the GeneralFileHandler 
 * 
 * CAUTION: inherited Classses, need to hide 
 * methods that are not need
 * 
 * */

public abstract class GeneralFileHandlerImplOfAPI 
											extends GeneralFileHandler {

	
	private IFileReader m_fileReader; 
	
	private IFileWriter m_fileWriter; 
	
	/*
	 * Constrctor for classes 
	 * that shall just use createFile, and/or readFile
	 * */
	
	public GeneralFileHandlerImplOfAPI(PathManager pathManager, FileNameTranslator fileNameTranslator) {
		super(pathManager, fileNameTranslator); 
	}


	public GeneralFileHandlerImplOfAPI(PathManager pathManager, 
										FileNameTranslator fileNameTranslator, 
											IFileReader  fileReader) {
		super(pathManager, fileNameTranslator); 
		
		m_fileReader = fileReader; 
	}
	

	public GeneralFileHandlerImplOfAPI(PathManager pathManager, 
										FileNameTranslator fileNameTranslator, 
											IFileReader fileReader, 
												IFileWriter  fileWriter) {
		super(pathManager, fileNameTranslator); 
		
		m_fileReader = fileReader; 
		m_fileWriter = fileWriter;
	}
	
	
	
	@Override 
	protected  void createFile( String fileName) {
		
		File fileThatShallBeCreated = new File(p_PathManager.getPathToOperateOn().toString() , fileName);
		
		try {
						  
			  fileThatShallBeCreated .createNewFile();
		}  
		catch (IOException  creationError) {
			 
			  throw new InternalError("Die Datei " + fileThatShallBeCreated.getName() + " kann nicht erzeugt werden ");
		}
	}
	

	@Override  
	@SuppressWarnings("unchecked")
	protected  <ReadFileType> ReadFileType readFile( String fileName) {
		
		ReadFileType dummyObject = null;	
		 
		try {
			   
			dummyObject =  (ReadFileType)  m_fileReader.readFile(determinePhysicallyStoredFile(fileName));
	    }
		catch (FileNotFoundException fileNotThereError) {
			  
			FileIsMissingError fileIsMissing =  new FileIsMissingError("Die Datei " + fileName + " ist nicht vorhanden");
			
			fileIsMissing.missingFileName = fileName; 
			
			fileIsMissing.missingFileCause = "abgerufen"; 
			
			throw   fileIsMissing;
	    } 
	    catch (ClassCastException |  IOException  internalError) {
			  
		    throw new InternalError("Die Datei " + fileName + " kann nicht verarbeitet werden");
			
		}
	    	   
	    return dummyObject;	
		
	} 
	
	
	@Override
	protected  < WriteFileType> void writeToFile(String fileName, WriteFileType content) {
		
		
		File fileThatShallBeAppended = new File(p_PathManager.getPathToOperateOn().toString() , fileName);
		
		try {
		
			m_fileWriter.writeToFile(fileThatShallBeAppended, content);
		
		} 
		catch (IOException e) {
		
			throw new InternalError("Die Datei " + fileThatShallBeAppended.getName() + " kann nicht verarbeitet werden ");
		}
		
	}
	 

}
