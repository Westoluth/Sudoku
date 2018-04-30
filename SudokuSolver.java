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

	public void updateGame(int[] boardValues) {
		setupBoard(boardValues);
	}

	/*
	Solves board
	*/
	public int[] solve() {
		int[] finalBoardValues = new int[81];

		for(int i = 0; i < finalBoardValues.length; i++) {
			finalBoardValues[i] = tiles[i].finalNum;
		}

		return finalBoardValues;
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
		for(int i = 0; i < squares.length; i++) {
			System.out.println("Square " + i + ": " + Arrays.toString(squares[i].tiles));
		}
		System.out.println("");
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
		for(int i = 0; i < rows.length; i++) {
			System.out.println("Row " + i + ": " + Arrays.toString(rows[i].tiles));
		}
		System.out.println("");
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
		for(int i = 0; i < columns.length; i++) {
			System.out.println("Column " + i + ": " + Arrays.toString(columns[i].tiles));
		}
		System.out.println("");
		//DEBUG

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

		public Tile(int assignedNum) {
			//Initializes variables
			possibleNums = new boolean[9];
			finalNum = assignedNum;

			//Determines whether tile is blank(assignedNum = 0) or filled(assignedNum = 1-9)
			if(assignedNum == 0) {
				for(int i = 0; i < possibleNums.length; i++) {
					possibleNums[i] = false;
				}
			} else {
				for(int i = 0; i < possibleNums.length; i++) {
					possibleNums[i] = true;
				}
			}
		}

		public String toString() {
			return String.valueOf(finalNum);
		}
	}

	/*
	Private class representing 9 tiles; either a square, row or columnn.
	*/
	private class Segment {
		/*
		Tile variables:
			-possibleNums: Array of size 9. If value is true, corresponding number is a possibility in this segment. If value is false, number is not a possibility in this segment.
			-tiles: Array containing all tiles in segment
		*/
		boolean[] possibleNums;
		Tile[] tiles;

		public Segment() {
			//Initializes variables
			possibleNums = new boolean[9];
			tiles = new Tile[9];
		}

		public void addTile(Tile newTile) {
			for(int i = 0; i < tiles.length; i++) {
				if(tiles[i] == null) {
					tiles[i] = newTile;
					return;
				}
			}

			throw new ArrayIndexOutOfBoundsException();
		}

		public String toString() {
			return Arrays.toString(tiles);
		}
	}
}