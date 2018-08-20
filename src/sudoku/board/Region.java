package sudoku.board;

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
		regionCells = new Cell[9];
	}

	/*--------------------------------------------------------------------------------
	// Public Getter Methods 
	--------------------------------------------------------------------------------*/

	/*
	Returns the array regionCells
	*/
	public Cell[] getCells() {
		return regionCells;
	}

	public String toString() {
		//Declares and initializes output string
		String output = "[";

		//Fills output with all cell values
		for(int cellNum = 0; cellNum < regionCells.length; cellNum++) {
			output += regionCells.toString();
			output += ", ";
		}

		output = output.substring(0, output.length()-2);

		return output;
	}

	/*--------------------------------------------------------------------------------
	// Protected Setup Functions
	--------------------------------------------------------------------------------*/

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