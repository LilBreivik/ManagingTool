package resources.constraint;
 
import java.util.Iterator; 
import java.util.Map;
 

import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row;

import resources.components.filehandler.XLS.DisAssembledXLSCell;
import resources.components.filehandler.XLS.DisAssembledXLSRow;
 
public class ConstraintRow extends DisAssembledXLSRow{
	 
	private Map<Integer, String> m_ColumnNameIndexes;
	 
	public ConstraintRow(Iterator<Row> rowThatShallBeConstrained, 
									Map<Integer, String> ColumnNameIndexes)
	{
		
		m_ColumnNameIndexes = ColumnNameIndexes;
		disassembleIteratorToXLSRow(rowThatShallBeConstrained);
	}
	
	
	/**
	 * This method reads the Row Iterator 
	 * and transforms it to a more convenient 
	 * DisAssembledXLSRow 
	 * */
	
	private void disassembleIteratorToXLSRow(Iterator<Row> iterator){

		Row nextRow = iterator.next();
        Iterator<Cell> cellIterator = nextRow.cellIterator();
         
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
             
             if (cell.getCellType() ==  Cell.CELL_TYPE_STRING) {
            	
            	addNewCell(new DisAssembledXLSCell(m_ColumnNameIndexes.get(cell.getColumnIndex()), 
            			cell.getStringCellValue()));
            	
            }
             
        } 
	}
	
	
	
  
}
