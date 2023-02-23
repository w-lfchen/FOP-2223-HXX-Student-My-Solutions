package h12.exceptions;

/**
 * Thrown to indicate that an exception occurred while trying to parse a JSON file.
 */
public class JSONParseException extends RuntimeException {

    /**
     * Creates a new {@link JSONParseException}-Instance.
     *
     * @param message A detail message.
     */
    public JSONParseException(String message) {
        super("An exception occurred while trying to parse a JSON file. %s".formatted(message));
    }
}

