package sudoku.solver.rules;

import sudoku.solver.solvercontext.*;
import sudoku.solver.actions.*;
import java.util.Arrays;
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

				//Creates list to store all pairs found and list to store all naked pairs found
				List<int[]> pairList = new ArrayList<>();
				List<int[]> nakedPairList = new ArrayList<>();

				/*
				Iterates through every cell and checks if cell only has a pair of possible numbers
				If so, it adds it to pairList.
				*/
				for(int cellNum = 0; cellNum < targetCells.length; cellNum++) {
					//Sets target cell
					SolverCell targetCell = targetCells[cellNum];

					//If a pair is found, adds it to pairList
					if(targetCell.countCellPossibleNums() == 2) {
						pairList.add(targetCell.getIntCellPossibleNums());
					}
				}

				/*
				Takes list of pairs found in region and checks for a pair of pairs.
				If one is found, that indicates a naked pair and that pair can be removed
				as possible candidates from all cells in the region that do not possess ONLY
				the pair as possible candidates.
				*/
				while(!pairList.isEmpty()){
					//Selects the first pair in the list as target
					int[] targetPair = pairList.remove(0);
					int targetCount = 1; //Counting the targetPair already removed

					//Checks the rest of the list for another instance of targetPair
					int pairIter = 0;
					while(pairIter < pairList.size()) {
						if(Arrays.equals(pairList.get(pairIter), targetPair)) {
							pairList.remove(pairIter);
							targetCount++;
						} else {
							pairIter++;
						}
					}

					if(targetCount == 2) {
						nakedPairList.add(targetPair);
					}
				}

				/*
				Creates actions to remove each naked pair from cells with one or both of the 
				numbers from the pair, but not only the pair.
				*/
				//Iterates through every naked pair in nakedPair list
				for(int pairIter = 0; pairIter < nakedPairList.size(); pairIter++) {
					//Sets target pair
					int[] targetPair = nakedPairList.get(pairIter);
					
					//Iterates through every cell in region
					for(int cellNum = 0; cellNum < targetCells.length; cellNum++) {
						//Sets target cell
						SolverCell targetCell = targetCells[cellNum];

						//Confirms the targetCell doesn't have only the pair as possible nums
						if(!Arrays.equals(targetCell.getIntCellPossibleNums(), targetPair)) {
							/*
							Determines which elements of the naked pair are present in the possible nums
							of this cell. This is done instead of removing all possible nums, even those
							that aren't present, to create cleaner actions that aren't doing redundant work.
							*/
							//Creates list to store pair numbers present in the possible nums of the cell.
							List<Integer> presentNums = new ArrayList<>();

							//Checks all pair numbers agains the cell
							for(int pairNumIter = 0; pairNumIter < targetPair.length; pairNumIter++) {
								if(targetCell.getCellPossibleNums()[targetPair[pairNumIter]-1]) {
									presentNums.add(targetPair[pairNumIter]);
								}								
							}

							//If any overlapped numbers were found, remove them
							if(presentNums.size() > 0) {
								//Converts present nums from an Integer list to an int array
								int[] removedNums = new int[presentNums.size()];

								for(int numIter = 0; numIter < removedNums.length; numIter++) {
									removedNums[numIter] = presentNums.get(numIter).intValue();
								}

								//Creates action removing removedNums from targetCell
								actionList.add(new RemoveCellPossibleNums(targetCell.getCellId(), removedNums));
							}
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
}