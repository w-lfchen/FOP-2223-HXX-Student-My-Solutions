package h12.exceptions;

/**
 * Thrown to indicate that the received character does not match the expected character.
 */
public class UnexpectedCharacterException extends JSONParseException {

    /**
     * Creates a new {@link UnexpectedCharacterException}-Instance.
     *
     * @param expected The expected character.
     * @param actual   The actual character that was received.
     */
    public UnexpectedCharacterException(char expected, int actual) {
        super("Received an unexpected character. Expected: <%s>, but was: <%s>".formatted(expected, actual == -1 ? "EOF" : (char) actual));
    }
}
