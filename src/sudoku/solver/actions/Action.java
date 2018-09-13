package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

public interface Action {
	public void applyAction(SolverBoard inputBoard);
}