package core.backend.REST.fileasset.download.task;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service; 
import core.backend.REST.general.task.response.nonrequest.AbstractFileHandlerResponseTaskWithoutRequestImpl; 
import core.backend.utils.download.handler.DownloadHandler;
import resources.components.filehandler.general.GeneralFileHandler;
import resources.error.InternalError;
 
@Service 
public class DownloadTemplateFileTask 
					extends   AbstractFileHandlerResponseTaskWithoutRequestImpl< HttpServletResponse, GeneralFileHandler>{

	@Autowired 
	@Qualifier("provide DownloadHandler for GeneralCourseScheduleTemplateFile")  
	protected   DownloadHandler p_downloadHandler; 
	 

	protected  HttpServletResponse p_httpServletResponse; 
	 
		
	public void workOnTask() {
			   		 
			if(this.p_httpServletResponse != null) {
							 
				 p_downloadHandler.processDownload(p_httpServletResponse);
			}
			else {			
				InternalError internalError = new InternalError("Download Response was not defined");
								
				throw internalError;	
			}						
		}

	public void setHTTPResponse(HttpServletResponse response){
	 
			p_httpServletResponse = response; 
	}

	@Override
	public void setFileHandler(GeneralFileHandler fileHandler) {
	
		p_fileHandler = fileHandler; 
	}
}
