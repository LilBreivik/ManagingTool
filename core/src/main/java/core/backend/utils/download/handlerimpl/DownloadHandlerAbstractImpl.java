package core.backend.utils.download.handlerimpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.google.common.io.Files;

import core.backend.utils.download.handler.DownloadHandler;

public abstract class DownloadHandlerAbstractImpl 
									implements DownloadHandler{

	@Override
	public void processDownload(File sourceFile, HttpServletResponse fileToDownload) {
		
		 try {  
		    // get your file as InputStream
		    InputStream contentToBuildDownload = Files.asByteSource(sourceFile).openStream();
		    
		    // copy it to response's OutputStream
		    org.apache.commons.io.IOUtils.copy(contentToBuildDownload, fileToDownload.getOutputStream());
		    
		 } catch (IOException ex) {
		       
		      throw new InternalError("Die Datei " + sourceFile.getName() + " kann nicht ausgeliefert werden ");
		 }
		
	}

	@Override
	public void processDownload(HttpServletResponse fileToDownload) {
	
		throw new UnsupportedOperationException("shall be implemented in hte inherit");
	}

}
