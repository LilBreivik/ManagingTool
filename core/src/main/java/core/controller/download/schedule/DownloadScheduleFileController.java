package core.controller.download.schedule;
 
 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import com.google.common.io.Files;
import core.controller.parameter.schedule.GenericScheduleFileParam; 
import core.controller.processor.download.DownloadProcessor;
import resources.components.filehandler.FileHandler;
import resources.error.InternalError;
import resources.error.download.AssetFileIsMissing;

public class DownloadScheduleFileController   {

	protected FileHandler m_downloadFileHandler;
	protected DownloadProcessor m_downloadHandler; 
	
	public DownloadScheduleFileController(FileHandler downloadFileHandler, 
			DownloadProcessor downloadHandler) {
		
		m_downloadFileHandler = downloadFileHandler; 
		m_downloadHandler = downloadHandler;
	}

	 
	public void downloadScheduleFile(GenericScheduleFileParam  downloadScheduleFilePram, 
										HttpServletResponse downloadRequest) {
		
		// first check , if the file does exist at all 
		
 
		// we have to translate the requested name to the real existing Source on the machine ...
		
		final String physicalNameOfSource = m_downloadFileHandler.getFileAssetsManager()
																	.getFileNameTranslator()
																		.translateFileName(downloadScheduleFilePram.getUploadedFileName()
																		  .getResolvedUploadedFileName());
		
		try {
		 		 
	     	processDownload( m_downloadFileHandler.getFileAssetsManager().getPathManager().getPathOfFile(physicalNameOfSource ).toFile(),
						downloadRequest);
			
		}
		catch(InternalError requestedFileINotThere) {
			
			//@Fixme put smt to handle missing files 
			
			AssetFileIsMissing missingAssetFileError = new AssetFileIsMissing("The requested File is missing");
			
			missingAssetFileError.setAssetFileName(  downloadScheduleFilePram.getUploadedFileName().toString());
			
			
			throw missingAssetFileError;
		
		}
		
	
	}
 
	
	private void processDownload(File sourceFile, HttpServletResponse fileToBuild) {
		
		 try {
		      // get your file as InputStream
		      InputStream contentToBuildDownload = Files.asByteSource(sourceFile).openStream();
		      // copy it to response's OutputStream
		      org.apache.commons.io.IOUtils.copy(contentToBuildDownload, fileToBuild.getOutputStream());
		     
		      m_downloadHandler.processDownload(sourceFile,  fileToBuild);
		       
		      // set correct type 
		      
		      // set downloadable 
		      
		      fileToBuild.flushBuffer();
		   
		 } catch (IOException ex) {
		      
		 
		      throw new InternalError("Die Datei " + sourceFile.getName() + " kann nicht ausgeliefert werden ");
		    
		 }
	}

}
