package h12.ioFactory;

import java.io.*;

/**
 * A factory for creating {@link Reader} and {@link Writer} objects.
 */
public interface IOFactory {

    /**
     * Creates a {@link BufferedReader} for reading from the provided resource.
     *
     * @param resourceName The resource to read from.
     * @return A {@link BufferedReader} to read from the resource.
     * @throws IOException                   If an {@link IOException} occurs while creating the {@link Reader}.
     * @throws UnsupportedOperationException If the implementation does not {@link #supportsReader() support reading}.
     */
    BufferedReader createReader(String resourceName) throws IOException, UnsupportedOperationException;

    /**
     * Creates a {@link BufferedWriter} for writing to the provided resource.
     *
     * @param resourceName The resource to write to.
     * @return A {@link BufferedWriter} to write to the resource.
     * @throws IOException                   If an {@link IOException} occurs while creating the {@link Reader}.
     * @throws UnsupportedOperationException If the implementation does not {@link #supportsWriter() support writing}
     */
    BufferedWriter createWriter(String resourceName) throws IOException, UnsupportedOperationException;

    /**
     * Whether the implementation supports reading.
     *
     * @return Whether the implementation supports reading.
     */
    boolean supportsReader();

    /**
     * Whether the implementation supports writing.
     *
     * @return Whether the implementation supports writing.
     */
    boolean supportsWriter();
}
