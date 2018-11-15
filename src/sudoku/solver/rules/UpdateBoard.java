package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

public class UpdateBoard implements Rule {
	public Action[] applyRule(SolverBoard inputBoard) {
		Action[] actions = new Action[1];
		
		actions[0] = new UpdateBoardPossibleNums();

		return actions;
	}
}