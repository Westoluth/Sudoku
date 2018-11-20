package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

/*
An Action to update all possible numbers arrays in the board's Regions and Cells
*/
public class UpdateBoardPossibleNums implements Action {
	/*
	Calls updatePossibleNums on the SolverBoard
	*/
	public void applyAction(SolverBoard inputBoard) {
		inputBoard.updatePossibleNums();
	}
}