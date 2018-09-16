package resources.components.filehandler.filereader;
 
import java.util.HashMap;
import java.util.Iterator; 
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.constraint.ConstraintRow;
import resources.constraint.LectureConstraint;
import resources.error.ConstraintViolationError;
import resources.fileconnection.XLSFileConnection;
 
public class XLSFileReader {

	private XLSFileConnection m_conn; 
 
	private LectureConstraint m_lectureConstraint;
	
	private Map<Integer, String> m_ColumnNameIndexes; 
	
	public XLSFileReader(XLSFileConnection conn, 
							LectureConstraint lectureConstraint) 
	{
		m_conn = conn; 
		m_lectureConstraint = lectureConstraint;
		determineColumnNamesIndex();
	}
	 

	/**
	 * Creates a Mapping between the 
	 * names and an integer, to describe the index ... 
	 * 
	 * (needed to do it maunally , cause the API 
	 * doe snot provide a way to handle the "columnames")
	 * */
	
	private void  determineColumnNamesIndex() {
		
		Sheet sheet =  m_conn.getSheet();
		
		Row columnNames =  sheet.getRow(0);
		 
		m_ColumnNameIndexes = new HashMap<Integer, String>();
		
		for(int ctr = 0; ctr < columnNames.getLastCellNum(); ctr += 1 ) 
		{
			 Cell cell = columnNames.getCell(ctr);
			 
			 m_ColumnNameIndexes .put(ctr, cell.getStringCellValue());
		}
		 
	}
	
	  
	
	public Object readFile() {
		  
		//List<String> neededConstraintsList = new ArrayList<String>(neededColumnConstraints.getConstraints());
		
		AllLecturesPOJO allLectures = new AllLecturesPOJO();
		 	
	    Iterator<Row> iterator =  m_conn.getSheet().iterator();
		
	    // We will call hasNext at thi point to "jump over the columnName"
        
        // We are just interested in the contents 
	    
	    iterator.next();
	     
	    
	    // At first we will generate rows, that 
	    // contain the needed lectures with its information
	    
	    while (iterator.hasNext()) {
         	
	    	// We will just collect ROws from the 
	    	// xls file that do not violate any constraint.... 
	    
	    	
	    	/**
	    	 * @fixme:  do not forget to define the constraint violations in the other 
	    	 * constraints .... .
	    	 * */
			try {
				
				allLectures.addNewLecture(m_lectureConstraint.applyLectureConstraints(new ConstraintRow(iterator, m_ColumnNameIndexes )));
						
			} catch (ConstraintViolationError e) {
				 
				e.printStackTrace();
			}		    		 
			  
	    	 
        }
	     
	    return  allLectures;  
	}

 
	
 
}
