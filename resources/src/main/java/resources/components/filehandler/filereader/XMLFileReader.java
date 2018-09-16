package resources.components.filehandler.filereader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import resources.components.elements.XML.XMLElement;
import resources.fileconnection.XMLFileConnection;
 
public class XMLFileReader {
	 
		private XMLFileConnection m_conn; 
		 
		private XMLFileReaderManager m_manager; 
		
		public XMLFileReader(XMLFileConnection connectionToXMLFile, 
									XMLFileReaderManager manager) {
			
			m_conn = connectionToXMLFile; 
			m_manager = manager;
		}

		
		/**
		 * Function that reads the configured 
		 * XML File, and converts it to the expected 
		 * POJO
		 * */
		
		public  Object readFile(){
			
			Collection<XMLElement> needdElements = new ArrayList<>();
			
			try {
				// courses
				System.out.println("---> " + m_manager.getRootNode().getM_elementValue());
				needdElements = getElementsByName(m_manager.getRootNode().getM_elementValue());
			}
			catch (XMLStreamException | FileNotFoundException e) {
					
				e.printStackTrace();
				// @FIXME: Own Exception.....
				//throw new Exception();
			}
			 
			return m_manager.convertToPOJO(needdElements);
		}
		 
		 
		/**
		 * retruns a list with all elements, that contain 
		 * the given ElementName. 
		 * 
		 * Additionally , it will return all elements in between, 
		 * if the element name, belongs to a start node.
		 * 
		 * @throws XMLStreamException
		 * @throws FileNotFoundException 
		 */
		
		
		public Collection<XMLElement> getElementsByName(String ElementName) throws XMLStreamException, FileNotFoundException{
		
			int elementCtr = 0;
			
			boolean recordNames = false; 
			
			Map<Integer, String> elementNamesIndex = new HashMap<>();
			
			Map<Integer, String> elementValuesIndex = new HashMap<>();

			XMLEventReader eventReader = m_conn.getM_xmlEventReader();
		 	
			while(eventReader.hasNext()){
	           
				XMLEvent event = eventReader.nextEvent();
	        
				switch(event.getEventType()){
	            
				  case XMLStreamConstants.START_ELEMENT:
	              
					  StartElement startElement = event.asStartElement();
	                 
					  String qStartName = startElement.getName().getLocalPart();
	                  
					  if (qStartName.equalsIgnoreCase(ElementName.toLowerCase())) {
	                 
						  recordNames = !recordNames; 
					
	                  } 
					 	
					  if(recordNames) {
				
						  // do record the character stream ....
						  elementNamesIndex.put(elementCtr, qStartName);  
						  elementCtr += 1; 
					  }
					  
	                  break;
	                  
	               case XMLStreamConstants.CHARACTERS:
	            
	            	  Characters characters = event.asCharacters();
	              
	                  if(elementNamesIndex.keySet().contains(elementCtr - 1)) {
	                	  
	                	  elementValuesIndex.put(elementCtr - 1, characters.getData()); 
	                	  
	                  }
	                  elementCtr += 1; 
	                  break;
	                  
	               case XMLStreamConstants.END_ELEMENT:
	                
	            	  EndElement endElement = event.asEndElement();
	           
	                  String qEndName =  endElement.getName().getLocalPart();
	                  
	                
	                  if(( qEndName.equalsIgnoreCase(ElementName.toLowerCase()))){
	                	  elementCtr = 0;
	                  }
	                  break;
	            }
				
				 
	         }
			 
			
			return transformToXMLElementList(elementNamesIndex ,  elementValuesIndex);
			
		}

		
		private Collection<XMLElement> transformToXMLElementList(Map<Integer, String> elementNamesIndex,
				Map<Integer, String> elementValuesIndex) {
			

			List<XMLElement> transformedXMLElements = new ArrayList<XMLElement>();
			 
			
			List<Integer> indexes = Arrays.asList(elementNamesIndex.keySet().toArray( new Integer[elementNamesIndex.keySet().size()]));
					
			Collections.sort( indexes);
			
			for(int index :  indexes) {
				
				String elementName = elementNamesIndex.get(index);
				 
				String elementValue = elementValuesIndex.get(index);
			  
				transformedXMLElements.add( new XMLElement(elementName, elementValue));
				
			}
			
			return transformedXMLElements; 
			
		}

	 
		
		
	}

