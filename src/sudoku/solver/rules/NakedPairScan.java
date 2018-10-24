package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;
import java.util.ArrayList;
import java.util.List;

public class NakedPairScan implements Rule {
	public Action[] applyRule(SolverBoard inputBoard) {
		//Declares action list
		List<Action> actionList = new ArrayList<Action>();

		//Iterates through every region type
		for(int regionType = 0; regionType < 3; regionType++) {
			//Iterates through all regions of that type
			for(int regionId = 0; regionId < 9; regionId++) {
				//Sets target region to current region
				SolverRegion targetRegion = inputBoard.getRegion(regionType, regionId);
				SolverCell[] targetCells = targetRegion.getCells();

				//Iterates through every cell 
				for(int cellNum = 0; cellNum < targetCells.length; cellNum++) {
					
				}
			}
		}

		//Converts Action list to an array and returns it
		Action[] actionArray = new Action[actionList.size()];
		actionArray = actionList.toArray(actionArray);
		return actionArray;
	}
}