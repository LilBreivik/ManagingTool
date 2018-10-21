package core.backend.REST.general.task.response;

import core.backend.REST.general.request.MasterRESTRequest;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;
import resources.components.filehandler.general.GeneralFileHandler;

public class AbstractFileHandlerTaskImpl<FileHandler extends GeneralFileHandler,
											RequestParameter extends  MasterRESTRequest,
													ResponseType>

										extends GeneralAbstractTaskImpl< RequestParameter,	ResponseType>{

	
	protected FileHandler p_fileHandler;
	
	public AbstractFileHandlerTaskImpl(FileHandler fileHandler) {
		
		p_fileHandler = fileHandler; 
	}
}
