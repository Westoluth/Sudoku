package sudokutest.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import java.util.Arrays;

import sudoku.board.*;
import sudoku.solver.solvercontext.*;

public class SolverContextTest {
	/*
	This test creates a solver board and verifies that all cells are created and present in boardValues
	*/
	@Test(groups = {"checkin", "full"})
	public void boardTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Checks all board values
		Assert.assertTrue(Arrays.equals(testSolverBoard.getBoardValues(), testValues));
	}

	/*
	This test creates a solver board and verifies that squares are created and the cells inside them properly sorted
	*/
	@Test(groups = {"checkin", "full"})
	public void squareTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Establishes and Checks certain square region values
		int[] square0 = {0,1,2,3,4,5,6,7,8};
		int[] square4 = {36,37,38,39,40,41,42,43,44};
		int[] square8 = {72,73,74,75,76,77,78,79,80};

		Assert.assertTrue(Arrays.equals(testSolverBoard.getSquareValues(0), square0) && Arrays.equals(testSolverBoard.getSquareValues(4), square4) && Arrays.equals(testSolverBoard.getSquareValues(8), square8));
	}

	/*
	This test creates a solver board and verifies that rows are created and the cells inside them and properly sorted
	*/
	@Test(groups = {"checkin", "full"})
	public void rowTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Establishes and Checks certain square region values
		int[] row0 = {0,1,2,9,10,11,18,19,20};
		int[] row4 = {30,31,32,39,40,41,48,49,50};
		int[] row8 = {60,61,62,69,70,71,78,79,80};

		Assert.assertTrue(Arrays.equals(testSolverBoard.getRowValues(0), row0) && Arrays.equals(testSolverBoard.getRowValues(4), row4) && Arrays.equals(testSolverBoard.getRowValues(8), row8));
	}

	/*
	This test creates a solver board and verifies that columns are created and the cells inside them and properly sorted
	*/
	@Test(groups = {"checkin", "full"})
	public void columnTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Establishes and Checks certain square region values
		int[] column0 = {0,3,6,27,30,33,54,57,60};
		int[] column4 = {10,13,16,37,40,43,64,67,70};
		int[] column8 = {20,23,26,47,50,53,74,77,80};

		Assert.assertTrue(Arrays.equals(testSolverBoard.getColumnValues(0), column0) && Arrays.equals(testSolverBoard.getColumnValues(4), column4) && Arrays.equals(testSolverBoard.getColumnValues(8), column8));
	}

	/*
	This test creates a solver board and verifies that basic cell possible nums are correct.
	Does not check for cross region elimination.
	*/
	@Test(groups = {"checkin", "full"})
	public void basicCellPossibleNumsTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Sets test values to have 1,5 and 9 be contained in the first square at positions 0, 4 and 8 respectively
		testValues[0] = 1;
		testValues[4] = 5;
		testValues[8] = 9;

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Updates testSolverBoard possible numbers
		testSolverBoard.updatePossibleNums();

		//Creates comparison cases
		int totalTestCases = 9;

		boolean[][] cellPossibleNumsCases = new boolean[totalTestCases][];
		boolean[] cellPossibleNumsEmptyCase;

		cellPossibleNumsCases[0] = new boolean[]{false, false, false, false, false, false, false, false, false};
		cellPossibleNumsCases[1] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsCases[2] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsCases[3] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsCases[4] = new boolean[]{false, false, false, false, false, false, false, false, false};
		cellPossibleNumsCases[5] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsCases[6] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsCases[7] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsCases[8] = new boolean[]{false, false, false, false, false, false, false, false, false};
		cellPossibleNumsEmptyCase = new boolean[]{true, true, true, true, true, true, true, true, true};

		//Checks the cellPossibleNums of the first square
		for(int testCaseNum = 0; testCaseNum < cellPossibleNumsCases.length; testCaseNum++) {
			Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(testCaseNum).getCellPossibleNums(), cellPossibleNumsCases[testCaseNum]));
		}

		//Checks that the middle cell has all possible options open
		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(40).getCellPossibleNums(), cellPossibleNumsEmptyCase));
	}
	

	/*
	This test creates a solver board and verifies that basic segment possible nums are correct
	*/
	@Test(groups = {"checkin", "full"})
	public void basicSegmentPossibleNumsTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Sets square one of the test values to the numbers 1-9
		for(int testValuesNum = 1; testValuesNum <= 9; testValuesNum++) {
			testValues[8+testValuesNum] = testValuesNum;
		}

		//Sets square two of the test values to contain all odd numbers
		for(int testValuesNum = 1; testValuesNum <= 9; testValuesNum++) {
			if(testValuesNum%2 != 0) {
				testValues[17+testValuesNum] = testValuesNum;
			}
		}

		//Sets square three of the test values to contain all even numbers
		for(int testValuesNum = 1; testValuesNum <= 9; testValuesNum++) {
			if(testValuesNum%2 == 0) {
				testValues[26+testValuesNum] = testValuesNum;
			}
		}

		//Sets square four of the test values to contain the numbers 1-4 in the appropriate spaces
		for(int testValuesNum = 1; testValuesNum <= 4; testValuesNum++) {
			testValues[35+testValuesNum] = testValuesNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Updates testSolverBoard possible numbers
		testSolverBoard.updatePossibleNums();

		//Creates comparison cases
		int totalTestCases = 5;

		boolean[][] squarePossibleNumsCases = new boolean[totalTestCases][];

		squarePossibleNumsCases[0] = new boolean[]{true, true, true, true, true, true, true, true, true};
		squarePossibleNumsCases[1] = new boolean[]{false, false, false, false, false, false, false, false, false};
		squarePossibleNumsCases[2] = new boolean[]{false, true, false, true, false, true, false, true, false};
		squarePossibleNumsCases[3] = new boolean[]{true, false, true, false, true, false, true, false, true};
		squarePossibleNumsCases[4] = new boolean[]{false, false, false, false, true, true, true, true, true};

		//Checks all regionPossibleNums against comparison cases
		for(int testCaseNum = 0; testCaseNum < squarePossibleNumsCases.length; testCaseNum++) {
			Assert.assertTrue(Arrays.equals(testSolverBoard.getSquare(testCaseNum).getRegionPossibleNums(), squarePossibleNumsCases[testCaseNum]));
		}
	}

	/*
	This test creates a solver board and verifies that cell possible nums are correct when dealing with multiple overlapping segments
	*/
	@Test(groups = {"checkin", "full"})
	public void overlapCellPossibleNumsTest() {
		//Declares an initial empty array
		int[] testValues = new int[81];

		//Sets test values so that the middle cell of the board should have only one option remaining, 9.
		testValues[10] = 1;
		testValues[16] = 2;
		testValues[67] = 3;
		testValues[30] = 4;
		testValues[32] = 5;
		testValues[49] = 6;
		testValues[36] = 7;
		testValues[44] = 8;

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Updates testSolverBoard possible numbers
		testSolverBoard.updatePossibleNums();

		boolean[] centerCellCase = new boolean[]{false, false, false, false, false, false, false, false, true};

		//Checks that the middle cell of the board has only a 9 available
		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(40).getCellPossibleNums(), centerCellCase));
	}
}