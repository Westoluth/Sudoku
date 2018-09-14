package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

public class RemoveRegionPossibleNums implements Action {
	/*
	RemoveRegionPossibleNums variables:
		-segmentType: The type of segment. (0 = squares, 1 = rows, 2 = columns)
		-segmentId: The segment number in inputBoard's segmentType array
		-removedNums: The possible numbers to be removed from Cell
	*/

	int segmentType;
	int segmentId;
	int[] removedNums;

	public void applyAction(SolverBoard inputBoard) {
		
	}
}