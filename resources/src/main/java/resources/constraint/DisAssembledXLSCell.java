package resources.constraint;

public class DisAssembledXLSCell {

	private String m_cellColumnName; 
	
	private String m_cellValue;

	
	public DisAssembledXLSCell(String cellColumnName, 
									String cellValue) {
		
		
		m_cellColumnName = cellColumnName; 
		m_cellValue =  cellValue;
	}
	
	
	public String getCellColumnName() {
		return m_cellColumnName;
	}

	public void setCellColumnName(String cellColumnName) {
	    m_cellColumnName = cellColumnName;
	}

	public String getCellValue() {
		return m_cellValue;
	}

	public void setCellValue(String cellValue) {
		m_cellValue = cellValue;
	} 
}
