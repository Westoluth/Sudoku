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

		//Fills test values with ints numbered 1-9
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = (testValueNum%9)+1;
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

		//Fills test values with ints numbered 1-9
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = (testValueNum%9)+1;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Establishes and Checks certain square region values
		int[] square0 = {1,2,3,4,5,6,7,8,9};
		int[] square4 = {1,2,3,4,5,6,7,8,9};
		int[] square8 = {1,2,3,4,5,6,7,8,9};

		Assert.assertTrue(Arrays.equals(testSolverBoard.getSquareValues(0), square0) && Arrays.equals(testSolverBoard.getSquareValues(4), square4) && Arrays.equals(testSolverBoard.getSquareValues(8), square8));
	}

	/*
	This test creates a solver board and verifies that rows are created and the cells inside them and properly sorted
	*/
	@Test(groups = {"checkin", "full"})
	public void rowTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 1-9
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = (testValueNum%9)+1;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Establishes and Checks certain square region values
		int[] row0 = {1,2,3,1,2,3,1,2,3};
		int[] row4 = {4,5,6,4,5,6,4,5,6};
		int[] row8 = {7,8,9,7,8,9,7,8,9};

		Assert.assertTrue(Arrays.equals(testSolverBoard.getRowValues(0), row0) && Arrays.equals(testSolverBoard.getRowValues(4), row4) && Arrays.equals(testSolverBoard.getRowValues(8), row8));
	}

	/*
	This test creates a solver board and verifies that columns are created and the cells inside them and properly sorted
	*/
	@Test(groups = {"checkin", "full"})
	public void columnTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 1-9
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = (testValueNum%9)+1;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Establishes and Checks certain square region values
		int[] column0 = {1,4,7,1,4,7,1,4,7};
		int[] column4 = {2,5,8,2,5,8,2,5,8};
		int[] column8 = {3,6,9,3,6,9,3,6,9};

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

		//Creates comparison cases
		int totalTestCases = 9;

		//Creates boolean test cases
		boolean[][] cellPossibleNumsBooleanCases = new boolean[totalTestCases][];
		boolean[] cellPossibleNumsBooleanEmptyCase;

		cellPossibleNumsBooleanCases[0] = new boolean[]{true, false, false, false, false, false, false, false, false};
		cellPossibleNumsBooleanCases[1] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsBooleanCases[2] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsBooleanCases[3] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsBooleanCases[4] = new boolean[]{false, false, false, false, true, false, false, false, false};
		cellPossibleNumsBooleanCases[5] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsBooleanCases[6] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsBooleanCases[7] = new boolean[]{false, true, true, true, false, true, true, true, false};
		cellPossibleNumsBooleanCases[8] = new boolean[]{false, false, false, false, false, false, false, false, true};
		cellPossibleNumsBooleanEmptyCase = new boolean[]{true, true, true, true, true, true, true, true, true};

		//Creates int test cases
		int[][] cellPossibleNumsIntCases = new int[totalTestCases][];
		int[] cellPossibleNumsIntEmptyCase;

		cellPossibleNumsIntCases[0] = new int[]{1};
		cellPossibleNumsIntCases[1] = new int[]{2, 3, 4, 6, 7, 8};
		cellPossibleNumsIntCases[2] = new int[]{2, 3, 4, 6, 7, 8};
		cellPossibleNumsIntCases[3] = new int[]{2, 3, 4, 6, 7, 8};
		cellPossibleNumsIntCases[4] = new int[]{5};
		cellPossibleNumsIntCases[5] = new int[]{2, 3, 4, 6, 7, 8};
		cellPossibleNumsIntCases[6] = new int[]{2, 3, 4, 6, 7, 8};
		cellPossibleNumsIntCases[7] = new int[]{2, 3, 4, 6, 7, 8};
		cellPossibleNumsIntCases[8] = new int[]{9};
		cellPossibleNumsIntEmptyCase = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

		//Creates count test cases
		int[] cellPossibleNumsCountCases = new int[totalTestCases];
		int cellPossibleNumsCountEmptyCase;

		cellPossibleNumsCountCases[0] = 1;
		cellPossibleNumsCountCases[1] = 6;
		cellPossibleNumsCountCases[2] = 6;
		cellPossibleNumsCountCases[3] = 6;
		cellPossibleNumsCountCases[4] = 1;
		cellPossibleNumsCountCases[5] = 6;
		cellPossibleNumsCountCases[6] = 6;
		cellPossibleNumsCountCases[7] = 6;
		cellPossibleNumsCountCases[8] = 1;
		cellPossibleNumsCountEmptyCase = 9;

		//Checks the cellPossibleNums of the first square against boolean comparison cases
		for(int testCaseNum = 0; testCaseNum < cellPossibleNumsBooleanCases.length; testCaseNum++) {
			Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(testCaseNum).getCellPossibleNums(), cellPossibleNumsBooleanCases[testCaseNum]));
		}

		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(40).getCellPossibleNums(), cellPossibleNumsBooleanEmptyCase));

		//Checks the cellPossibleNums of the first square against boolean comparison cases
		for(int testCaseNum = 0; testCaseNum < cellPossibleNumsIntCases.length; testCaseNum++) {
			Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(testCaseNum).getIntCellPossibleNums(), cellPossibleNumsIntCases[testCaseNum]));
		}

		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(40).getIntCellPossibleNums(), cellPossibleNumsIntEmptyCase));

		//Checks the cellPossibleNums of the first square against boolean comparison cases
		for(int testCaseNum = 0; testCaseNum < cellPossibleNumsCountCases.length; testCaseNum++) {
			Assert.assertTrue(testSolverBoard.getCell(testCaseNum).countCellPossibleNums() == cellPossibleNumsCountCases[testCaseNum]);
		}

		Assert.assertTrue(testSolverBoard.getCell(40).countCellPossibleNums() == cellPossibleNumsCountEmptyCase);
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

		//Creates comparison cases
		int totalTestCases = 5;

		//Creates boolean test cases
		boolean[][] squarePossibleNumsBooleanCases = new boolean[totalTestCases][];

		squarePossibleNumsBooleanCases[0] = new boolean[]{true, true, true, true, true, true, true, true, true};
		squarePossibleNumsBooleanCases[1] = new boolean[]{false, false, false, false, false, false, false, false, false};
		squarePossibleNumsBooleanCases[2] = new boolean[]{false, true, false, true, false, true, false, true, false};
		squarePossibleNumsBooleanCases[3] = new boolean[]{true, false, true, false, true, false, true, false, true};
		squarePossibleNumsBooleanCases[4] = new boolean[]{false, false, false, false, true, true, true, true, true};

		//Creates int test cases
		int[][] squarePossibleNumsIntCases = new int[totalTestCases][];

		squarePossibleNumsIntCases[0] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
		squarePossibleNumsIntCases[1] = new int[]{};
		squarePossibleNumsIntCases[2] = new int[]{2, 4, 6, 8};
		squarePossibleNumsIntCases[3] = new int[]{1, 3, 5, 7, 9};
		squarePossibleNumsIntCases[4] = new int[]{5, 6, 7, 8, 9};

		//Creates count test cases
		int[] squarePossibleNumsCountCases = new int[totalTestCases];

		squarePossibleNumsCountCases[0] = 9;
		squarePossibleNumsCountCases[1] = 0;
		squarePossibleNumsCountCases[2] = 4;
		squarePossibleNumsCountCases[3] = 5;
		squarePossibleNumsCountCases[4] = 5;


		//Checks all regionPossibleNums against boolean comparison cases
		for(int testCaseNum = 0; testCaseNum < squarePossibleNumsBooleanCases.length; testCaseNum++) {
			Assert.assertTrue(Arrays.equals(testSolverBoard.getSquare(testCaseNum).getRegionPossibleNums(), squarePossibleNumsBooleanCases[testCaseNum]));
		}

		//Checks all regionPossibleNums against int comparison cases
		for(int testCaseNum = 0; testCaseNum < squarePossibleNumsIntCases.length; testCaseNum++) {
			Assert.assertTrue(Arrays.equals(testSolverBoard.getSquare(testCaseNum).getIntRegionPossibleNums(), squarePossibleNumsIntCases[testCaseNum]));
		}

		//Checks all regionPossibleNums against count comparison cases
		for(int testCaseNum = 0; testCaseNum < squarePossibleNumsCountCases.length; testCaseNum++) {
			Assert.assertTrue(testSolverBoard.getSquare(testCaseNum).countRegionPossibleNums() == squarePossibleNumsCountCases[testCaseNum]);
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

		//Declares and initializes comparison case
		boolean[] centerCellCase = new boolean[]{false, false, false, false, false, false, false, false, true};

		//Checks that the middle cell of the board has only a 9 available
		Assert.assertTrue(Arrays.equals(testSolverBoard.getCell(40).getCellPossibleNums(), centerCellCase));
	}

	/*
	This test creates a solver board and verifies that all cells are created and present in boardValues when upcasted to Board
	*/
	@Test(groups = {"checkin", "full"})
	public void upcastBoardTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 1-9
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = (testValueNum%9)+1;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Upcasts baseTestSolver board to upcasted board
		Board upcastedTestBoard = ((Board)testSolverBoard);

		//Checks all board values
		Assert.assertTrue(Arrays.equals(upcastedTestBoard.getBoardValues(), testValues));
	}

	/*
	This test creates a solver board and verifies that squares are created and the cells inside them properly sorted when upcasted to Board
	*/
	@Test(groups = {"checkin", "full"})
	public void upcastSquareTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 1-9
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = (testValueNum%9)+1;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Upcasts baseTestSolver board to upcasted board
		Board upcastedTestBoard = ((Board)testSolverBoard);

		//Establishes and Checks certain square region values
		int[] square0 = {1,2,3,4,5,6,7,8,9};
		int[] square4 = {1,2,3,4,5,6,7,8,9};
		int[] square8 = {1,2,3,4,5,6,7,8,9};

		Assert.assertTrue(Arrays.equals(upcastedTestBoard.getSquareValues(0), square0) && Arrays.equals(upcastedTestBoard.getSquareValues(4), square4) && Arrays.equals(upcastedTestBoard.getSquareValues(8), square8));
	}

	/*
	This test creates a solver board and verifies that rows are created and the cells inside them and properly sorted when upcasted to Board
	*/
	@Test(groups = {"checkin", "full"})
	public void upcastRowTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 1-9
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = (testValueNum%9)+1;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Upcasts baseTestSolver board to upcasted board
		Board upcastedTestBoard = ((Board)testSolverBoard);

		//Establishes and Checks certain square region values
		int[] row0 = {1,2,3,1,2,3,1,2,3};
		int[] row4 = {4,5,6,4,5,6,4,5,6};
		int[] row8 = {7,8,9,7,8,9,7,8,9};

		Assert.assertTrue(Arrays.equals(upcastedTestBoard.getRowValues(0), row0) && Arrays.equals(upcastedTestBoard.getRowValues(4), row4) && Arrays.equals(upcastedTestBoard.getRowValues(8), row8));
	}

	/*
	This test creates a solver board and verifies that columns are created and the cells inside them and properly sorted when upcasted to Board
	*/
	@Test(groups = {"checkin", "full"})
	public void upcastColumnTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 1-9
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = (testValueNum%9)+1;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);
		
		//Creates new solver board and passes it board
		SolverBoard testSolverBoard = new SolverBoard(testBoard);

		//Upcasts baseTestSolver board to upcasted board
		Board upcastedTestBoard = ((Board)testSolverBoard);

		//Establishes and Checks certain square region values
		int[] column0 = {1,4,7,1,4,7,1,4,7};
		int[] column4 = {2,5,8,2,5,8,2,5,8};
		int[] column8 = {3,6,9,3,6,9,3,6,9};

		Assert.assertTrue(Arrays.equals(upcastedTestBoard.getColumnValues(0), column0) && Arrays.equals(upcastedTestBoard.getColumnValues(4), column4) && Arrays.equals(upcastedTestBoard.getColumnValues(8), column8));
	}
}