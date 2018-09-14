package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

/*
An Action to remove multiple possible numbers from a SolverCell
*/
public class RemoveCellPossibleNums implements Action {
	/*
	RemoveCellPossibleNum variables:
		-cellId: The location of the cell in inputBoard's boardCells array
		-possibleNums: The possible numbers to be removed from Cell
	*/

	int cellId;
	int[] possibleNums;

	public RemoveCellPossibleNums(int cellId, int[] possibleNums) {
		this.cellId = cellId;
		this.possibleNums = possibleNums;
	}

	public void applyAction(SolverBoard inputBoard) {

	}
}