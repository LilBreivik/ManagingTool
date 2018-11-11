package resources.components.filehandler.XML.general;
 
import java.util.Collection;
import resources.components.elements.XML.XMLElement;
import resources.components.filereader.XML.readermanager.XMLFileReaderManager;

public abstract class GeneralXMLFileReaderManager<POJOClass>
											implements XMLFileReaderManager<POJOClass>{
  
	@Override
	public XMLElement getRootNode() {
	
		throw new UnsupportedOperationException("shall be implemented in the inherited class");
	}

	@Override
	public POJOClass convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO) {
		
		throw new UnsupportedOperationException("shall be implemented in the inherited class");
	}
}
