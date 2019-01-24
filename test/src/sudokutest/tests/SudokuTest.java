package sudokutest.tests;

import sudoku.board.*;
import sudoku.solver.*;
import sudoku.solver.solvercontext.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.*;
import org.testng.Assert;

import java.util.Arrays;
import java.io.*;

/*
A class containing tests for entire Sudoku App
*/
public class SudokuTest {
	//Logger
	private static final Logger logger = LoggerFactory.getLogger("sudoku.SudokuTest.class");	

	/*
	This test runs a full suite of puzzles through the solver.
	*/
	@Test(groups = {"full"}, priority = 1)
	public void puzzlesTest() throws FileNotFoundException, IOException {
		//Boolean value to track if an IncorrectPuzzleException is thrown, indicating code flaw
		boolean incorrectThrown = false;

		//Directory variables
		String unsolvedPuzzlesDirectory = "test/data/unsolvedPuzzles";

		//Gets files in unsolvedPuzzlesDirectory
		File unsolvedPuzzlesFolder = new File(unsolvedPuzzlesDirectory);
		File[] unsolvedPuzzlesFiles = unsolvedPuzzlesFolder.listFiles();
		Arrays.sort(unsolvedPuzzlesFiles);

		//Sets up unsolvedPuzzlesValues raw and parsed directory
		int[][] unsolvedPuzzlesValuesRaw = new int[unsolvedPuzzlesFiles.length][81];
		int[][] unsolvedPuzzlesValues = new int[unsolvedPuzzlesFiles.length][81];

		//Fills unsolvedPuzzlesValues
		for(int fileNum = 0; fileNum < unsolvedPuzzlesFiles.length; fileNum++) {
			//Sets up fileReader and bufferReader
			FileReader fileReader = new FileReader(unsolvedPuzzlesFiles[fileNum]);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			//Sets up array and char to store puzzle values
			int[] puzzleValues = new int[81];
			int puzzleIter = 0;
			int puzzleChar = -1;

			//Parses puzzle
			do {
				//Reads next digit
				puzzleChar = bufferedReader.read();

				//Checks if puzzleChar is a valid sudoku number
				if(puzzleChar >= 48 && puzzleChar <= 57) {
					puzzleValues[puzzleIter] = puzzleChar-48;
					puzzleIter++;
				}

			} while(puzzleChar != -1);

			unsolvedPuzzlesValuesRaw[fileNum] = puzzleValues;
		}


		//Converts each array of puzzle values from row based form to square based from
		for(int fileNum = 0; fileNum < unsolvedPuzzlesFiles.length; fileNum++) {
			//Integer tp track which slot for tile to be placed into
			int tileCount = 0;
			for(int rowGroup = 0; rowGroup < 3; rowGroup++) {
				for(int columnGroup = 0; columnGroup < 3; columnGroup++) {
					for(int rowNum = 0; rowNum < 3; rowNum++) {
						for(int columnNum = 0; columnNum < 3; columnNum++) {
							unsolvedPuzzlesValues[fileNum][tileCount] = unsolvedPuzzlesValuesRaw[fileNum][columnNum+(9*rowNum)+(3*columnGroup)+27*(rowGroup)];
							tileCount++;
						}
					}
				}
			}
		}

		//Tests each puzzle
		for(int fileNum = 0; fileNum < unsolvedPuzzlesFiles.length; fileNum++) {
			SudokuSolver puzzleSolver = new SudokuSolver();

			try {
				SolverReport puzzleReport = puzzleSolver.solve(new Board(unsolvedPuzzlesValues[fileNum]));
				logger.info(unsolvedPuzzlesFiles[fileNum].getName() + ": Puzzle completed");
			} catch (IncompletePuzzleException e) {
				logger.info(unsolvedPuzzlesFiles[fileNum].getName() + ": Unable to complete puzzle");
			} catch (IncorrectPuzzleException e) {
				logger.error(unsolvedPuzzlesFiles[fileNum].getName() + ": Puzzle incorrect");
				incorrectThrown = true;
			}
		}

		//Checks to make sure no IncorrectPuzzleExceptions were thrown
		Assert.assertFalse(incorrectThrown);
	}
}