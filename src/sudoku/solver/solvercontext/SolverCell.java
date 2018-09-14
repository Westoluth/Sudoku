package sudoku.solver.solvercontext;

import sudoku.board.*;

/*
Class representing one board cell in the SudokuSolverContext
*/
public class SolverCell extends Cell{
	/*
	SolverCell variables:
		-cellPossibleNums: Array of size 9. If value is true, corresponding number is a possibility in this cell. 
			If value is false, number is not a possibility in this cell.
	*/
	protected boolean[] cellPossibleNums;

	protected SolverCell(int cellValue) {
		super(cellValue);

		cellPossibleNums = new boolean[9];

		//Initializes cellPossibleNums to all true if cellValue equals zero, indicating a non-final number
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

	/*--------------------------------------------------------------------------------
	// Public Management Functions
	--------------------------------------------------------------------------------*/

	/*
	Removes a possible number from cellPossibleNums.
	possibleNum(0-9) corresponds to a number being a possibility in this board cell.
	*/
	public void removePossibleNum(int possibleNum) {
		cellPossibleNums[possibleNum-1] = false;
	}
}