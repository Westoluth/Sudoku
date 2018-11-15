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
	This test verifies that the UpdateBoard Rule properly updates possible nums.
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

	/*
	This test verifies that the NakedSingleScan Rule properly finds naked singles.
	*/
	@Test(groups = {"checkin", "full"})
	public void nakedSingleScanTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Sets testSolverBoard values to cause cell 40 to become a hidden single
		testSolverBoard.getCell(10).setValue(1);
		testSolverBoard.getCell(13).setValue(2);
		testSolverBoard.getCell(30).setValue(3);
		testSolverBoard.getCell(31).setValue(4);
		testSolverBoard.getCell(49).setValue(5);
		testSolverBoard.getCell(50).setValue(6);
		testSolverBoard.getCell(67).setValue(7);
		testSolverBoard.getCell(70).setValue(8);

		//Gets actions from UpdateBoardRule
		Rule updateRule = new UpdateBoard();
		Action[] testActions = updateRule.applyRule(testSolverBoard);

		//Applies all actions to testBoard
		for(int actionNum = 0; actionNum < testActions.length; actionNum++) {
			testActions[actionNum].applyAction(testSolverBoard);
		}

		//Gets actions from NakedSingleScan
		Rule  testRule = new NakedSingleScan();
		 	testActions = testRule.applyRule(testSolverBoard);

		//Applies all actions to testBoard
		for(int actionNum = 0; actionNum < testActions.length; actionNum++) {
			testActions[actionNum].applyAction(testSolverBoard);
		}

		//Creates comparison cases
		int cellTestCase = 9;

		//Checks cell against comparison cases
		Assert.assertTrue(testSolverBoard.getCell(40).getCellValue() == cellTestCase);
	}

	/*
	This test verifies that the HiddenSingleScan Rule properly finds hidden singles.
	*/
	@Test(groups = {"checkin", "full"})
	public void hiddenSingleScanTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Sets testSolverBoard values to cause cell 40 to become a hidden single
		testSolverBoard.getCell(10).setValue(1);
		testSolverBoard.getCell(13).setValue(2);
		testSolverBoard.getCell(64).setValue(3);
		testSolverBoard.getCell(70).setValue(4);
		testSolverBoard.getCell(14).setValue(5);
		testSolverBoard.getCell(35).setValue(5);
		testSolverBoard.getCell(45).setValue(5);
		testSolverBoard.getCell(58).setValue(5);

		//Gets actions from UpdateBoardRule
		Rule updateRule = new UpdateBoard();
		Action[] testActions = updateRule.applyRule(testSolverBoard);

		//Applies all actions to testBoard
		for(int actionNum = 0; actionNum < testActions.length; actionNum++) {
			testActions[actionNum].applyAction(testSolverBoard);
		}

		//Gets actions from HiddenSingleScan
		Rule  testRule = new HiddenSingleScan();
		testActions = testRule.applyRule(testSolverBoard);

		//Applies all actions to testBoard
		for(int actionNum = 0; actionNum < testActions.length; actionNum++) {
			testActions[actionNum].applyAction(testSolverBoard);
		}

		//Creates comparison cases
		int cellTestCase = 5;

		//Checks cell against comparison cases
		Assert.assertTrue(testSolverBoard.getCell(40).getCellValue() == cellTestCase);
	}

	/*
	This test verifies that the NakedPairScan Rule properly finds naked pairs.
	*/
	@Test(groups = {"checkin", "full"})
	public void nakedPairScanTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Sets testSolverBoard values to cause cell 40 to become a hidden single
		testSolverBoard.getCell(10).setValue(1);
		testSolverBoard.getCell(16).setValue(2);
		testSolverBoard.getCell(40).setValue(5);
		testSolverBoard.getCell(64).setValue(3);
		testSolverBoard.getCell(70).setValue(4);
		testSolverBoard.getCell(12).setValue(8);
		testSolverBoard.getCell(14).setValue(9);
		testSolverBoard.getCell(57).setValue(8);
		testSolverBoard.getCell(77).setValue(9);

		//Gets actions from UpdateBoardRule
		Rule updateRule = new UpdateBoard();
		Action[] testActions = updateRule.applyRule(testSolverBoard);

		//Applies all actions to testBoard
		for(int actionNum = 0; actionNum < testActions.length; actionNum++) {
			testActions[actionNum].applyAction(testSolverBoard);
		}

		//Gets actions from NakedPairScan
		Rule  testRule = new NakedPairScan();
		testActions = testRule.applyRule(testSolverBoard);

		//Applies all actions to testBoard
		for(int actionNum = 0; actionNum < testActions.length; actionNum++) {
			testActions[actionNum].applyAction(testSolverBoard);
		}

		//Creates comparison cases
		int[] cellTestCase = {8, 9};

		//Checks all regionPossibleNums against comparison cases
		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(37).getIntCellPossibleNums(), cellTestCase));
		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(43).getIntCellPossibleNums(), cellTestCase));
	}
}