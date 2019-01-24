package sudoku.solver;

import sudoku.board.*;
import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;
import sudoku.solver.rules.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		logger: Logger for SudokuSolver
	*/

	Rule[] solverRules;
	static final int numRules = 4;

	//Logger
	private static final Logger logger = LoggerFactory.getLogger("sudoku.SudokuSolver.class");

	public SudokuSolver() {
		logger.debug("Beginning SudokuSolver setup.");

		logger.debug("Setting up Rules");

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

		logger.debug("Rules:");

		for(int ruleNum = 0; ruleNum < solverRules.length; ruleNum++) {
			logger.debug("	{}: {}", (ruleNum+1), solverRules[ruleNum].toString());
		}

		logger.debug("Completed SudokuSolver setup.");
	}

	public SolverReport solve(Board inputBoard) throws IncompletePuzzleException, IncorrectPuzzleException{
		//Creates a new SolverBoard from inputBoard
		SolverBoard solverBoard = new SolverBoard(inputBoard);

		//Declares actions list
		List<Action> solverActions = new ArrayList<Action>();

		//Declares solve loop variables
		boolean progressMade = true;
		int previousActionsNum = 0;
		int loopCounter = 0;

		logger.debug("Beginning main solver loop.");

		//Main solve loop
		while(progressMade) {
			loopCounter++;
			logger.debug("Starting Cycle#{}.", loopCounter);

			//Applies all rules to solverBoard
			for(int ruleNum = 0; ruleNum < solverRules.length; ruleNum++) {
				//Applies Rule to solverBoard
				Action[] ruleActions = solverRules[ruleNum].applyRule(solverBoard);

				//Applies actions to solverBoard
				for(int actionNum = 0; actionNum < ruleActions.length; actionNum++) {
					ruleActions[actionNum].applyAction(solverBoard);
				}

				logger.debug("Applying Rule {}.", solverRules[ruleNum].toString());
				if(ruleActions.length == 0) {
					logger.debug("	No actions generated.");
				} else {
					logger.debug("Actions generated:");

					for(int actionNum = 0; actionNum < ruleActions.length; actionNum++) {
						logger.debug("	{}: {}", (actionNum+1), ruleActions[actionNum].toString());
					}
				}

				//Appends ruleActions to solverActions
				solverActions.addAll(Arrays.asList(ruleActions));
			}

			logger.debug("Board status(Cycle#{}):\n{}", loopCounter, solverBoard.toString());

			/*
			Checks if there's more than one new action, indicating progress was made.
			There will always be at least one new action created because UpdateBoard 
			will always generate one.
			*/
			if(solverActions.size() <= previousActionsNum+1) {
				logger.debug("Setting progress flag to false.");
				progressMade = false;
			}

			previousActionsNum = solverActions.size();
		}

		logger.debug("Solver loop complete.");
		logger.debug("Initial Board:\n{}", inputBoard.toString());
		logger.debug("End Board:\n{}", solverBoard.toString());
		logger.debug("Actions:");
		for(int actionNum = 0; actionNum < solverActions.size(); actionNum++) {
			logger.debug("{}: {}", (actionNum+1), solverActions.get(actionNum).toString());
		}


		//SudokuSolver has now made all progress possible, checks if puzzle is valid
		checkPuzzle(solverBoard);

		//Puzzle is now assumed to be valid, and a SolverReport is compiled and returns it
		SolverReport report = new SolverReport(new SolverBoard(inputBoard), solverBoard, solverActions.toArray(new Action[0]));
		
		logger.debug("Returning SolverReport.");

		return report;
	}


	/*
	Confirms validity of solved puzzle. Does nothing if puzzle is valid, otherwise throws certain type of exception
	*/
	private void checkPuzzle(SolverBoard inputBoard) throws IncompletePuzzleException, IncorrectPuzzleException{
		logger.debug("Checking puzzle.");

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
						logger.debug("Found incomplete puzzle, throwing exception.");
						throw new IncompletePuzzleException("Incomplete Puzzle Exception: Unable to solve puzzle");
					}

					numCheck[targetCells[cellNum].getCellValue()-1]++;
				}

				//Checks to make sure all numCheck values are 1. If not, throws IncorrectPuzzleException
				for(int checkIter = 0; checkIter < numCheck.length; checkIter++) {
					if(numCheck[checkIter] != 1) {
						logger.debug("Found incorrect puzzle, throwing exception.");
						throw new IncorrectPuzzleException("IncorrectPuzzleException: Incorrect input or error in program. End puzzle is not correct");
					}
				}
			}
		}

		logger.debug("Puzzle valid.");
	}
}