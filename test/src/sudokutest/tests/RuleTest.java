package sudokutest.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import java.util.Arrays;

import sudoku.board.*;
import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;
import sudoku.solver.rules.*;

public class RuleTest {
	/*
	This test creates a solver board and verifies that the UpdateBoard Rule properly updates possible nums.
	*/
	@Test(groups = {"checkin", "full"})
	public void updateBoardTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Sets cell 4 of square 0 to 5
		testSolverBoard.getSquare(0).getCells()[4].setValue(5);

		//Creates an UpdateBoard rule and runs it on testSolverBoard, saving all actions to testActions
		Action[] testActions;
		Rule testRule = new UpdateBoard();
		testActions = testRule.applyRule(testSolverBoard);

		//Applies all actions to testBoard
		for(int actionNum = 0; actionNum < testActions.length; actionNum++) {
			testActions[actionNum].applyAction(testSolverBoard);
		}

		//Creates comparison cases
		boolean[] squareTestCase = new boolean[]{true, true, true, true, false, true, true, true, true};

		//Checks all regionPossibleNums against comparison cases
		Assert.assertTrue(Arrays.equals(testSolverBoard.getSquare(0).getRegionPossibleNums(),squareTestCase));
	}
}