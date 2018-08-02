package sudoku.solver;

//Suppresses serial warnings
@SuppressWarnings("serial")

/*
Exception thrown if someone attempts to add to a full Segment
*/
public class SegmentOverfilledException extends RuntimeException {
    //Parameterless Constructor
    public SegmentOverfilledException() {}

    //Constructor that accepts a message
    public SegmentOverfilledException(String message) {
        super(message);
    }
}