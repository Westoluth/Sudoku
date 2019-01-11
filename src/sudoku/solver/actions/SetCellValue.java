package sudoku.solver.actions;

import sudoku.solver.solvercontext.*;

/*
An Action to set a cell at cellId in inputBoard to cellValue.
*/
public class SetCellValue implements Action {
	/*
	SetCellValue variables:
		-cellId: The location of the cell in inputBoard's boardCells array
		-cellValue: The value that the selected cell should be set to
	*/

	int cellId;
	int cellValue;

	/*
	Constructor to set cellId and cellValue of Action
	*/
	public SetCellValue(int cellId, int cellValue) {
		this.cellId = cellId;
		this.cellValue = cellValue;
	}

	/*
	Sets a cell at cellId to cellValue
	*/
	public void applyAction(SolverBoard inputBoard) {
		SolverCell targetCell = inputBoard.getCell(cellId);
		targetCell.setValue(cellValue);
	}

	/*
	String interpretation of Action
	*/
	public String toString() {
		return "SetCellValue: Set Cell# " + cellId + " to " + cellValue;
	}
}