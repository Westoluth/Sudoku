package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

public class UpdateBoardPossibleNums implements Action {
	public void applyAction(SolverBoard inputBoard) {
		inputBoard.updatePossibleNums();
	}
}