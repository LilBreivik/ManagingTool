package resources.components.filehandler.XLS.general;
   
 
import resources.components.filehandler.general.FileRepositoryHandler;
import resources.components.filereader.XLS.XLSFileReader;
import resources.components.filereader.utils.FileNameTranslator;
import resources.database.repository.FilesRepository;
import resources.utils.pathmanager.PathManager;
 
 
public class GeneralXLSFileHandler
										extends FileRepositoryHandler  {
 
	
	public GeneralXLSFileHandler(FilesRepository fileRepository,
									PathManager pathManager,
											FileNameTranslator fileNameTranslator, 
													XLSFileReader fileReader) {
	 
		super( pathManager , 
					fileRepository,  
							fileNameTranslator, 
								fileReader);
		     
	} 
	  
	
	@Override
	public <ReadFileType> ReadFileType readFile(String fileName) {

		return super.readFile(p_PathManager.buildFileFromFileName(fileName).getName());
	}
	
	 
}
