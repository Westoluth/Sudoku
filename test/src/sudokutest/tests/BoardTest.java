package sudokutest.tests;

import org.testng.annotations.*;
import java.util.Arrays;

import sudoku.Board;

public class BoardTest {
	/*
	This test creates a board and verifies that all cells are created and sorted into the correct spots
	*/
	@Test(groups = {"checkin", "full"})
	public void creationTest() {
		//Declares array full of test values to be passed to board
		int[] testValues = new int[81];

		//Fills test values with ints numbered 0-80
		for(int testValueNum = 0; testValueNum < testValues.length; testValueNum++) {
			testValues[testValueNum] = testValueNum;
		}

		//Creates new board and passes it testValues
		Board testBoard = new Board(testValues);

		//Checks all board values
		assert Arrays.equals(testBoard.getBoardValues(), testValues);

		//Establishes and Checks certain region values
		int[] square0 = {0,1,2,3,4,5,6,7,8};
		int[] square4 = {36,37,38,39,40,41,42,43,44};
		int[] square8 = {72,74,75,76,77,78,79,80,81};

		int[] row0 = {0,1,2,9,10,11,18,19,20};
		int[] row4 = {30,31,32,39,40,41,48,49,51};
		int[] row8 = {60,61,62,69,70,71,78,79,80};

		int[] column0 = {0,3,6,27,30,33,54,57,60};
		int[] column4 = {10,13,16,37,40,43,64,67,70};
		int[] column8 = {20,23,26,47,50,53,74,77,80};

		assert Arrays.equals(testBoard.getSquareValues(0), square0);
		assert Arrays.equals(testBoard.getSquareValues(4), square4);
		assert Arrays.equals(testBoard.getSquareValues(8), square8);

		assert Arrays.equals(testBoard.getRowValues(0), row0);
		assert Arrays.equals(testBoard.getRowValues(4), row4);
		assert Arrays.equals(testBoard.getRowValues(8), row8);
		
		assert Arrays.equals(testBoard.getColumnValues(0), column0);
		assert Arrays.equals(testBoard.getColumnValues(4), column4);
		assert Arrays.equals(testBoard.getColumnValues(8), column8);
	}
}