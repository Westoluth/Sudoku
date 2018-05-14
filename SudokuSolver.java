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

	public SudokuSolver() {
	}

	/*
	Inputs new boardValues to SudokuSolver
	*/
	public void updateGame(int[] boardValues) {
		setupBoard(boardValues);
	}

	/*
	Solves board
	*/
	public int[] solve() {
		//Dummy code
		/*
		int[] finalBoardValues = new int[81];

		for(int i = 0; i < finalBoardValues.length; i++) {
			finalBoardValues[i] = tiles[i].finalNum;
		}

		return finalBoardValues;
		*/

		//progressMade indicates whether progress was made in this most recent iteration of the loop or if we have reached a dead end
		boolean progressMade;

		//Dumps initial board status
		System.out.println("Initial board status: ");
		for(int i = 0; i < tiles.length; i++) {
			System.out.println("Tile #" + i + ": " + Arrays.toString(tiles[i].possibleNums) + "  finalNum: " + tiles[i].finalNum);
		}
		System.out.println();

		while(true) {
			//Resets progressMade to false
			progressMade = false;

			//Calls solve on each segment of each segment type
			for(int i = 0; i < squares.length; i++) {
				squares[i].solve();
			}

			for(int i = 0; i < rows.length; i++) {
				rows[i].solve();
			}

			for(int i = 0; i < columns.length; i++) {
				columns[i].solve();
			}

			//Goes through every tile and checks if it has only one solution left
			for(int i = 0; i < tiles.length; i++) {

				//DEBUG//
				System.out.println("Tile #" + i + ": " + Arrays.toString(tiles[i].possibleNums) + "  finalNum: " + tiles[i].finalNum);

				//Count of available number options. count>1 indicates unsolved puzzle, 1 indicates solved, 0 should never be reached
				int count = 0;

				for(int x = 0; x < tiles[i].possibleNums.length; x++) {
					if(tiles[i].possibleNums[x]) {
						count++;
					}
				}

				//Checks if count and if it is sets finalNum to only remaining num
				if(count == 1) {
					progressMade = true;
					for(int x = 0; x < tiles[i].possibleNums.length; x++) {
						if(tiles[i].possibleNums[x]) {
							tiles[i].finalNum = x+1;
							break;
						}
					}
				}
			}

			//DEBUG//
			System.out.println("Current puzzle status: \n");
			for(int i = 0; i < rows.length; i++) {
				System.out.println(rows[i].toString());
			}
			System.out.println("");

			//Checks if progress was made
			if(!progressMade) {
				//Checks if problem solved, or something went wrong
				if(checkPuzzle()) {
					System.out.println("Puzzle solved!");
					return packagePuzzle();
				} else {
					System.out.println("Something went wrong");
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

		//Sets up tiles
		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile(boardValues[i]);
		}

		//Sets up squares and adds tiles
		for(int i = 0; i < squares.length; i++) {
			squares[i] = new Segment();
		}

		for(int i = 0; i < tiles.length; i++) {
			squares[i/9].addTile(tiles[i]); 
		}

		//DEBUG
		/*
		for(int i = 0; i < squares.length; i++) {
			System.out.println("Square " + i + ": " + Arrays.toString(squares[i].tiles));
		}
		System.out.println("");
		*/
		//DEBUG

		//Sets up rows and adds tiles
		for(int i = 0; i < rows.length; i++) {
			rows[i] = new Segment();
		}

		for(int i = 0; i < rows.length; i++) {
			for(int x = 0; x < rows[i].tiles.length; x++) {
				//Various forumla to assign tiles to rows
				int tileNum = 0;

				if(i < 3) {
					tileNum += 0;
				} else if(i < 6) {
					tileNum += 27;
				} else if(i < 9) {
					tileNum += 54;
				}

				if(i%3 == 0) {
					tileNum += 0;
				} else if(i%3 == 1) {
					tileNum += 3;
				} else if(i%3 == 2) {
					tileNum += 6;
				}

				if(x < 3) {
					tileNum += 0; 
				} else if(x < 6) {
					tileNum += 6;
				} else if(x < 9) {
					tileNum += 12;
				}

				tileNum += x;

				//Assigns tile to row
				rows[i].addTile(tiles[tileNum]);
			}
		}

		//DEBUG
		/*
		for(int i = 0; i < rows.length; i++) {
			System.out.println("Row " + i + ": " + Arrays.toString(rows[i].tiles));
		}
		System.out.println("");
		*/
		//DEBUG

		//Sets up columns and adds tiles
		for(int i = 0; i < columns.length; i++) {
			columns[i] = new Segment();
		}

		for(int i = 0; i < columns.length; i++) {
			for(int x = 0; x < columns[i].tiles.length; x++) {
				//Various forumla to assign tiles to rows
				int tileNum = 0;

				if(x < 3) {
					tileNum += 0;
				} else if(x < 6) {
					tileNum += 27;
				} else if(x < 9) {
					tileNum += 54;
				}

				if(x%3 == 0) {
					tileNum += 0;
				} else if(x%3 == 1) {
					tileNum += 3;
				} else if(x%3 == 2) {
					tileNum += 6;
				}

				if(i < 3) {
					tileNum += 0; 
				} else if(i < 6) {
					tileNum += 6;
				} else if(i < 9) {
					tileNum += 12;
				}

				tileNum += i;

				//Assigns tile to row
				columns[i].addTile(tiles[tileNum]);
			}
		}

		//DEBUG
		/*
		for(int i = 0; i < columns.length; i++) {
			System.out.println("Column " + i + ": " + Arrays.toString(columns[i].tiles));
		}
		System.out.println("");
		*/
		//DEBUG

	}


	private boolean checkPuzzle() {
		for(int y = 0; y < 3; y++) {
			//Assigns currentCheck to proper array of segments (Squares, Rows, Columns)
			Segment[] currentCheck;
			if(y == 0) {
				currentCheck = squares;
			} else if(y == 1) {
				currentCheck = rows;
			} else if(y == 3){
				currentCheck = columns;
			}

			for(int i = 0; i <  squares.length; i++) {
				//Creates array to hold quantity of each number found
				int[] numCheck = new int[9];

				//Fills numCheck
				for(int x = 0; x < squares[i].tiles.length; x++) {
					numCheck[squares[i].tiles[x].finalNum-1]++;
				}

				//Checks to make sure all numCheck values are 1
				for(int x = 0; x < numCheck.length; x++) {
					if(numCheck[x] != 1) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private int[] packagePuzzle() {
		int[] puzzle = new int[81];

		for(int i = 0; i < tiles.length; i++) {
			puzzle[i] = tiles[i].finalNum;
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
				for(int i = 0; i < possibleNums.length; i++) {
					possibleNums[i] = true;
				}
			} else {
				for(int i = 0; i < possibleNums.length; i++) {
					possibleNums[i] = false;
				}
			}
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
			for(int i = 0; i < possibleNums.length; i++) {
				possibleNums[i] = true;
			}
		}

		/*
		Adds new tile to Segment
		*/
		public void addTile(Tile newTile) {
			for(int i = 0; i < tiles.length; i++) {
				if(tiles[i] == null) {
					tiles[i] = newTile;
					return;
				}
			}

			//Thrown if Segment is full
			throw new SegmentOverfilled();
		}

		/*
		Checks which numbers are present in this segment and removes those numbers from possibleNumbers in all tiles
		*/
		public void solve() {
			//Updates possible nums for segment
			for(int i = 0; i < tiles.length; i++) {
				if(tiles[i].finalNum != 0) {
					possibleNums[tiles[i].finalNum-1] = false;
				}
			}

			//Updates possibleNums of all tiles
			for(int i = 0; i < tiles.length; i++) {
				for(int x = 0; x < tiles[i].possibleNums.length; x++) {
					if(possibleNums[x] == false) {
						tiles[i].possibleNums[x] = false;
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