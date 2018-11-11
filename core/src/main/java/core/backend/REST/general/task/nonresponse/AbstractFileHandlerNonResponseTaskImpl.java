package core.backend.REST.general.task.nonresponse;
 
import core.backend.REST.general.request.RESTRequest;
import resources.components.filehandler.general.GeneralFileHandler;

public class AbstractFileHandlerNonResponseTaskImpl <RequestParameter  extends RESTRequest, 
														FileHandler extends GeneralFileHandler >

								extends  AbstractNonResponseTaskImpl< RequestParameter  >{


		protected FileHandler p_fileHandler;
			
		public AbstractFileHandlerNonResponseTaskImpl(FileHandler fileHandler) {

			p_fileHandler = fileHandler; 
		}

}
