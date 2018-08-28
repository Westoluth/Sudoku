package sudoku.solver.solvercontext;

import sudoku.board.*;

/*
Class representing a board region (Square, Row or Column) in the SudokuSolver context
*/
public class SolverRegion extends Region{
	/*
	SolverRegion variables:
		-regionPossibleNums: Array of size 9. If value is true, corresponding number is a possibility in this region. 
			If value is false, number is not a possibility in this region.
	*/
	protected boolean[] regionPossibleNums;

	protected SolverRegion() {
		regionPossibleNums = new boolean[9];

		for(int regionPossibleNumsNum = 0; regionPossibleNumsNum < regionPossibleNums.length; regionPossibleNumsNum++) {
			regionPossibleNums[regionPossibleNumsNum] = true;
		}
	}

	/*--------------------------------------------------------------------------------
	// Public Getter Methods 
	--------------------------------------------------------------------------------*/

	/*
	Returns the SolverCell array regionCells
	*/
	public SolverCell[] getCells() {
		return (SolverCell[])regionCells;
	}

	/*
	Returns cellPossibleNums
	*/
	public boolean[] getRegionPossibleNums() {
		return regionPossibleNums;
	}

	/*--------------------------------------------------------------------------------
	// Public Management Functions
	--------------------------------------------------------------------------------*/

	/*
	Updates regionPossibleNums, then updates cellPossibleNums of each cell in region
	*/
	public void updatePossibleNums() {
		updateRegionPossibleNums();
		updateCellPossibleNums();
	}

	/*
	Removes a possible number from regionPossibleNums.
	possibleNum(0-9) corresponds to a number being a possibility in this board region.
	*/
	public void removePossibleNum(int possibleNum) {
		regionPossibleNums[possibleNum-1] = false;
	}

	/*--------------------------------------------------------------------------------
	// Private Management Functions
	--------------------------------------------------------------------------------*/

	/*
	Removes values already present in region from regionPossibleNums
	*/
	private void updateRegionPossibleNums() {
		//Iterates through every cell in region
		for(int cellNum = 0; cellNum < regionCells.length; cellNum++) {
			//If finalized number has been found, remove it from Region's possible nums
			if(regionCells[cellNum].getCellValue() != 0) {
				regionPossibleNums[regionCells[cellNum].getCellValue()-1] = false;
			}
		}
	}

	/*
	Removes values not present in regionPossibleNums from cellPossibleNums
	*/
	private void updateCellPossibleNums() {
		//Iterates through every cell in region and checks if cell is finalized
		for(int cellNum = 0; cellNum < regionCells.length; cellNum++) {
			if(regionCells[cellNum].getCellValue() == 0) {
				//Removes all numbers already present in region from cells possible nums
				for(int possibleNumsNum = 0; possibleNumsNum < regionPossibleNums.length; possibleNumsNum++) {
					((SolverCell)regionCells[cellNum]).cellPossibleNums[possibleNumsNum] = regionPossibleNums[possibleNumsNum] && ((SolverCell)regionCells[cellNum]).cellPossibleNums[possibleNumsNum];
				}
			}
		}
	}


}