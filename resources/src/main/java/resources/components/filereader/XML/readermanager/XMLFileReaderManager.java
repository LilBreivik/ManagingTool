package resources.components.filereader.XML.readermanager;

import java.util.Collection;

import resources.components.elements.XML.XMLElement;
 
public interface XMLFileReaderManager<POJOClass> {
  
	public XMLElement getRootNode();

	POJOClass convertToPOJO(Collection<XMLElement> elementsToBeConverteToPOJO);
}
