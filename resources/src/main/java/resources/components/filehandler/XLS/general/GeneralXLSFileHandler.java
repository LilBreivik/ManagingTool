package resources.components.filehandler.XLS.general;
   
 
import resources.components.elements.POJO.Persistence.AllLecturesPOJO; 
import resources.components.filehandler.general.FileRepositoryHandler;
import resources.components.filereader.XLS.XLSFileReader;
import resources.components.filereader.utils.FileNameTranslator;
import resources.constraint.LectureConstraint;
import resources.database.repository.FilesRepository;
import resources.fileconnection.XLSFileConnection;
import resources.utils.pathmanager.PathManager;
 
 
public class GeneralXLSFileHandler
										extends FileRepositoryHandler  {

	private LectureConstraint m_lectureConstraint; 
	
	
	public GeneralXLSFileHandler(FilesRepository fileRepository,
									LectureConstraint lectureConstraint, 
								      	PathManager pathManager,
											FileNameTranslator fileNameTranslator) {
	 
		super( pathManager , 
					fileRepository,  
							fileNameTranslator);
		  
		
		
		m_lectureConstraint = lectureConstraint; 
	} 
	 
	/**
	 * Method to read a 
	 * certain JSON-File per FileName 
	 * 
	 * @param (String) fileName, that describes the JSON File, that shall be read 
	 * @return ( AllLecturesPOJO) pojo, that describes the contents of the  AllLectures
	 * */
	
	
	public AllLecturesPOJO readXLSFile(String fileName) {
	 	 
		System.out.println(p_PathManager.getPathToOperateOn());
		
		System.out.println(p_PathManager.buildFileFromFileName(fileName));
		
		
		
		return readFile( new XLSFileReader( 
							new XLSFileConnection(p_PathManager.getPathToOperateOn()), m_lectureConstraint), 
								p_PathManager.buildFileFromFileName(fileName).getName());
		
	}
	
}
