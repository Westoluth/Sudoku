package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

/*
An interface for a Rule. A Rule checks a SolverBoard to see
if the Rule can make progress, and if it can it returns a
list of Actions.
*/
public interface Rule {
	public Action[] applyRule(SolverBoard inputBoard);
}