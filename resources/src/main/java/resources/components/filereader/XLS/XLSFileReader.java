package resources.components.filereader.XLS;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.util.HashMap;
import java.util.Iterator; 
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.filereader.general.GeneralFileReader; 
import resources.constraint.ConstraintRow;
import resources.constraint.LectureConstraint;
import resources.error.ConstraintViolationError;
import resources.fileconnection.XLSFileConnection;
 

/**
 * Class that handles the reading process
 * 
 * of xls Files
 * */

public class XLSFileReader
						extends GeneralFileReader<XLSFileConnection, AllLecturesPOJO>  {
  
	 
	private LectureConstraint m_lectureConstraint;
	
	private Map<Integer, String> m_ColumnNameIndexes; 
	
	
	/**
	 * Constructor of XLSFileReader
	 * 
	 * @param (XLSFileConnection), conn to a certain xls file
	 * @param (LectureConstraint), constraints, that are need to parse the read conents of a certain xls file 
	 * */
	
	public XLSFileReader(XLSFileConnection conn, 
							LectureConstraint lectureConstraint) 
	{
	
		super(conn);
		m_lectureConstraint = lectureConstraint; 
	}
	
 
	public Map<Integer, String> getColumnNameIndexes(){
		
		return m_ColumnNameIndexes; 
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
	
	private void  determineColumnNamesIndex() throws IOException {
		 
		try {
			
			Sheet sheet =  p_Connection.getSheet();
			
			Row columnNames =  sheet.getRow(0);
			 
			m_ColumnNameIndexes = new HashMap<Integer, String>();
			
			for(int ctr = 0; ctr < columnNames.getLastCellNum(); ctr += 1 ) 
			{
				 Cell cell = columnNames.getCell(ctr);
				 
				 m_ColumnNameIndexes .put(ctr, cell.getStringCellValue());
			}	
		}
		catch(Exception ex) {
			
			throw new IOException ("Incorrect XLS File");
		}
	}
 
	@Override
	public AllLecturesPOJO readFile(File file) throws FileNotFoundException, ClassCastException, IOException {
	  
		// establish Connection to the needed file 
		
		p_Connection.buildConnectionToAFile(file);
		
        determineColumnNamesIndex();
        
        AllLecturesPOJO allLectures = new AllLecturesPOJO();
	 	
		Iterator<Row> iterator =   p_Connection.getSheet().iterator();
		
		// We will call hasNext at thi point to "jump over the columnName"
        
		// We are just interested in the contents 
			    
		iterator.next();
		
		// At first we will generate rows, that 
	    // contain the needed lectures with its information
			    
		while (iterator.hasNext()) {
         	
	    	// We will just collect ROws from the 
	    	// xls file that do not violate any constraint.... 
	    	
			try {
				
				 allLectures.addNewLecture(m_lectureConstraint.applyLectureConstraints(new ConstraintRow(iterator, m_ColumnNameIndexes )));
			}
			catch(ConstraintViolationError error) {
			 
				// if a constraint got violated, we will track the certain violation 
				// and skip it further error handlingk to collect the nect lecture
				
				System.out.println("constraint violated " + error);
			}
			  
        }
	     
	    return  allLectures; 
	}
}
