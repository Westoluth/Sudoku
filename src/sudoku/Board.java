package sudoku;

/*
Board class used to pass board information between Sudoku app modules
*/
public class Board {
	/*
	SudokuSolver variables:
		-boardcells: Contains all cells on board
		-squares: Contains all squares on the board
		-rows: Contains all rows on the board
		-columns: Contains all columns on the board
		-regionGroups: Array containing all region groups (squares, rows, columns)
	*/
	private Cell[] boardCells;
	private Region[] squares;
	private Region[] rows;
	private Region[] columns;
	private Region[][] regionGroups;

	/*
	Constructor for Board
	*/
	public Board(int[] rawBoard) {
		//Initializes all member variables
		boardCells = new Cell[81];
		squares = new Region[9];
		rows = new Region[9];
		columns = new Region[9];

		regionGroups = new Region[3][];
		regionGroups[0] = squares;
		regionGroups[1] = rows;
		regionGroups[2] = columns;

		//Fills cells with rawBoard
		fillCells(rawBoard);

		//Fills regions with cells
		fillRegions();
	}

	/*--------------------------------------------------------------------------------
	// Public Getter Methods 
	--------------------------------------------------------------------------------*/

	/*
	Returns the integer value of a cell in the board
	*/
	public int getCellValue(int cellNum) {
		return boardCells[cellNum].finalNum;
	}

	/*
	Returns an integer array containing the values of every cell on the board
	*/
	public int[] getBoardValues() {
		int[] boardValues = new int[81];

		for(int cellNum = 0; cellNum < boardCells.length; cellNum++) {
			boardValues[cellNum] = boardCells[cellNum].finalNum;
		}

		return boardValues;
	}

	/*
	Returns an integer array containing all values in selected square 
	*/
	public int[] getSquareValues(int squareNum) {
		return getRegionValues(columns[squareNum]);
	}

	/*
	Returns an integer array containing all values in selected row 
	*/
	public int[] getRowValues(int rowNum) {
		return getRegionValues(columns[rowNum]);
	}

	/*
	Returns an integer array containing all values in selected column 
	*/
	public int[] getColumnValues(int columnNum) {
		return getRegionValues(columns[columnNum]);
	}

	/*--------------------------------------------------------------------------------
	// Private Getter Helper Functions
	--------------------------------------------------------------------------------*/

	/*
	Returns an integer array containing all values in selected region
	*/
	private int[] getRegionValues(Region targetRegion) {
		int[] regionValues = new int[9];

		for(int cellNum = 0; cellNum < targetRegion.regionCells.length; cellNum++) {
			regionValues[cellNum] = targetRegion.regionCells[cellNum].finalNum;
		}

		return regionValues;
	}

	/*--------------------------------------------------------------------------------
	// Private Setup Functions
	--------------------------------------------------------------------------------*/

	/*
	Fills the cells array with cells created from the passed integer array
	*/
	private void fillCells(int[] rawBoard) {
		for(int cellNum = 0; cellNum < boardCells.length; cellNum++) {
			boardCells[cellNum] = new Cell(rawBoard[cellNum]);
		}
	}

	/*
	Assigns cells in boardCells to the appropriate regions
	*/
	private void fillRegions() {
		//Sets up squares and adds cells
		for(int squareNum = 0; squareNum < squares.length; squareNum++) {
			squares[squareNum] = new Region();
		}

		for(int cellNum = 0; cellNum < boardCells.length; cellNum++) {
			squares[cellNum/9].addCell(boardCells[cellNum]); 
		}


		//Sets up rows and adds cells
		for(int rowNum = 0; rowNum < rows.length; rowNum++) {
			rows[rowNum] = new Region();
		}

		for(int rowNum = 0; rowNum < rows.length; rowNum++) {
			for(int rowCellsNum = 0; rowCellsNum < rows[rowNum].regionCells.length; rowCellsNum++) {
				//Various forumla to assign cells to rows
				int cellNum = 0;

				if(rowNum < 3) {
					cellNum += 0;
				} else if(rowNum < 6) {
					cellNum += 27;
				} else if(rowNum < 9) {
					cellNum += 54;
				}

				if(rowNum%3 == 0) {
					cellNum += 0;
				} else if(rowNum%3 == 1) {
					cellNum += 3;
				} else if(rowNum%3 == 2) {
					cellNum += 6;
				}

				if(rowCellsNum < 3) {
					cellNum += 0; 
				} else if(rowCellsNum < 6) {
					cellNum += 6;
				} else if(rowCellsNum < 9) {
					cellNum += 12;
				}

				cellNum += rowCellsNum;

				//Assigns cell to row
				rows[rowNum].addCell(boardCells[cellNum]);
			}
		}

		//Sets up columns and adds cells
		for(int columnnNum = 0; columnnNum < columns.length; columnnNum++) {
			columns[columnnNum] = new Region();
		}

		for(int columnnNum = 0; columnnNum < columns.length; columnnNum++) {
			for(int columnCellNum = 0; columnCellNum < columns[columnnNum].regionCells.length; columnCellNum++) {
				//Various forumla to assign cells to columns
				int cellNum = 0;

				if(columnCellNum < 3) {
					cellNum += 0;
				} else if(columnCellNum < 6) {
					cellNum += 27;
				} else if(columnCellNum < 9) {
					cellNum += 54;
				}

				if(columnCellNum%3 == 0) {
					cellNum += 0;
				} else if(columnCellNum%3 == 1) {
					cellNum += 3;
				} else if(columnCellNum%3 == 2) {
					cellNum += 6;
				}

				if(columnnNum < 3) {
					cellNum += 0; 
				} else if(columnnNum < 6) {
					cellNum += 6;
				} else if(columnnNum < 9) {
					cellNum += 12;
				}

				cellNum += columnnNum;

				//Assigns cell to row
				columns[columnnNum].addCell(boardCells[cellNum]);
			}
		}
	}

	/*
	Private class representing a Board Region (Square, Row or Column)
	*/
	private class Region {
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

	/*
	A board Cell containing one number
	*/
	private class Cell {
		/*
		Cell variables:
			finalNum: The number contained in a cell
		*/
		public int finalNum;

		public Cell(int finalNum) {
			this.finalNum = finalNum;
		}

		public String toString() {
			return String.valueOf(finalNum);
		}
	}
}