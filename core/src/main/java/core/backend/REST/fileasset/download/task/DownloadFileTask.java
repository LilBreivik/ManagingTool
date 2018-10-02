package core.backend.REST.fileasset.download.task;
 
import core.backend.REST.fileasset.download.parameter.DownloadFileParameter;
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.general.task.AbstractTaskImpl;
import core.backend.utils.download.DownloadProcessor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import com.google.common.io.Files;  
import resources.components.filehandler.FileHandler;
import resources.error.FileIsMissingError;
import resources.error.InternalError;

public class DownloadFileTask 
								extends AbstractTaskImpl<DownloadFileParameter, HttpServletResponse>{
	
	
	protected FileHandler p_downloadFileHandler;

	protected  DownloadProcessor p_downloadHandler; 

	protected  HttpServletResponse p_httpServletResponse; 
	
	public DownloadFileTask(FileHandler downloadFileHandler, 
									DownloadProcessor downloadHandler) {
		
		p_downloadFileHandler = downloadFileHandler; 
		p_downloadHandler =  downloadHandler; 
	}
	
	
	@Override
	public void workOnTask(DownloadFileParameter parameter) {
		  
		p_httpServletResponse = parameter.getDownloadResponse();
		 
		// first check , if the file does exist at all 
		
		// we have to translate the requested name to the real existing Source on the machine ...
		
		try {
			
			final String physicalNameOfSource = p_downloadFileHandler.getFileAssetsManager()
					.getFileNameTranslator()
						.translateFileName(parameter.getFileNameResolver()
						  .getResolvedFileName());
			
			if(p_downloadFileHandler.getFileAssetsManager()
										.getPathManager()
											.getPathOfFile(physicalNameOfSource )
												.toFile().exists()) 
			{
				if(this.p_httpServletResponse != null) {
				
					processDownload( p_downloadFileHandler.getFileAssetsManager().getPathManager().getPathOfFile(physicalNameOfSource ).toFile(),
					p_httpServletResponse);
				}
				else 
				{			
					InternalError internalError = new InternalError("Download Response was not defined");
					
					throw internalError;
				}		
			}
			
			else {
			
				FileIsMissingError missingFileError = new FileIsMissingError("Die Datei " + parameter.getFileNameResolver().getResolvedFileName().toString() + " ist nicht vorhanden");
				
				missingFileError.missingFileName =  parameter.getFileNameResolver().getResolvedFileName().toString(); 
				
				missingFileError.missingFileCause = "heruntergeladen"; 
				
				throw missingFileError;
			}
						
		}
		catch(NullPointerException fileWasDeletedBefore) {
			
			// We cannot build a path to the real existing ressource, if it ws deleted before 
			
			throw new InternalError("Die Datei " + parameter.getFileNameResolver().getResolvedFileName().toString() + " wurde schon gelöscht");
			
		}
	}

	
	protected void processDownload(File sourceFile, HttpServletResponse fileToBuild) {
		
		 try {
		      // get your file as InputStream
		      InputStream contentToBuildDownload = Files.asByteSource(sourceFile).openStream();
		      // copy it to response's OutputStream
		      org.apache.commons.io.IOUtils.copy(contentToBuildDownload, fileToBuild.getOutputStream());
		     
		      p_downloadHandler.processDownload(sourceFile,  fileToBuild);
		       
		      // set correct type 
		      
		      // set downloadable 
		      
		    //  fileToBuild.flushBuffer();
		   
		 } catch (IOException ex) {
		      
		 
		      throw new InternalError("Die Datei " + sourceFile.getName() + " kann nicht ausgeliefert werden ");
		    
		 }
	}

    @Override
    public SuccessResponse<HttpServletResponse> getResultsFromTask() {
    
    	return  new SuccessResponse<HttpServletResponse>(p_httpServletResponse) ;
    	 
    }
 
}


 