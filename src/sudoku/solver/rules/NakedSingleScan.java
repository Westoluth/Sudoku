package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

import java.util.ArrayList;
import java.util.List;

/*
A Rule to detect Naked Singles in the board
*/
public class NakedSingleScan implements Rule {
	public Action[] applyRule(SolverBoard inputBoard) {
		//Declares shorthand variables
		SolverCell[] inputCells = inputBoard.getCells();

		//Declares action list
		List<Action> actionList = new ArrayList<Action>();

		//Iterates through every cell in inputBoard
		for(int cellNum = 0; cellNum < inputCells.length; cellNum++) {
			//Declares shorthand variables
			SolverCell targetCell = inputCells[cellNum];

			//Counts the number of possible numbers in each cell
			int possibleNumsTally = 0;

			for(int possNumIter = 0; possNumIter < targetCell.getCellPossibleNums().length; possNumIter++) {
				if(targetCell.getCellPossibleNums()[possNumIter]) {
					possibleNumsTally++;
				}
			}

			//If there is only one possible number left and the cell is not already final, the cell is final so add an action setting the cell value to actionList
			if(possibleNumsTally == 1 && targetCell.getCellValue() == 0) {
				for(int possNumIter = 0; possNumIter < targetCell.getCellPossibleNums().length; possNumIter++) {
					if(targetCell.getCellPossibleNums()[possNumIter]) {
						actionList.add(new SetCellValue(targetCell.getCellId(), possNumIter+1));
						break;
					}
				}
			}
		}

		//Converts Action list to an array and returns it
		Action[] actionArray = new Action[actionList.size()];
		actionArray = actionList.toArray(actionArray);
		return actionArray;
	}
}