package resources.fileconnection;

import java.io.File; 
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook; 
import org.apache.poi.ss.usermodel.WorkbookFactory;

import resources.error.ConnectionError;
import resources.error.parameter.FileAssetNotCommitedError;
 

public class XLSFileConnection {

	private POIFSFileSystem fs = null;
    private Workbook wb = null;
    private Sheet sheet = null; 
    private File m_XMLFile; 
    
	public XLSFileConnection(File XLS_File) throws ConnectionError {
		
		try {
			 
			setM_XMLFile(XLS_File);
			
			setWb(WorkbookFactory.create(XLS_File)); 
			
			setSheet(this.getWb());
		}
		catch (NullPointerException noFileCommitted) {
			 
			throw new FileAssetNotCommitedError ();
		}
		catch(Exception ioe) {
		   
			throw new ConnectionError("Cannot build connection to the XLS File " + XLS_File.getName() + " at " + XLS_File.getAbsolutePath());
		}
	}
 

	public POIFSFileSystem getFs() {
		return fs;
	}
 
	
	public Workbook getWb() {
		return this.wb;
	}

	public void  setWb(Workbook wb) {
		
		this.wb = wb;
	}
 
	public Sheet getSheet() {
		return this.sheet;
	}

	private void setSheet(Workbook wb) {
	
		this.sheet = wb.getSheetAt(0);
	}


	public File getM_XMLFile() {
		return m_XMLFile;
	}


	public void setM_XMLFile(File m_XMLFile) {
		this.m_XMLFile = m_XMLFile;
	}
}
