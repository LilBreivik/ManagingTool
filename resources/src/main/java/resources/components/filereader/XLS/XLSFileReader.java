package resources.components.filereader.XLS;
  
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
 
import resources.components.elements.POJO.Persistence.AllLecturesPOJO; 
import resources.components.filereader.general.GeneralFileReader;  
import resources.constraint.impl.ConstraintImpl;  
import resources.fileconnection.XLSFileConnection;
 

/**
 * Class that handles the reading process
 * 
 * of xls Files
 * */

public abstract class XLSFileReader
						extends GeneralFileReader<XLSFileConnection, AllLecturesPOJO>  {
   
	 
	protected  ConstraintImpl  p_constraint;
	
	protected  Map<Integer, String> p_ColumnNameIndexes; 
	 
	
	/**
	 * Constructor of XLSFileReader
	 * 
	 * @param (XLSFileConnection), conn to a certain xls file
	 * @param (LectureConstraint), constraints, that are need to parse the read conents of a certain xls file 
	 * */
	
	public XLSFileReader(XLSFileConnection conn, 
								ConstraintImpl  lectureConstraint) 
	{
	
		super(conn);
		p_constraint = lectureConstraint; 
	}
	
  
	  
	/*
	 * Creates a Mapping between the 
	 * names and an integer, to describe the index ... 
	 * 
	 * (needed to do it maunally , cause the API 
	 * doe snot provide a way to handle the "columnames")
	 * 
	 * @throw (IOException)
	 * */
	
	protected  void  determineColumnNamesIndex() throws IOException {
		 
		try {
			
			Sheet sheet =  p_Connection.getSheet();
			
			Row columnNames =  sheet.getRow(0);
			 
			p_ColumnNameIndexes = new HashMap<Integer, String>();
			
			for(int ctr = 0; ctr < columnNames.getLastCellNum(); ctr += 1 ) 
			{
				 Cell cell = columnNames.getCell(ctr);
				 
				 p_ColumnNameIndexes .put(ctr, cell.getStringCellValue());
			}	
		}
		catch(Exception ex) {
			
			throw new IOException ("Incorrect XLS File");
		}
	}

}
