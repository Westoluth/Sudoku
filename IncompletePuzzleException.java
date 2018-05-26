//Suppresses serial warnings
@SuppressWarnings("serial")

/*
Exception thrown if someone attempts to add to a full Segment
*/
public class IncompletePuzzleException extends Exception {
    //Parameterless Constructor
    public IncompletePuzzleException() {}

    //Constructor that accepts a message
    public IncompletePuzzleException(String message) {
        super(message);
    }
}