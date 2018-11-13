package core.backend.REST.general.task.response.nonrequest;
     
import resources.components.filehandler.general.GeneralFileHandler;
 
public abstract class AbstractFileHandlerResponseTaskWithoutRequestImpl< ResponseType,  FileHandler extends GeneralFileHandler>

										extends  AbstractResponseTaskWithoutRequestImpl{
 
   
	protected FileHandler p_fileHandler;
	 
	
	public abstract void setFileHandler(FileHandler fileHandler);
}
 