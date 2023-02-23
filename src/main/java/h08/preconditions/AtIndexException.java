package h08.preconditions;

/**
 * Thrown when a secondary array is null.
 */
public class AtIndexException extends RuntimeException {
    public AtIndexException(int i) {
        super("Index: " + i); // see WrongNumberException.java for my thoughts here.
    }
}
