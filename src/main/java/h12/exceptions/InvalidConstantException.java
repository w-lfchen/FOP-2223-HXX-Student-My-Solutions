package h12.exceptions;

/**
 * Thrown to indicate that the read constant does not match a valid constant as defined in {@link h12.json.JSONConstants}.
 */
public class InvalidConstantException extends JSONParseException {

    /**
     * Creates a new {@link InvalidConstantException}-Instance.
     *
     * @param constant The invalid constant that was read.
     */
    public InvalidConstantException(String constant) {
        super("Invalid constant: %s!".formatted(constant));
    }
}
