package sudoku.board;

/*
A class representing a Cell on the game board
*/
public class Cell {
	/*
	Cell variables:
		-cellValue: The number contained in a cell. Number 1-9 indicates that this cell has been finalized as this number.
			A number of 0 indicates that this cell has yet to be finalized
		-cellId: Number of the cell in the Board's boardCells array
	*/
	protected int cellValue;
	protected int cellId;

	protected Cell(int cellValue, int cellId) {
		this.cellValue = cellValue;
		this.cellId = cellId;
	}

	/*--------------------------------------------------------------------------------
	// Public Getter Methods 
	--------------------------------------------------------------------------------*/
	
	/*
	Returns cellValue
	*/
	public int getCellValue() {
		return cellValue;
	}

	/*
	Returns cellId
	*/
	public int getCellId() {
		return cellId;
	}

	public String toString() {
		return String.valueOf(cellValue);
	}
}