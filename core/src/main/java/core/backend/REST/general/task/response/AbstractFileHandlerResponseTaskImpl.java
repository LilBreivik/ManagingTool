package core.backend.REST.general.task.response;
   
import core.backend.REST.general.request.RESTRequest;
import resources.components.filehandler.general.GeneralFileHandler;

public abstract class AbstractFileHandlerResponseTaskImpl<RequestParameter  extends RESTRequest ,
													ResponseType,
													   FileHandler extends GeneralFileHandler>

										extends AbstractResponseTaskImpl< RequestParameter, ResponseType >{
 
  
	protected FileHandler p_fileHandler;
	 
	
	public abstract void setFileHandler(FileHandler fileHandler);
}
 