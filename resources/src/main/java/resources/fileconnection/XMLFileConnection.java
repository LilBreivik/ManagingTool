package resources.fileconnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import resources.error.ConnectionError;
import resources.error.parameter.fileasset.FileAssetNotCommitedError; 
 
@Component
public class XMLFileConnection 
					extends GeneralFileConnection{
 

    private XMLInputFactory m_factory;
    
    private XMLEventReader m_xmlEventReader; 
  
        
    
	/**  we need to keep track of the 
	 *   first event, to create a possibility to read several times 
	 *   via the same connection instance .. 
     * @throws XMLStreamException 
     * @throws FileNotFoundException 
	 */
    
	public XMLEventReader getM_xmlEventReader() throws FileNotFoundException, XMLStreamException {
		
		return m_factory.createXMLEventReader(new FileReader(getConnectedFile().getAbsolutePath()));	
	}

	@Override
	public void buildConnectionToAFile(File file) {
	   
		try {
			 
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		
    		factory.setValidating(false);
    		
    		factory.setNamespaceAware(true);

    		DocumentBuilder builder;	

			builder = factory.newDocumentBuilder();
				
			Document document = builder.parse(new InputSource(file .getAbsolutePath()));
	
			m_factory = XMLInputFactory.newInstance(); 	
			 
			p_connectedFile = file; 
			
		}   
		catch (NullPointerException noFileCommitted) {
			 
			throw new FileAssetNotCommitedError ();
		}
		catch (SAXException | ParserConfigurationException | IOException e1) {
			  
		 
			throw new ConnectionError("Cannot build connection to the XML File " + file.getName() + " at " + file.getAbsolutePath());
		}
	} 
}
