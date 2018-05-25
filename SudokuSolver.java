import java.util.Arrays;

public class SudokuSolver {
	/*
	SudokuSolver variables:
		-tiles: Contains all tiles on board
		-squares: Contains all squares on the board
		-rows: Contains all rows on the board
		-columns: Contains all columns on the board
	*/
	Tile[] tiles;
	Segment[] squares;
	Segment[] rows;
	Segment[] columns;
	Segment[][] segmentGroups;

	public SudokuSolver() {
	}

	/*
	Inputs new boardValues to SudokuSolver
	*/
	public void updateGame(int[] boardValues) {
		setupBoard(boardValues);
	}

	/*
	Handles solving of the board
	*/
	public int[] solve() {
		//progressMade indicates whether progress was made in this most recent iteration of the loop or if a dead end has been reached
		boolean progressMade;

		//DEBUG//
		System.out.println("Initial board status: ");
		for(int i = 0; i < tiles.length; i++) {
			System.out.println("Tile #" + i + ": " + Arrays.toString(tiles[i].possibleNums) + "  finalNum: " + tiles[i].finalNum);
		}
		System.out.println();
		//DEBUG//

		while(true) {
			//Resets progressMade to false
			progressMade = false;

			//Calls simpleScan on each segment of each segment type
			for(int segmentGroupNum = 0; segmentGroupNum < segmentGroups.length; segmentGroupNum++) {
				for(int segmentNum = 0; segmentNum < segmentGroups[segmentGroupNum].length; segmentNum++) {
					segmentGroups[segmentGroupNum][segmentNum].hiddenSingleScan();
				}
			}

			//Goes through all tiles that have one possible number remaining and finalizes them
			for(int tileNum = 0; tileNum < tiles.length; tileNum++) {
					//DEBUG//
				System.out.println("Tile #" + tileNum + ": " + Arrays.toString(tiles[tileNum].possibleNums) + "  finalNum: " + tiles[tileNum].finalNum);
					//DEBUG//

				boolean progressCheck = tiles[tileNum].checkFinal();

				if(progressCheck) {
					progressMade = true;
				}
			}

			//DEBUG//
			System.out.println("Current puzzle status: \n");
			for(int i = 0; i < rows.length; i++) {
				System.out.println(rows[i].toString());
			}
			System.out.println("");
			//DEBUG//

			//Checks if progress was made
			if(!progressMade) {
				//Checks if problem solved, or something went wrong
				if(checkPuzzle()) {
					System.out.println("Puzzle solved!");
					return packagePuzzle();
				} else {
					System.out.println("Something went wrong, unable to continue");
					throw new RuntimeException();
				}
			}
		}
	}

	/*
	Sets up board
	*/
	private void setupBoard(int[] boardValues) {
		//Initializes variables
		tiles = new Tile[81];
		squares = new Segment[9];
		rows = new Segment[9];
		columns = new Segment[9];
		segmentGroups = new Segment[3][9];
		segmentGroups[0] = squares;
		segmentGroups[1] = rows;
		segmentGroups[2] = columns;

		//Sets up tiles
		for(int tileNum = 0; tileNum < tiles.length; tileNum++) {
			tiles[tileNum] = new Tile(boardValues[tileNum]);
		}

		//Sets up squares and adds tiles
		for(int squareNum = 0; squareNum < squares.length; squareNum++) {
			squares[squareNum] = new Segment();
		}

		for(int tileNum = 0; tileNum < tiles.length; tileNum++) {
			squares[tileNum/9].addTile(tiles[tileNum]); 
		}


		//Sets up rows and adds tiles
		for(int rowNum = 0; rowNum < rows.length; rowNum++) {
			rows[rowNum] = new Segment();
		}

		for(int rowNum = 0; rowNum < rows.length; rowNum++) {
			for(int rowTilesNum = 0; rowTilesNum < rows[rowNum].tiles.length; rowTilesNum++) {
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
				rows[rowNum].addTile(tiles[tileNum]);
			}
		}

		//Sets up columns and adds tiles
		for(int columnnNum = 0; columnnNum < columns.length; columnnNum++) {
			columns[columnnNum] = new Segment();
		}

		for(int columnnNum = 0; columnnNum < columns.length; columnnNum++) {
			for(int columnTileNum = 0; columnTileNum < columns[columnnNum].tiles.length; columnTileNum++) {
				//Various forumla to assign tiles to rows
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
				columns[columnnNum].addTile(tiles[tileNum]);
			}
		}
	}


	/*
	Confirms validity of solved puzzle. Returns true if puzzle is valid, false otherwise
	*/
	private boolean checkPuzzle() {
		for(int segmentGroupNum = 0; segmentGroupNum < segmentGroups.length; segmentGroupNum++) {
			for(int segmentNum = 0; segmentNum < segmentGroups[segmentGroupNum].length; segmentNum++) {
				//Creates array to hold quantity of each number found
				int[] numCheck = new int[9];

				//Fills numCheck
				for(int tileNum = 0; tileNum < segmentGroups[segmentGroupNum][segmentNum].tiles.length; tileNum++) {
					numCheck[segmentGroups[segmentGroupNum][segmentNum].tiles[tileNum].finalNum-1]++;
				}

				//Checks to make sure all numCheck values are 1. If not, returns false
				for(int x = 0; x < numCheck.length; x++) {
					if(numCheck[x] != 1) {
						return false;
					}
				}
			}
		}

		//Because no discrepancies have been detected, returns true
		return true;
	}

