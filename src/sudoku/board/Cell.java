package sudoku.board;

/*
A class representing a Cell on the game board
*/
public class Cell {
	/*
	Cell variables:
		cellValue: The number contained in a cell. Number 1-9 indicates that this cell has been finalized as this number.
			A number of 0 indicates that this cell has yet to be finalized
	*/
	protected int cellValue;

	protected Cell(int cellValue) {
		this.cellValue = cellValue;
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

	public String toString() {
		return String.valueOf(cellValue);
	}
}