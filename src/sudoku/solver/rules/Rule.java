package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;

public interface Rule {
	public Action[] applyRule(SolverBoard inputBoard);
}