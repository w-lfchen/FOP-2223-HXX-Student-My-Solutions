package h12.exceptions;

/**
 * Thrown to indicate that the parse encountered a trailing comma while trying to parse a JSON file.
 */
public class TrailingCommaException extends JSONParseException {

    /**
     * Creates a new {@link TrailingCommaException}-Instance.
     */
    public TrailingCommaException() {
        super("Encountered a trailing comma while trying to parse a JSON file");
    }
}
