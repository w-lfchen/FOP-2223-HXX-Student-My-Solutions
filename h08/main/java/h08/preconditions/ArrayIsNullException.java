package h08.preconditions;

/**
 * Thrown when a primary array is null.
 */
public class ArrayIsNullException extends RuntimeException {
    public ArrayIsNullException() {
        super("Array is null!");
    }
}
