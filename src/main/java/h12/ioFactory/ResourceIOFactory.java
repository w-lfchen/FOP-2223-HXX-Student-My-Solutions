package h12.ioFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * A factory for creating {@link BufferedReader} and {@link BufferedWriter} linked to the resource directory.
 */
public class ResourceIOFactory implements IOFactory {

    /**
     * Creates a new {@link ResourceIOFactory}-Instance.
     *
     * <p> The {@link ClassLoader} used to locate resources will default to the {@link ClassLoader} of this class.
     */
    public ResourceIOFactory() {}

    /**
     * {@inheritDoc}
     *
     * @param resourceName The resource to read from.
     * @return A {@link BufferedReader} to read from the resource.
     * @throws IOException If an {@link IOException} occurs while creating the {@link BufferedReader}.
     */
    @Override
    public BufferedReader createReader(String resourceName) throws IOException {
        InputStream resourceStream = getClass().getResourceAsStream(resourceName);
        if (resourceStream == null) {
            throw new IOException("Could not find %s/%s".formatted(getClass().getPackageName(), resourceName));
        }
        return new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8));
    }

    /**
     * This class does not support this operation.
     *
     * @throws UnsupportedOperationException always.
     */
    @Override
    public BufferedWriter createWriter(String resourceName) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("%s does not support writing!".formatted(getClass().getSimpleName()));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true}.
     */
    @Override
    public boolean supportsReader() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code false}.
     */
    @Override
    public boolean supportsWriter() {
        return false;
    }

}
