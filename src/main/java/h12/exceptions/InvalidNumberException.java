package h12.exceptions;

/**
 * Thrown to indicate that the read number does not match a valid number.
 */
public class InvalidNumberException extends JSONParseException {

    /**
     * Creates a new {@link InvalidNumberException}-Instance.
     *
     * @param number The invalid number that was read.
     */
    public InvalidNumberException(String number) {
        super("Invalid number: %s!".formatted(number));
    }
}
