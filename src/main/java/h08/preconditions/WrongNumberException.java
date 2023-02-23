package h08.preconditions;

/**
 * Thrown when a max value is negative.
 */
public class WrongNumberException extends Exception {
    public WrongNumberException(double d) {
        super("" + d); // I am not sure why one wouldn't use String.valueOf.
    } // I am doing it this way because that is what the sheet suggested.
}
