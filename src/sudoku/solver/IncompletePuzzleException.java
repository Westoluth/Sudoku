package sudoku.solver;

//Suppresses serial warnings
@SuppressWarnings("serial")

/*
Exception thrown if a puzzle was not able to be solved.
Indicates bad input, a bug, or simply that the program
does not know how to deal with certain patterns in the
puzzle.
*/
public class IncompletePuzzleException extends Exception {
    //Parameterless Constructor
    public IncompletePuzzleException() {}

    //Constructor that accepts a message
    public IncompletePuzzleException(String message) {
        super(message);
    }
}