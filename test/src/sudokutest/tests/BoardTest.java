package sudokutest.tests;

import sudoku.board.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.*;
import org.testng.Assert;

import java.util.Arrays;

/*
A class containing tests for the board package
*/
public class BoardTest {
	//Logger
	private static final Logger logger = LoggerFactory.getLogger("sudoku.BoardTest.class");

	/*
	This test creates a board and verifies that all cells are created and present in boardValues
	*/
	@Test(groups = {"checkin", "full"})
	public void boardTest() {
		logger.info("Beginning " + Thread.currentThread().getStackTrace()[1].getMethodName() + ".");

		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Checks all board values
		Assert.assertTrue(Arrays.equals(testBoard.getBoardValues(), testValues));

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " completed successfully.");
	}

	/*
	This test creates a board and verifies that all cellId variables are created and retrieved properly
	*/
	@Test(groups = {"checkin", "full"})
	public void boardCellIdTest() {
		logger.info("Beginning " + Thread.currentThread().getStackTrace()[1].getMethodName() + ".");

		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Checks all cellId values
		for(int cellNum = 0; cellNum < testValues.length; cellNum++) {
			Assert.assertTrue(testBoard.getCell(cellNum).getCellId() == testValues[cellNum]);
		}

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " completed successfully.");
	}


	/*
	This test creates a board and verifies that squares are created and the cells inside them properly sorted
	*/
	@Test(groups = {"checkin", "full"})
	public void squareTest() {
		logger.info("Beginning " + Thread.currentThread().getStackTrace()[1].getMethodName() + ".");

		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Establishes and Checks certain square region values
		int[] square0 = {0,1,2,3,4,5,6,7,8};
		int[] square4 = {36,37,38,39,40,41,42,43,44};
		int[] square8 = {72,73,74,75,76,77,78,79,80};

		Assert.assertTrue(Arrays.equals(testBoard.getSquareValues(0), square0) && Arrays.equals(testBoard.getSquareValues(4), square4) && Arrays.equals(testBoard.getSquareValues(8), square8));

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " completed successfully.");
	}

	/*
	This test creates a board and verifies that rows are created and the cells inside them and properly sorted
	*/
	@Test(groups = {"checkin", "full"})
	public void rowTest() {
		logger.info("Beginning " + Thread.currentThread().getStackTrace()[1].getMethodName() + ".");

		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Establishes and Checks certain square region values
		int[] row0 = {0,1,2,9,10,11,18,19,20};
		int[] row4 = {30,31,32,39,40,41,48,49,50};
		int[] row8 = {60,61,62,69,70,71,78,79,80};

		Assert.assertTrue(Arrays.equals(testBoard.getRowValues(0), row0) && Arrays.equals(testBoard.getRowValues(4), row4) && Arrays.equals(testBoard.getRowValues(8), row8));

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " completed successfully.");
	}

	/*
	This test creates a board and verifies that columns are created and the cells inside them and properly sorted
	*/
	@Test(groups = {"checkin", "full"})
	public void columnTest() {
		logger.info("Beginning " + Thread.currentThread().getStackTrace()[1].getMethodName() + ".");

		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Establishes and Checks certain square region values
		int[] column0 = {0,3,6,27,30,33,54,57,60};
		int[] column4 = {10,13,16,37,40,43,64,67,70};
		int[] column8 = {20,23,26,47,50,53,74,77,80};

		Assert.assertTrue(Arrays.equals(testBoard.getColumnValues(0), column0) && Arrays.equals(testBoard.getColumnValues(4), column4) && Arrays.equals(testBoard.getColumnValues(8), column8));

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " completed successfully.");
	}
}