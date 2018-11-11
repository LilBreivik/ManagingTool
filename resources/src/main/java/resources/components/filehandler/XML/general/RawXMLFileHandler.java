package resources.components.filehandler.XML.general;
 

import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.filehandler.general.RawFileHandler; 
import resources.components.filereader.XML.reader.XMLFileReader;
import resources.components.filereader.XML.readermanager.XMLFileReaderManager;
import resources.utils.pathmanager.PathManager;
 
public class RawXMLFileHandler<POJOClass> 
									extends RawFileHandler  {

	 
	public RawXMLFileHandler(PathManager pathManagerToJSONFiles,
			XMLFileReader<POJOClass> xmlFileReader) { 
		
		super(pathManagerToJSONFiles,  xmlFileReader );
		
	}
 
	
	@Override
	public <ReadObjectType> ReadObjectType readFile(String fileName) {
		
		return super.readFile(fileName);
	}
}
