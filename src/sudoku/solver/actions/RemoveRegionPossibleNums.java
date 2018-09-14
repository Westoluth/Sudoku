package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

public class RemoveRegionPossibleNums implements Action {
	/*
	RemoveRegionPossibleNums variables:
		-regionType: The type of region. (0 = squares, 1 = rows, 2 = columns)
		-regionId: The region number in inputBoard's regionType array
		-removedNums: The possible numbers to be removed from Cell
	*/

	int regionType;
	int regionId;
	int[] removedNums;

	public void applyAction(SolverBoard inputBoard) {
		
	}
}