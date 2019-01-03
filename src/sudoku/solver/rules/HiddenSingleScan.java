package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;

import java.util.ArrayList;
import java.util.List;

/*
A Rule to detect Hidden Singles in the board
*/
public class HiddenSingleScan implements Rule {
	public Action[] applyRule(SolverBoard inputBoard) {
		//Declares action list
		List<Action> actionList = new ArrayList<Action>();

		//Iterates through every region type
		for(int regionType = 0; regionType < 3; regionType++) {
			//Iterates through all regions of that type
			for(int regionId = 0; regionId < 9; regionId++) {
				//Sets target region to current region
				SolverRegion targetRegion = inputBoard.getRegion(regionType, regionId);

				//Iterates through all possible numbers of region
				for(int regionPossibleNumIter = 0; regionPossibleNumIter < targetRegion.getRegionPossibleNums().length; regionPossibleNumIter++) {
					//Checks if the selected possible number is still being considered in the region
					if(targetRegion.getRegionPossibleNums()[regionPossibleNumIter]) {
						//Sets targetCells to cells of current region
						SolverCell[] targetCells = targetRegion.getCells();

						//Declares int to track number of times selected possible number is valid in a cell
						int numCount = 0;

						//Declares int to track location of the possible number if located
						int cellId = -1;

						//Iterates through every cell and checks if selected possible number is valid
						for(int cellNum = 0; cellNum < targetCells.length; cellNum++) {
							//Sets targetCell to currently selected targetCells cell
							SolverCell targetCell = targetCells[cellNum];

							//Checks if regionPossibleNumIter is still being considered in targetCell
							if(targetCell.getCellPossibleNums()[regionPossibleNumIter]) {
								numCount++;
								cellId = targetCell.getCellId();
							}
						}

						//Checks if only one instance of regionPossibleNumIter was found, indicating a hidden single
						if(numCount == 1) {
							actionList.add(new SetCellValue(cellId, regionPossibleNumIter+1));
						}
					}
				}
			}
		}

		//Converts Action list to an array and returns it
		Action[] actionArray = new Action[actionList.size()];
		actionArray = actionList.toArray(actionArray);
		return actionArray;
	}

	/*
	String interpretation of Rule
	*/
	public String toString() {
		return "HiddenSingleScan: Detects hidden singles on the game board";
	}
}