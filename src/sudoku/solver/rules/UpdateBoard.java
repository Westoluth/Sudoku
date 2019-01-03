package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

/*
A Rule to update all possible numbers in the board
*/
public class UpdateBoard implements Rule {
	public Action[] applyRule(SolverBoard inputBoard) {
		//Simply returns a UpdateBoardPossibleNums Action
		Action[] actions = new Action[1];
		
		actions[0] = new UpdateBoardPossibleNums();

		return actions;
	}

	/*
	String interpretation of Rule
	*/
	public String toString() {
		return "UpdateBoard";
	}
}