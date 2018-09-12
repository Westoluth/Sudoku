package sudoku.solver;

import sudoku.solver.solvercontext.*;
import sudoku.solver.rules.*;

public class SolverReport {
	/*
	SolverReport variables:
		-initalBoard: Contains the board as it was initially passed to the solver
		-endBoard: Contains the completed board at the end of the solve call
		-actions: An array of all the actions needed to transform intialBoard into endBoard
	*/
	private SolverBoard initialBoard;
	private SolverBoard endBoard;
	private Action[] actions;

	/*
	Takes all the parameters and assigns them to their respective member variables
	*/
	public SolverReport(SolverBoard initialBoard, SolverBoard endBoard, Action[] actions) {
		this.initialBoard = initialBoard;
		this.endBoard = endBoard;
		this.actions = actions;
	}

	/*
	Returns initialBoard
	*/
	public SolverBoard getInitialBoard() {
		return initialBoard;
	}

	/*
	Returns endBoard
	*/
	public SolverBoard getEndBoard() {
		return endBoard;
	}

	/*
	Returns actions
	*/
	public Action[] getActions() {
		return actions;
	}
}