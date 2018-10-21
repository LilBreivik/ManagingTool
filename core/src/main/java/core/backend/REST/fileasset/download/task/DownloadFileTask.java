package core.backend.REST.fileasset.download.task;
 
import core.backend.REST.fileasset.download.parameter.DownloadFileParameter;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.response.AbstractFileHandlerTaskImpl;
import core.backend.utils.download.handler.DownloadHandler; 
import javax.servlet.http.HttpServletResponse; 
import resources.components.filehandler.general.GeneralFileHandler;
import resources.error.FileIsMissingError;
import resources.error.InternalError;

public class DownloadFileTask 
								extends AbstractFileHandlerTaskImpl<GeneralFileHandler, DownloadFileParameter, HttpServletResponse>{
	
	 

	protected  DownloadHandler p_downloadHandler; 

	protected  HttpServletResponse p_httpServletResponse; 
	
	public DownloadFileTask(GeneralFileHandler downloadGeneralFileHandler, 
									DownloadHandler downloadHandler) {
		
		super(downloadGeneralFileHandler);
		
		p_downloadHandler =  downloadHandler; 
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
		 
		// first check , if the file does exist at all 
		
		// we have to translate the requested name to the real existing Source on the machine ...
		 
		
		if( p_fileHandler .checkIfFileDoesExist(parameter.getFileNameResolver()
				.getResolvedFileName()))  {
			
			  if(this.p_httpServletResponse != null) {
					
				  p_downloadHandler.processDownload( p_fileHandler.determinePhysicallyStoredFile(parameter.getFileNameResolver().getResolvedFileName()),
										p_httpServletResponse);
			  }
		  	  else {			
				
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
 
	
    public void setHTTPResponse(HttpServletResponse response){
		
		 
		p_httpServletResponse = response; 
	}

    @Override
    public SuccessResponse<HttpServletResponse> getResultsFromTask() {
    
    	return  new SuccessResponse<HttpServletResponse>(p_httpServletResponse) ;
    	 
    }
 
}


 