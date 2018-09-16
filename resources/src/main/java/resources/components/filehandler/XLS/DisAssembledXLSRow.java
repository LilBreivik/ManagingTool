package resources.components.filehandler.XLS;

import java.util.ArrayList;
import java.util.Collection;
 

public class DisAssembledXLSRow {

	private Collection<DisAssembledXLSCell> cells;

	public DisAssembledXLSRow() {
		
		this.cells = new ArrayList<DisAssembledXLSCell>();
	}

	public void addNewCell(DisAssembledXLSCell newCell) {
		
		this.cells.add(newCell);
	}
	
	public Collection<DisAssembledXLSCell> getCellsFromRow(){
		
		return this.cells;
	}
	 
}
