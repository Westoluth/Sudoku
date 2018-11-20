package sudoku.solver.solvercontext;

import sudoku.board.*;

/*
Class representing one board cell in the SudokuSolver context
*/
public class SolverCell extends Cell{
	/*
	SolverCell variables:
		-cellPossibleNums: Array of size 9. If value is true, corresponding number is a possibility in this cell. 
			If value is false, number is not a possibility in this cell.
	*/
	protected boolean[] cellPossibleNums;

	/*
	Creates a new SolverRegion. Can only be created from within the solvercontext package
	*/
	protected SolverCell(int cellValue, int cellId) {
		super(cellValue, cellId);

		cellPossibleNums = new boolean[9];

		/*
		Initializes cellPossibleNums to all true if cellValue equals zero, indicating a non-final number.
		If cellValue is not 0, sets only the cellPossibleNum corresponding to the value to true.
		*/
		if(cellValue == 0) {
			for(int cellPossibleNumsNum = 0; cellPossibleNumsNum < cellPossibleNums.length; cellPossibleNumsNum++) {
				cellPossibleNums[cellPossibleNumsNum] = true;
			}
		} else {
			cellPossibleNums[cellValue-1] = true;
		}
	}

	/*--------------------------------------------------------------------------------
	// Public Getter Methods 
	--------------------------------------------------------------------------------*/
	/*
	Returns cellPossibleNums
	*/
	public boolean[] getCellPossibleNums() {
		return cellPossibleNums;
	}

	/*
	Returns an int array of cellPossibleNums values
	*/
	public int[] getIntCellPossibleNums() {
		int[] possibleNums = new int[countCellPossibleNums()];
		int possibleNumsArrayIter = 0;

		for(int possibleNumIter = 0; possibleNumIter < cellPossibleNums.length; possibleNumIter++) {
			if(cellPossibleNums[possibleNumIter]) {
				possibleNums[possibleNumsArrayIter] = possibleNumIter+1;
				possibleNumsArrayIter++;
			}
		}

		return possibleNums;
	}

	/*
	Returns the number of possible nums remaining in the cell
	*/
	public int countCellPossibleNums() {
		int possibleNumsCount = 0;

		for(int possibleNumIter = 0; possibleNumIter < cellPossibleNums.length; possibleNumIter++) {
			if(cellPossibleNums[possibleNumIter]) {
				possibleNumsCount++;
			}
		}

		return possibleNumsCount;
	}

	/*--------------------------------------------------------------------------------
	// Public Management Functions
	--------------------------------------------------------------------------------*/

	/*
	Sets the cellValue to to the number and adjusts cellPossibleNums accordingly
	*/
	public void setValue(int value) {
		//Sets cellValue
		cellValue = value;

		//Sells all cellPossibleNums to false except for the one corresponding to cellValue
		for(int cellPossibleNumsNum = 0; cellPossibleNumsNum < cellPossibleNums.length; cellPossibleNumsNum++) {
			if(cellPossibleNumsNum != cellValue-1) {
				cellPossibleNums[cellPossibleNumsNum] = false;
			}
		}
	}

	/*
	Removes a possible number from cellPossibleNums.
	possibleNum(0-9) corresponds to a number being a possibility in this board cell.
	*/
	public void removePossibleNum(int possibleNum) {
		cellPossibleNums[possibleNum-1] = false;
	}
}