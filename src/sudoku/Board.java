package sudoku;

/*
Board class used to pass board information between Sudoku app modules
*/
public class Board {
	/*
	SudokuSolver variables:
		-tiles: Contains all tiles on board
		-squares: Contains all squares on the board
		-rows: Contains all rows on the board
		-columns: Contains all columns on the board
		-regionGroups: Array containing all region groups (squares, rows, columns)
	*/
	private Tile[] boardTiles;
	private Region[] squares;
	private Region[] rows;
	private Region[] columns;
	private Region[][] regionGroups;

	/*
	Constructor for Board
	*/
	public Board(int[] rawBoard) {
		//Initializes all member variables
		boardTiles = new Tile[81];
		squares = new Region[9];
		rows = new Region[9];
		columns = new Region[9];

		regionGroups = new Region[3][];
		regionGroups[0] = squares;
		regionGroups[1] = rows;
		regionGroups[2] = columns;

		//Fills tiles with rawBoard
		fillTiles(rawBoard);

		//Fills regions with tiles
		fillRegions();
	}

	/*
	Returns the value of a tile in the board
	*/
	public int getTile(int tileNum) {
		return boardTiles[tileNum].finalNum;
	}

	/*
	Returns an integer array containing the values of every tile on the board
	*/
	public int[] getBoardValues() {
		int[] boardValues = new int[81];

		for(int tileNum = 0; tileNum < boardTiles.length; tileNum++) {
			boardValues[tileNum] = boardTiles[tileNum].finalNum;
		}

		return boardValues;
	}

	/*
	Returns an integer array containing all values in selected square 
	*/
	public int[] getSquare(int squareNum) {
		return getRegion(columns[squareNum]);
	}

	/*
	Returns an integer array containing all values in selected row 
	*/
	public int[] getRow(int rowNum) {
		return getRegion(columns[rowNum]);
	}

	/*
	Returns an integer array containing all values in selected column 
	*/
	public int[] getColumn(int columnNum) {
		return getRegion(columns[columnNum]);
	}

	/*
	Returns an integer array containing all values in selected region
	*/
	private int[] getRegion(Region targetRegion) {
		int[] regionValues = new int[9];

		for(int tileNum = 0; tileNum < targetRegion.regionTiles.length; tileNum++) {
			regionValues[tileNum] = targetRegion.regionTiles[tileNum].finalNum;
		}

		return regionValues;
	}
	/*
	Fills the tiles array with tiles created by the passed integer array
	*/
	private void fillTiles(int[] rawBoard) {
		for(int tileNum = 0; tileNum < boardTiles.length; tileNum++) {
			boardTiles[tileNum] = new Tile(rawBoard[tileNum]);
		}
	}

	/*
	Assigns tiles to the appropriate regions
	*/
	private void fillRegions() {
		//Sets up squares and adds tiles
		for(int squareNum = 0; squareNum < squares.length; squareNum++) {
			squares[squareNum] = new Region();
		}

		for(int tileNum = 0; tileNum < boardTiles.length; tileNum++) {
			squares[tileNum/9].addTile(boardTiles[tileNum]); 
		}


		//Sets up rows and adds tiles
		for(int rowNum = 0; rowNum < rows.length; rowNum++) {
			rows[rowNum] = new Region();
		}

		for(int rowNum = 0; rowNum < rows.length; rowNum++) {
			for(int rowTilesNum = 0; rowTilesNum < rows[rowNum].regionTiles.length; rowTilesNum++) {
				//Various forumla to assign tiles to rows
				int tileNum = 0;

				if(rowNum < 3) {
					tileNum += 0;
				} else if(rowNum < 6) {
					tileNum += 27;
				} else if(rowNum < 9) {
					tileNum += 54;
				}

				if(rowNum%3 == 0) {
					tileNum += 0;
				} else if(rowNum%3 == 1) {
					tileNum += 3;
				} else if(rowNum%3 == 2) {
					tileNum += 6;
				}

				if(rowTilesNum < 3) {
					tileNum += 0; 
				} else if(rowTilesNum < 6) {
					tileNum += 6;
				} else if(rowTilesNum < 9) {
					tileNum += 12;
				}

				tileNum += rowTilesNum;

				//Assigns tile to row
				rows[rowNum].addTile(boardTiles[tileNum]);
			}
		}

		//Sets up columns and adds tiles
		for(int columnnNum = 0; columnnNum < columns.length; columnnNum++) {
			columns[columnnNum] = new Region();
		}

		for(int columnnNum = 0; columnnNum < columns.length; columnnNum++) {
			for(int columnTileNum = 0; columnTileNum < columns[columnnNum].regionTiles.length; columnTileNum++) {
				//Various forumla to assign tiles to columns
				int tileNum = 0;

				if(columnTileNum < 3) {
					tileNum += 0;
				} else if(columnTileNum < 6) {
					tileNum += 27;
				} else if(columnTileNum < 9) {
					tileNum += 54;
				}

				if(columnTileNum%3 == 0) {
					tileNum += 0;
				} else if(columnTileNum%3 == 1) {
					tileNum += 3;
				} else if(columnTileNum%3 == 2) {
					tileNum += 6;
				}

				if(columnnNum < 3) {
					tileNum += 0; 
				} else if(columnnNum < 6) {
					tileNum += 6;
				} else if(columnnNum < 9) {
					tileNum += 12;
				}

				tileNum += columnnNum;

				//Assigns tile to row
				columns[columnnNum].addTile(boardTiles[tileNum]);
			}
		}
	}

	/*
	Private class representing a Board Region (Square, Row or Column)
	*/
	private class Region {
		/*
		Region variables:
			-regionTiles: All tiles contained in this region
		*/
		public Tile[] regionTiles;

		public Region() {
			//Initializes regionTiles to hold 9 tiles
			regionTiles = new Tile[9];
		}

		/*
		Adds new tile to Region
		*/
		public void addTile(Tile newTile) {
			for(int tileNum = 0; tileNum < regionTiles.length; tileNum++) {
				if(regionTiles[tileNum] == null) {
					regionTiles[tileNum] = newTile;
					return;
				}
			}

			//Thrown if Region is full
			throw new RuntimeException("RegionOverfilled: Attempted to add new tile to filled region");
		}

		public String toString() {
			//Declares and initializes output string
			String output = "[";

			//Fills output with all tile values
			for(int tileNum = 0; tileNum < regionTiles.length; tileNum++) {
				output += regionTiles.toString();
				output += ", ";
			}

			output = output.substring(0, output.length()-2);

			return output;
		}
	}

	/*
	A board Tile containing one number
	*/
	private class Tile {
		/*
		Tile variables:
			finalNum: The number contained in a tile
		*/
		public int finalNum;

		public Tile(int finalNum) {
			this.finalNum = finalNum;
		}

		public String toString() {
			return String.valueOf(finalNum);
		}
	}
}