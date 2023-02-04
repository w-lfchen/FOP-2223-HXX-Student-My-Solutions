package h12.json;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Reads a character stream from a {@link Reader}.
 *
 * <p> The method peek() allows the user the retrieve the next incoming char without skipping it.
 * Given the sequence {@code "abc"} the {@link LookaheadReader} would behave as followed:
 *
 * <pre>   Method called:       Returned value:
 *
 * reader.peek();       'a'
 * reader.read();       'a'
 * reader.read();       'b'
 * reader.peek();       'c'
 * reader.peek();       'c'
 * reader.read();       'c'
 * reader.peek();       -1
 * reader.read();       -1
 * </pre>
 */
public class LookaheadReader extends Reader {

    private final BufferedReader reader;
    private boolean isClosed = false;
    // own attribute to track the value to be returned upon calling peek()
    private int peekedValue;

    /**
     * Creates a new {@link LookaheadReader}-Instance based on the given reader.
     *
     * @param reader The Reader the constructed lookahead reader is based on.
     * @throws IOException If reading from the underlying reader causes an {@link IOException}.
     */
    public LookaheadReader(BufferedReader reader) throws IOException {
        this.reader = reader;
        // save the first value
        peekedValue = reader.read();
    }

    /**
     * Reads a single character.
     *
     * @return The character read or -1 if the end of the reader is reached.
     * @throws IOException If reading from the underlying reader causes an {@link IOException}.
     */
    public int read() throws IOException {
        // save the next value and return the current one
        int tmp = peekedValue;
        peekedValue = reader.read();
        return tmp;
    }

    /**
     * Retrieves the next character without skipping that character.
     *
     * @return The next character or -1 if the end of the reader is reached.
     * @throws IOException If reading from the underlying Reader causes an {@link IOException}.
     */
    public int peek() throws IOException {
        // this can be called as often as needed without advancing the reader
        return peekedValue;
    }

    /**
     * {@inheritDoc}
     *
     * @param characterBuffer Destination buffer
     * @param off             Offset at which to start storing characters
     * @param len             Maximum number of characters to read
     * @return The number of characters read, or -1 if the end of the
     * stream has been reached
     * @throws IndexOutOfBoundsException If {@code off} is negative, or {@code len} is negative,
     *                                   or {@code len} is greater than {@code characterBuffer.length - off}
     * @throws IOException               If reading from the underlying Reader causes an {@link IOException}.
     */
    @Override
    public int read(char @NotNull [] characterBuffer, int off, int len) throws IOException, IndexOutOfBoundsException {
        if (off < 0 || len < 0 || len > characterBuffer.length - off) {
            throw new IndexOutOfBoundsException();
        }

        if (peek() == -1) {
            return -1;
        }

        int i;
        for (i = 0; i < len; i++) {
            if (peek() == -1) {
                break;
            }
            characterBuffer[i + off] = (char) read();
        }

        return i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        reader.close();
        isClosed = true;
    }

    /**
     * Returns whether this {@link LookaheadReader} has been closed.
     *
     * @return {@code true}, if this {@link LookaheadReader} has been closed.
     */
    public boolean isClosed() {
        return isClosed;
    }
}
