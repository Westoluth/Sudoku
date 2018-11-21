package sudoku.board;

/*
Board class used to pass board information between Sudoku app modules
*/
public class Board {
	/*
	Board variables:
		-boardcells: Contains all cells on board
		-squares: Contains all squares on the board
		-rows: Contains all rows on the board
		-columns: Contains all columns on the board
		-regionGroups: Array containing all region groups (squares, rows, columns)
	*/
	protected Cell[] boardCells;
	protected Region[] squares;
	protected Region[] rows;
	protected Region[] columns;
	protected Region[][] regionGroups;

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
	Returns a Cell at number cellNum(0-80) in the board
	*/
	public Cell getCell(int cellNum) {
		return boardCells[cellNum];
	}

	/*
	Returns a Cell array containing every cell on the board
	*/
	public Cell[] getBoard() {
		return boardCells;
	}

	/*
	Returns a Region containing all values in selected square at squareNum(0-8)
	*/
	public Region getSquare(int squareNum) {
		return squares[squareNum];
	}

	/*
	Returns a Region containing all values in selected row at rowNum(0-8)
	*/
	public Region getRow(int rowNum) {
		return rows[rowNum];
	}

	/*
	Returns a Region containing all values in selected row at columnNum(0-8)
	*/
	public Region getColumn(int columnNum) {
		return columns[columnNum];
	}

	/*
	Returns a specified Region of the Board. 
	regionType indicates the type of Region (0 = squares, 1 = rows, 2 = columns).
	regionId indicates the number it is in it's Region array.
	*/
	public Region getRegion(int regionType, int regionId) {
		return regionGroups[regionType][regionId];
	}

	/*
	Returns the integer value of a cell at number cellNum(0-80) in the board
	*/
	public int getCellValue(int cellNum) {
		return boardCells[cellNum].cellValue;
	}

	/*
	Returns an integer array containing the values of every cell on the board
	*/
	public int[] getBoardValues() {
		int[] boardValues = new int[81];

		for(int cellNum = 0; cellNum < boardCells.length; cellNum++) {
			boardValues[cellNum] = boardCells[cellNum].cellValue;
		}

		return boardValues;
	}

	/*
	Returns an integer array containing all values in selected square at squareNum(0-8)
	*/
	public int[] getSquareValues(int squareNum) {
		return getRegionValues(squares[squareNum]);
	}

	/*
	Returns an integer array containing all values in selected row at rowNum(0-8)
	*/
	public int[] getRowValues(int rowNum) {
		return getRegionValues(rows[rowNum]);
	}

	/*
	Returns an integer array containing all values in selected column at columnNum(0-8)
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
			regionValues[cellNum] = targetRegion.regionCells[cellNum].cellValue;
		}

		return regionValues;
	}

	/*--------------------------------------------------------------------------------
	// Private Setup Functions
	--------------------------------------------------------------------------------*/

	protected Cell createCell(int cellValue, int cellId) {
		return new Cell(cellValue, cellId);
	}

	protected Region createRegion() {
		return new Region();
	}

	/*
	Fills the cells array with cells created from the passed integer array
	*/
	protected void fillCells(int[] rawBoard) {
		for(int cellNum = 0; cellNum < boardCells.length; cellNum++) {
			boardCells[cellNum] = createCell(rawBoard[cellNum], cellNum);
		}
	}

	/*
	Assigns cells in boardCells to the appropriate regions
	*/
	protected void fillRegions() {
		//Sets up squares and adds cells
		for(int squareNum = 0; squareNum < squares.length; squareNum++) {
			squares[squareNum] = createRegion();
		}

		for(int cellNum = 0; cellNum < boardCells.length; cellNum++) {
			squares[cellNum/9].addCell(boardCells[cellNum]); 
		}


		//Sets up rows and adds cells
		for(int rowNum = 0; rowNum < rows.length; rowNum++) {
			rows[rowNum] = createRegion();
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
			columns[columnnNum] = createRegion();
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
}