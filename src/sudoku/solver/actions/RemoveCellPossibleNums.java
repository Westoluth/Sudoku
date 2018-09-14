package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

/*
An Action to remove one or more possible numbers from a SolverCell
*/
public class RemoveCellPossibleNums implements Action {
	/*
	RemoveCellPossibleNum variables:
		-cellId: The location of the cell in inputBoard's boardCells array
		-removedNums: The possible numbers to be removed from Cell
	*/

	int cellId;
	int[] removedNums;

	/*
	Constructor to remove just one possible number from a cell 
	*/
	public RemoveCellPossibleNums(int cellId, int removedNum) {
		this.cellId = cellId;
		removedNums = new int[1];
		removedNums[0] = removedNum;
	}

	/*
	Constructor to remove multiple possible numbers from a cell
	*/
	public RemoveCellPossibleNums(int cellId, int[] removedNums) {
		this.cellId = cellId;
		this.removedNums = removedNums;
	}

	/*
	Removes removedNums from a cell specified by cellId's possibleNums
	*/
	public void applyAction(SolverBoard inputBoard) {
		//Finds targetCell based of cellId
		SolverCell targetCell =  inputBoard.getCell(cellId);

		//Removes all numbers in removedNums from targetCell's possibleNums
		for(int removedNumsIt = 0; removedNumsIt < removedNums.length; removedNumsIt++) {
			targetCell.removePossibleNum(removedNums[removedNumsIt]);
		}
	}
}