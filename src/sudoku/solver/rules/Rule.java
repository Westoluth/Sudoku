package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

public interface Rule {
	public Action[] applyRule(SolverBoard inputBoard);
}