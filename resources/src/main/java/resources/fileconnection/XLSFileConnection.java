package resources.fileconnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook; 
import org.apache.poi.ss.usermodel.WorkbookFactory;
import resources.error.ConnectionError;
import resources.error.parameter.fileasset.FileAssetNotCommitedError;
 
public class XLSFileConnection 
									extends GeneralFileConnection{

	private Workbook m_wb;
    private Sheet m_sheet;
	
	public XLSFileConnection(Path path) {
		super(path);
	}
 
	@Override
	public void buildConnectionToAFile(File file) {
		
		try {
			
			m_wb  = WorkbookFactory.create( file);
			 
			m_sheet = m_wb.getSheetAt(0);
			
			setConnectedFile(file);
		 
		}
		catch (NullPointerException noFileCommitted) {
			  
			throw new FileAssetNotCommitedError ();
		} 
		catch (InvalidFormatException e) {
			
			throw new ConnectionError("Cannot build connection to the invalid XLS File " +  file.getName() + " at " +  file.getAbsolutePath());
			
		} catch (IOException e) {
			
			throw new ConnectionError("Cannot build connection to the XLS File " +  file.getName() + " at " +  file.getAbsolutePath());
		}
	} 
    
	public Workbook getWb() {
		
		return m_wb;
	}
	
	public Sheet getSheet() {
		
		return m_sheet;
	}
}
