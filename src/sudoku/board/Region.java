package sudoku.board;

import java.util.Arrays;

/*
Class representing a Board Region (Square, Row or Column)
*/
public class Region {
	/*
	Region variables:
		-regionCells: All cells contained in this region
	*/
	protected Cell[] regionCells;

	protected Region() {
		//Initializes regionCells to hold 9 cells
		regionCells = createRegionCells();
	}

	/*--------------------------------------------------------------------------------
	// Public Getter Methods 
	--------------------------------------------------------------------------------*/
	
	/*
	Returns a Cell at number cellNum(0-8) in the board
	*/
	public Cell getCell(int cellNum) {
		return regionCells[cellNum];
	}

	/*
	Returns the array regionCells
	*/
	public Cell[] getCells() {
		return regionCells;
	}

	public String toString() {
		return Arrays.toString(regionCells);
	}

	/*--------------------------------------------------------------------------------
	// Protected Setup Functions
	--------------------------------------------------------------------------------*/

	/*
	Returns a new Cell Array of size 9
	*/
	protected Cell[] createRegionCells() {
		return new Cell[9];
	}

	/*
	Adds new cell to Region
	*/
	protected void addCell(Cell newCell) {
		for(int cellNum = 0; cellNum < regionCells.length; cellNum++) {
			if(regionCells[cellNum] == null) {
				regionCells[cellNum] = newCell;
				return;
			}
		}

		//Thrown if Region is full
		throw new RuntimeException("RegionOverfilled: Attempted to add new cell to filled region");
	}
}