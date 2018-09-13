package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;

public interface Action {
	public void applyAction(SolverBoard inputBoard);
}