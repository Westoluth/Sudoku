package sudoku.board;

/*
A board Cell containing one number
*/
public class Cell {
	/*
	Cell variables:
		finalNum: The number contained in a cell
	*/
	public int finalNum;

	public Cell(int finalNum) {
		this.finalNum = finalNum;
	}

	public String toString() {
		return String.valueOf(finalNum);
	}
}