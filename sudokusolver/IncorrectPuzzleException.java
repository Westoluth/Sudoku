package sudokusolver;

//Suppresses serial warnings
@SuppressWarnings("serial")

/*
Exception thrown if someone attempts to add to a full Segment
*/
public class IncorrectPuzzleException extends Exception {
    //Parameterless Constructor
    public IncorrectPuzzleException() {}

    //Constructor that accepts a message
    public IncorrectPuzzleException(String message) {
        super(message);
    }
}