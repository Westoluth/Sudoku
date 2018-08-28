package sudoku.solver.solvercontext;

import sudoku.board.*;

/*
Board class to manage data in the SudokuSolver context
*/
public class SolverBoard extends Board{
	/*
	Constructor for SolverBoard. Begins in same state as passed Board
	*/
	public SolverBoard(Board baseBoard) {
		//Creates all board member variables
		super(baseBoard.getBoardValues());
	}

	/*--------------------------------------------------------------------------------
	// Public Getter Methods 
	--------------------------------------------------------------------------------*/
	
	/*
	Returns a SolverCell at number cellNum(0-80) in the board
	*/
	public SolverCell getCell(int cellNum) {
		return (SolverCell)boardCells[cellNum];
	}

	/*
	Returns a SolverCell array containing every cell on the board
	*/
	public SolverCell[] getBoard() {
		return (SolverCell[])boardCells;
	}

	/*
	Returns a SolverRegion containing all values in selected square at squareNum(0-8)
	*/
	public SolverRegion getSquare(int squareNum) {
		return (SolverRegion)squares[squareNum];
	}

	/*
	Returns a SolverRegion containing all values in selected row at rowNum(0-8)
	*/
	public SolverRegion getRow(int rowNum) {
		return (SolverRegion)rows[rowNum];
	}

	/*
	Returns a SolverRegion containing all values in selected row at columnNum(0-8)
	*/
	public SolverRegion getColumn(int columnNum) {
		return (SolverRegion)columns[columnNum];
	}

	/*--------------------------------------------------------------------------------
	// Private Setup Functions
	--------------------------------------------------------------------------------*/

	/*
	Overwrites createCell function to return SolverCell instead of Cell
	*/
	protected SolverCell createCell(int cellValue) {
		return new SolverCell(cellValue);
	}

	/*
	Overwrites createRegion function to return SolverRegion instead of Region
	*/
	protected SolverRegion createRegion() {
		return new SolverRegion();
	}

	/*--------------------------------------------------------------------------------
	// Private Management Functions
	--------------------------------------------------------------------------------*/

	/*
	Updates all possible numbers. Calls Region.updatePossibleNums() on every region
	*/
	public void updatePossibleNums() {
		for(int regionGroupNum = 0; regionGroupNum < regionGroups.length; regionGroupNum++) {
			for(int regionNum = 0; regionNum < regionGroups[regionGroupNum].length; regionNum++) {
				((SolverRegion)regionGroups[regionGroupNum][regionNum]).updatePossibleNums();
			}
		}
	}
}