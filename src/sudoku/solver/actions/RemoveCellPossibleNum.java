package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

/*
An Action to remove one possible number from a SolverCell
*/
public class RemoveCellPossibleNum implements Action {
	/*
	RemoveCellPossibleNum variables:
		-cellId: The location of the cell in inputBoard's boardCells array
		-possibleNum: The possible number to be removed from Cell
	*/

	int cellId;
	int possibleNum;

	public RemoveCellPossibleNum(int cellId, int possibleNum) {
		this.cellId = cellId;
		this.possibleNum = possibleNum;
	}

	public void applyAction(SolverBoard inputBoard) {

	}
}