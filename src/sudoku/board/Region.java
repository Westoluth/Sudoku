package sudoku.board;

/*
Class representing a Board Region (Square, Row or Column)
*/
public class Region {
	/*
	Region variables:
		-regionCells: All cells contained in this region
	*/
	public Cell[] regionCells;

	public Region() {
		//Initializes regionCells to hold 9 cells
		regionCells = new Cell[9];
	}

	/*
	Adds new cell to Region
	*/
	public void addCell(Cell newCell) {
		for(int cellNum = 0; cellNum < regionCells.length; cellNum++) {
			if(regionCells[cellNum] == null) {
				regionCells[cellNum] = newCell;
				return;
			}
		}

		//Thrown if Region is full
		throw new RuntimeException("RegionOverfilled: Attempted to add new cell to filled region");
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
}