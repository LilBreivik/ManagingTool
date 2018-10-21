package resources.components.filehandler.XML.general;
 

public interface IXMLFileHandler<POJOClass>  {

	/**
	 * Method to read a 
	 * certain XML-File per FileName 
	 * 
	 * @param (String) fileName, that describes the JSON File, that shall be read 
	 * @return (POJOClass)
	 * */
	
	public POJOClass readXMLFile(String fileName);
	
}