	/*
	Converts puzzle array to an array of Ints
	*/
	private int[] packagePuzzle() {
		int[] puzzle = new int[81];

		for(int tileNum = 0; tileNum < tiles.length; tileNum++) {
			puzzle[tileNum] = tiles[tileNum].finalNum;
		}

		return puzzle;
	}

	/*
	Private class representing a board tile
	*/
	private class Tile {
		/*
		Tile variables:
			-possibleNums: Array of size 9. If value is true, corresponding number is a possibility. If value is false, number is not a possibility.
			-finalNum: Finalized number. If only one possibleNum remains, it becomes the finalNum. Default is set to 0, indicating no final number.
		*/
		public boolean[] possibleNums;
		public int finalNum;

		/*
		Creates new tile sets up possibleNums and finalNum
		*/
		public Tile(int assignedNum) {
			//Initializes variables
			possibleNums = new boolean[9];
			finalNum = assignedNum;

			//Determines whether tile is blank(assignedNum = 0) or filled(assignedNum = 1-9)
			if(assignedNum == 0) {
				for(int possibleNum = 0; possibleNum < possibleNums.length; possibleNum++) {
					possibleNums[possibleNum] = true;
				}
			} else {
				for(int possibleNum = 0; possibleNum < possibleNums.length; possibleNum++) {
					possibleNums[possibleNum] = false;
				}
			}
		}

		/*
		Checks if only one possible number remains for final. If so, finalizes tile by setting final num
		*/
		public boolean checkFinal() {
			//Boolean to check if progressCheck. Returned at end of function
			boolean progressCheck = false;

			//Count of available number options. count>1 indicates unsolved puzzle, 1 indicates solved, 0 should never be reached
			int count = 0;

			for(int possibleNum = 0; possibleNum < possibleNums.length; possibleNum++) {
				if(possibleNums[possibleNum]) {
					count++;
				}
			}

			//Checks if count and if it is sets finalNum to only remaining num
			if(count == 1) {
				progressCheck = true;
				for(int possibleNum = 0; possibleNum < possibleNums.length; possibleNum++) {
					if(possibleNums[possibleNum]) {
						finalNum = possibleNum+1;
						break;
					}
				}

				for(int possibleNum = 0; possibleNum < possibleNums.length; possibleNum++) {
					possibleNums[possibleNum] = false;
				}
			}

			return progressCheck;
		}

		/*
		Returns string of value finalNum
		*/
		public String toString() {
			return String.valueOf(finalNum);
		}
	}

	/*
	Private class representing 9 tiles; either a square, row or columnn.
	*/
	private class Segment {
		/*
		Segment variables:
			-possibleNums: Array of size 9. If value is true, corresponding number is a possibility in this segment. If value is false, number is not a possibility in this segment.
			-tiles: Array containing all tiles in segment
		*/
		boolean[] possibleNums;
		Tile[] tiles;

		/*
		Initializes Segment variables
		*/
		public Segment() {
			//Initializes variables
			possibleNums = new boolean[9];
			tiles = new Tile[9];

			//Sets all possibleNums to true
			for(int possibleNum = 0; possibleNum < possibleNums.length; possibleNum++) {
				possibleNums[possibleNum] = true;
			}
		}

		/*
		Adds new tile to Segment
		*/
		public void addTile(Tile newTile) {
			for(int tileNum = 0; tileNum < tiles.length; tileNum++) {
				if(tiles[tileNum] == null) {
					tiles[tileNum] = newTile;
					return;
				}
			}

			//Thrown if Segment is full
			throw new SegmentOverfilled();
		}

		/*
		Checks which numbers are present in this segment and removes those numbers from possibleNumbers in all tiles of the segment.
		Reveals Hidden Singles
		*/
		public void hiddenSingleScan() {
			//Updates possible nums for segment
			for(int tileNum = 0; tileNum < tiles.length; tileNum++) {
				if(tiles[tileNum].finalNum != 0) {
					possibleNums[tiles[tileNum].finalNum-1] = false;
				}
			}

			//Updates possibleNums of all tiles
			for(int tileNum = 0; tileNum < tiles.length; tileNum++) {
				for(int possibleNum = 0; possibleNum < tiles[tileNum].possibleNums.length; possibleNum++) {
					if(possibleNums[possibleNum] == false) {
						tiles[tileNum].possibleNums[possibleNum] = false;
					}
				}
			}
		}

		/*
		Returns array of tiles in toString() form
		*/
		public String toString() {
			return Arrays.toString(tiles);
		}


		//Suppresses serial warnings
		@SuppressWarnings("serial")

		/*
		Exception thrown if someone attempts to add to a full Segment
		*/
		private class SegmentOverfilled extends RuntimeException {
		    //Parameterless Constructor
		    public SegmentOverfilled() {}

		    //Constructor that accepts a message
		    public SegmentOverfilled(String message) {
		        super(message);
		    }
		}
	}
}