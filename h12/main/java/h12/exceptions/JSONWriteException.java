package h12.exceptions;

/**
 * Thrown to indicate that an exception occurred while trying to read a JSON file.
 */
public class JSONWriteException extends RuntimeException {

    /**
     * Creates a new {@link JSONWriteException}-Instance.
     *
     * @param message A detail message.
     */
    public JSONWriteException(String message) {
        super("An exception occurred while trying to write to a JSON file. %s".formatted(message));
    }

}
