package sudokutest.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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
	public void nakedSingleScan() {
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

		//Creates UpdateBoard and NakedSingleScan rules and runs them on testSolverBoard, saving all actions to testActions
		List<Action> testActionsList = new ArrayList<Action>();

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

		//Checks all regionPossibleNums against comparison cases
		Assert.assertTrue(testSolverBoard.getCell(40).getCellValue() == cellTestCase);
	}

}