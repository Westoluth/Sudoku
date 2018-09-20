package sudokutest.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import java.util.Arrays;

import sudoku.board.*;
import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

public class ActionTest {
	/*
	This test creates a solver board and verifies that basic segment possible nums are correct
	*/
	@Test(groups = {"checkin", "full"})
	public void updatePossibleNumsTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Sets cell 4 of square 0 to 5
		testSolverBoard.getSquare(0).getCells()[4].setValue(5);

		//Creates an UpdateBoardPossibeNums Action and applies it to testSolverBoard
		Action testAction = new UpdateBoardPossibleNums();
		testAction.applyAction(testSolverBoard);

		//Creates comparison cases
		boolean[] squareTestCase = new boolean[]{false, false, false, false, false, false, false, false, false};

		//Checks all regionPossibleNums against comparison cases
		Assert.assertTrue(Arrays.equals(testSolverBoard.getSquare(0).getRegionPossibleNums(),squareTestCase));
	}
}