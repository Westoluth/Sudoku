package sudokutest.tests;

import sudoku.board.*;
import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

import org.testng.annotations.*;
import org.testng.Assert;

import java.util.Arrays;

/*
A class containing tests for the actions package
*/
public class ActionsTest {
	/*
	This test creates a solver board and verifies that the UpdatePossibleNums Action properly updates possible nums.
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
	This test creates a solver board, uses the SetCellValue action on a certain cell, and then verifies that it has been properly set.
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

	/*
	This test creates a solver board, uses the RemoveCellPossibleNums action on a certain cell, and then verifies that the numbers have been properly removed.
	*/
	@Test(groups = {"checkin", "full"})
	public void removeCellPossibleNumsTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Creates an action to remove 1 as a possible number for cell 0
		Action testAction1 = new RemoveCellPossibleNums(0, 1);

		//Creates an action to remove 2,3, and 4 as possible numbers for cell 0
		int[] possibleNums = {2,3,4};
		Action testAction2 = new RemoveCellPossibleNums(0, possibleNums);

		//Creates comparison cases
		boolean[] cellTestCase1 = new boolean[]{false, true, true, true, true, true, true, true, true};
		boolean[] cellTestCase2 = new boolean[]{false, false, false, false, true, true, true, true, true};

		//Applies actions and checks results
		testAction1.applyAction(testSolverBoard);
		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(0).getCellPossibleNums(), cellTestCase1));

		testAction2.applyAction(testSolverBoard);
		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(0).getCellPossibleNums(), cellTestCase2));
	}

	/*
	This test creates a solver board, uses the RemoveRegionPossibleNums action on a certain region, and then verifies that the numbers have been properly removed.
	*/
	@Test(groups = {"checkin", "full"})
	public void removeRegionPossibleNumsTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Creates an action to remove 1 as a possible number for square 0
		Action testAction1 = new RemoveRegionPossibleNums(0, 0, 1);

		//Creates an action to remove 2,3, and 4 as possible numbers for square 0
		int[] possibleNums = {2,3,4};
		Action testAction2 = new RemoveRegionPossibleNums(0, 0, possibleNums);

		//Creates comparison cases
		boolean[] regionTestCase1 = new boolean[]{false, true, true, true, true, true, true, true, true};
		boolean[] regionTestCase2 = new boolean[]{false, false, false, false, true, true, true, true, true};

		//Applies actions and checks results
		testAction1.applyAction(testSolverBoard);
		Assert.assertTrue(Arrays.equals(testSolverBoard.getRegion(0,0).getRegionPossibleNums(), regionTestCase1));
		
		testAction2.applyAction(testSolverBoard);
		Assert.assertTrue(Arrays.equals(testSolverBoard.getRegion(0,0).getRegionPossibleNums(), regionTestCase2));
	}
}