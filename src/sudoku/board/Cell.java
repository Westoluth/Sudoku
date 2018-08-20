package sudoku.board;

/*
A class representing a Cell on the game board
*/
public class Cell {
	/*
	Cell variables:
		finalNum: The number contained in a cell. Number 1-9 indicates that this cell has been finalized as this number.
			A number of 0 indicates that this cell has yet to be finalized
	*/
	protected int finalNum;

	protected Cell(int finalNum) {
		this.finalNum = finalNum;
	}

	/*--------------------------------------------------------------------------------
	// Public Getter Methods 
	--------------------------------------------------------------------------------*/
	
	/*
	Returns finalNum
	*/
	public int getFinalNum() {
		return finalNum;
	}

	public String toString() {
		return String.valueOf(finalNum);
	}
}