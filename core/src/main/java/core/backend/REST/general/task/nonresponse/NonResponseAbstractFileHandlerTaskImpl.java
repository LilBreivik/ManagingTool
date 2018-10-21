package core.backend.REST.general.task.nonresponse;

import core.backend.REST.general.request.MasterRESTRequest;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;
import resources.components.filehandler.general.GeneralFileHandler;

public class NonResponseAbstractFileHandlerTaskImpl<FileHandler extends GeneralFileHandler,
											RequestParameter extends  MasterRESTRequest >

										extends NonResponseAbstractTaskImpl< RequestParameter>{

	
	protected FileHandler p_fileHandler;
	
	public NonResponseAbstractFileHandlerTaskImpl(FileHandler fileHandler) {
		
		p_fileHandler = fileHandler; 
	}
}
