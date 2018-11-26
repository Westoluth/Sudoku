package sudoku.solver;

import sudoku.board.*;
import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;
import sudoku.solver.rules.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/*
Class to solve sudoku boards. Applies rules to generate actions which when applied to the
board result in a completed board.
*/
public class SudokuSolver {
	/*
	SudokuSolver variables:
		solverRules: Rule array containing all rules the solver will attempt to apply
		numRules: Constant indicating the number of rules
	*/

	Rule[] solverRules;
	static final int numRules = 4;

	public SudokuSolver() {
		//Creates and fills rules array
		solverRules = new Rule[numRules];

		/*
		UpdateBoard should always be first rule. After that, rule order should not affect ability
		to solve, but the following order seems most logical
		*/
		solverRules[0] = new UpdateBoard();
		solverRules[1] = new NakedSingleScan();
		solverRules[2] = new HiddenSingleScan();
		solverRules[3] = new NakedPairScan();
	}

	public SolverReport solve(Board inputBoard) throws IncompletePuzzleException, IncorrectPuzzleException{
		//Creates a new SolverBoard from inputBoard
		SolverBoard solverBoard = new SolverBoard(inputBoard);

		//Declares actions list
		List<Action> solverActions = new ArrayList<Action>();

		//Declares solve loop variables
		boolean progressMade = true;
		int previousActionsNum = 0;

		//Main solve loop
		while(progressMade) {
			//Applies all rules to solverBoard
			for(int ruleNum = 0; ruleNum < solverRules.length; ruleNum++) {
				//Applies Rule to solverBoard
				Action[] ruleActions = solverRules[ruleNum].applyRule(solverBoard);

				//Applies actions to solverBoard
				for(int actionNum = 0; actionNum < ruleActions.length; actionNum++) {
					ruleActions[actionNum].applyAction(solverBoard);
				}

				//Appends ruleActions to solverActions
				solverActions.addAll(Arrays.asList(ruleActions));
			}

			/*
			Checks if there's more than one new action, indicating progress was made.
			There will always be at least one new action created because UpdateBoard 
			will always generate one.
			*/
			if(solverActions.size() <= previousActionsNum+1) {
				progressMade = false;
			}

			previousActionsNum = solverActions.size();
		}

		//SudokuSolver has now made all progress possible, checks if puzzle is valid
		checkPuzzle(solverBoard);

		//Puzzle is now assumed to be valid, and a SolverReport is compiled and returns it
		SolverReport report = new SolverReport(new SolverBoard(inputBoard), solverBoard, solverActions.toArray(new Action[0]));
		return report;
	}


	/*
	Confirms validity of solved puzzle. Does nothing if puzzle is valid, otherwise throws certain type of exception
	*/
	private void checkPuzzle(SolverBoard inputBoard) throws IncompletePuzzleException, IncorrectPuzzleException{
		for(int regionGroupNum = 0; regionGroupNum < 3; regionGroupNum ++) {
			for(int regionNum = 0; regionNum < 9; regionNum++) {
				//Creates targetCells pointer to targetRegion's cells
				SolverCell[] targetCells = inputBoard.getRegion(regionGroupNum, regionNum).getCells();

				//Creates array to hold quantity of each number found
				int[] numCheck = new int[9];

				//Fills numCheck
				for(int cellNum = 0; cellNum < 9; cellNum++) {
					//Checks for empty tile. If found throws IncompletePuzzleException
					if(targetCells[cellNum].getCellValue() == 0) {
						throw new IncompletePuzzleException("Incomplete Puzzle Exception: Unable to solve puzzle");
					}

					numCheck[targetCells[cellNum].getCellValue()-1]++;
				}

				//Checks to make sure all numCheck values are 1. If not, throws IncorrectPuzzleException
				for(int checkIter = 0; checkIter < numCheck.length; checkIter++) {
					if(numCheck[checkIter] != 1) {
						throw new IncorrectPuzzleException("IncorrectPuzzleException: Incorrect input or error in program. End puzzle is not correct");
					}
				}
			}
		}
	}
}