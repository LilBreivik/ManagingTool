package core.backend.REST.fileasset.download.task;
 
import core.backend.REST.fileasset.download.parameter.DownloadFileParameter; 
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.response.AbstractFileHandlerResponseTaskImpl;
import core.backend.utils.download.handler.DownloadHandler; 
import javax.servlet.http.HttpServletResponse; 
import resources.components.filehandler.general.GeneralFileHandler;
import resources.error.FileIsMissingError;
import resources.error.InternalError;

public class DownloadFileTask 
				extends AbstractFileHandlerResponseTaskImpl< DownloadFileParameter, 
																HttpServletResponse,
																	GeneralFileHandler>{
	
	  
	protected   DownloadHandler p_downloadHandler; 

	protected  HttpServletResponse p_httpServletResponse; 
	
	public DownloadFileTask(GeneralFileHandler downloadGeneralFileHandler, 
									DownloadHandler downloadHandler) {
	
		p_downloadHandler =  downloadHandler; 

		setFileHandler(downloadGeneralFileHandler);
	}
	

	@Override
	public void setFileHandler(GeneralFileHandler fileHandler) {
	
		p_fileHandler = fileHandler; 
	}
	
	
	
	@Override
	public void workOnTask() {
		   		 
		if(this.p_httpServletResponse != null) {
						 
			 p_downloadHandler.processDownload(p_httpServletResponse);
		}
		else {			
			InternalError internalError = new InternalError("Download Response was not defined");
							
			throw internalError;	
		}						
	}
	
	@Override
	public void workOnTask(DownloadFileParameter parameter) {
		  
		p_httpServletResponse = parameter.getDownloadResponse();
		 
		final String resolvedFileName = parameter.getFileNameResolver().getResolvedFileName(); 
		
		
		// first check , if the file does exist at all 
		
		// we  have to translate the requested name to the real existing Source on the machine ...
		  
		
		if( p_fileHandler .checkIfFileDoesExist(resolvedFileName))  {
			
			  if(this.p_httpServletResponse != null) {
					
				  p_downloadHandler.processDownload( p_fileHandler.determinePhysicallyStoredFile(resolvedFileName),
										p_httpServletResponse);
			  }
		  	  else {			
				
					InternalError internalError = new InternalError("Download Response was not defined");
					
					throw internalError;
				}		 
		}
			
		else {
				
			FileIsMissingError missingFileError = new FileIsMissingError("Die Datei " + resolvedFileName+ " ist nicht vorhanden");
				
			missingFileError.missingFileName = resolvedFileName; 
				
	    	missingFileError.missingFileCause = "heruntergeladen"; 
				
			throw missingFileError;
		}		
				 
	}
 
	
    public void setHTTPResponse(HttpServletResponse response){
		
		 
		p_httpServletResponse = response; 
	}

    @Override
    public SuccessResponse<HttpServletResponse> getResultsFromTask() {
    
    	return  new SuccessResponse<HttpServletResponse>(p_httpServletResponse) ;
    	 
    }
 
 

}


 