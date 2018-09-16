package resources.components.filehandler.XML;
 
import resources.components.filehandler.filereader.XMLFileReader; 

@FunctionalInterface
public interface IXMLFileReaderFactory {

	public XMLFileReader getXMLFileReader(String fileName);
}
