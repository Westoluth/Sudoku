package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

/*
An Action to remove a single number or an array of numbers from a Region's possible numbers
*/
public class RemoveRegionPossibleNums implements Action {
	/*
	RemoveRegionPossibleNums variables:
		-regionType: The type of region. (0 = squares, 1 = rows, 2 = columns)
		-regionId: The region number in inputBoard's regionType array
		-removedNums: The possible numbers to be removed from Cell
	*/

	int regionType;
	int regionId;
	int[] removedNums;

	/*
	Constructor to remove just one possible number from a SolverRegion 
	*/
	public RemoveRegionPossibleNums(int regionType, int regionId, int removedNum) {
		this.regionType = regionType;
		this.regionId = regionId;
		this.removedNums = new int[1];
		removedNums[0] = removedNum;
	}

	/*
	Constructor to remove multiple possible numbers from a SolverRegion
	*/
	public RemoveRegionPossibleNums(int regionType, int regionId, int[] removedNums) {
		this.regionType = regionType;
		this.regionId = regionId;
		this.removedNums = removedNums;
	}

	/*
	Removes removedNums from the region of regionType at regionId
	*/
	public void applyAction(SolverBoard inputBoard) {
		//Finds targetRegion bsaed on regionType and regionId
		SolverRegion targetRegion = inputBoard.getRegion(regionType, regionId);

		//Removes all numbers in removedNums from targetRegion's possibleNums
		for(int removedNumsIt = 0; removedNumsIt < removedNums.length; removedNumsIt++) {
			targetRegion.removePossibleNum(removedNums[removedNumsIt]);
		}
	}
}