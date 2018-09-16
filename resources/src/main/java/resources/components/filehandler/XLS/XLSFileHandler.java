package resources.components.filehandler.XLS;
   
import java.io.File; 
import resources.components.filehandler.FileHandler; 
import resources.components.filehandler.assetsmanager.FileAssetsManager;
import resources.components.filehandler.filereader.FileNameTranslator;
import resources.components.filehandler.filereader.FileReadingPlan;
import resources.components.filehandler.filereader.XLSFileReader;
import resources.constraint.LectureConstraint; 
import resources.error.ConnectionError;
import resources.fileconnection.XLSFileConnection;
 
 
public class XLSFileHandler extends FileHandler {
 
	private LectureConstraint m_lectureConstraint; 
  
	public XLSFileHandler(LectureConstraint lectureConstraint, FileAssetsManager fileassetsmanager) {
	
		super(fileassetsmanager);
		
		m_lectureConstraint = lectureConstraint; 
		    
	}
	
  
	@Override
	public Object readFile(String fileName) {
		
		return p_fileAssetsManager.readFile(new  FileReadingPlan() {

			@Override
			public Object readFile(FileNameTranslator translator)  {
				
			try {
				
				return new XLSFileReader(new XLSFileConnection(new File(p_fileAssetsManager.getPathManager().getPathToOperateOn().toFile(), 
		    			
						translator.translateFileName(fileName))) , 
		    			m_lectureConstraint).readFile() ;
			}
			catch(ConnectionError ex) {
				
				return null; 
			}
		 
		}
			 
		});
			
	}
	
	public XLSFileReader getXLSFileReader(String fileName) {
		
		try {
		 
			return new XLSFileReader(new XLSFileConnection(new File(p_fileAssetsManager.getPathManager().getPathToOperateOn().toFile(), 
	    			fileName)) , 
	    			m_lectureConstraint);
		}
		catch(ConnectionError ex) {
			
			return null; 
		}
		
	}

 
	 
}
