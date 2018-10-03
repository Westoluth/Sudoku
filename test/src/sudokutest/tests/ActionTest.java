package sudokutest.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import java.util.Arrays;

import sudoku.board.*;
import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

public class ActionTest {
	/*
	This test creates a solver board and verifies that the UpdatePossibleNums Action properly
	updates possible nums.
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
		boolean[] squareTestCase = new boolean[]{true, true, true, true, false, true, true, true, true};

		//Checks all regionPossibleNums against comparison cases
		Assert.assertTrue(Arrays.equals(testSolverBoard.getSquare(0).getRegionPossibleNums(),squareTestCase));
	}

	/*
	This test creates a solver board, uses the SetCellValue action on a certain cell, and then verifies
	that it has been properly set.
	*/
	@Test(groups = {"checkin", "full"})
	public void setCellValueTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Creates an action to set cell 40 of a board to 5 and applies it
		Action testAction = new SetCellValue(40, 5);
		testAction.applyAction(testSolverBoard);

		//Checks all regionPossibleNums against comparison cases
		Assert.assertTrue(testSolverBoard.getCellValue(40) == 5);
	}
}