package sudoku.solver;

//Suppresses serial warnings
@SuppressWarnings("serial")

/*
Exception thrown if a puzzle was partially or fully solved, but in a way that
resulted in an invalid puzzle. This is an indication of bad input or a bug in 
the program.
*/
public class IncorrectPuzzleException extends Exception {
    //Parameterless Constructor
    public IncorrectPuzzleException() {}

    //Constructor that accepts a message
    public IncorrectPuzzleException(String message) {
        super(message);
    }
}