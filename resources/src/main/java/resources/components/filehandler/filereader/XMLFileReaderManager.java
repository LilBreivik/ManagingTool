package resources.components.filehandler.filereader;

import java.util.Collection;

import resources.components.elements.XML.XMLElement;
 
public interface XMLFileReaderManager {
 
	
	public XMLElement getRootNode();

	Object convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO);
	
  
}
