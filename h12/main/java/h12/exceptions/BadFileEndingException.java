package h12.exceptions;

/**
 * Thrown to indicate that the read file either ended too early or did not end after the expected content was read.
 */
public class BadFileEndingException extends JSONParseException {

    /**
     * Creates a new {@link BadFileEndingException}-Instance.
     */
    public BadFileEndingException() {
        super("The file didn't end properly after the content was parsed or ended too early!");
    }
}
