package h12.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * An interface for all elements a JSON File can contain
 */
public interface JSONElement {

    /**
     * Transforms this {@link JSONElement} into a string representation and writes it using the given {@link BufferedWriter}.
     *
     * @param writer      The writer used to write the string representation.
     * @param indentation The current indentation.
     * @throws IOException If writing to the writer causes an IOException.
     */
    void write(BufferedWriter writer, int indentation) throws IOException;

    /**
     * If present, returns the array this {@link JSONElement} represents.
     *
     * @return The array this {@link JSONElement} represents.
     * @throws UnsupportedOperationException If this JSON element does not represent an array.
     */
    default JSONElement[] getArray() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This JSON Element does not support the operation getArray().");
    }

    /**
     * If present, returns the constant this {@link JSONElement} represents.
     *
     * @return The constant this JSON element represents.
     * @throws UnsupportedOperationException If this JSON element does not represent a constant.
     */
    default JSONConstants getConstant() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This JSON Element does not support the operation getConstant().");
    }

    /**
     * If present, returns the number this {@link JSONElement} represents.
     *
     * @return The number this {@link JSONElement} represents.
     * @throws UnsupportedOperationException If this JSON element does not represent a number.
     */
    default Number getNumber() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This JSON Element does not support the operation getNumber().");
    }

    /**
     * If present, returns the number this {@link JSONElement} represents as an {@link Integer}.
     *
     * @return The number this JSON element represents as an {@link Integer}.
     * @throws UnsupportedOperationException If this {@link JSONElement} does not represent a number.
     */
    default Integer getInteger() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This JSON Element does not support the operation getInteger().");
    }

    /**
     * If present, returns the number this {@link JSONElement} represents as a {@link Double}.
     *
     * @return The number this JSON element represents as a {@link Double}.
     * @throws UnsupportedOperationException If this {@link JSONElement} does not represent a number.
     */
    default Double getDouble() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This JSON Element does not support the operation getDouble().");
    }

    /**
     * If present, returns a {@link Set} containing the objectEntries of the object this {@link JSONElement} represents.
     *
     * @return A {@link Set} containing the objectEntries of the object this {@link JSONElement} represents.
     * @throws UnsupportedOperationException If this {@link JSONElement} does not represent an object.
     */
    default Set<JSONObject.JSONObjectEntry> getObjectEntries() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This JSON Element does not support the operation getObjectEntries().");
    }

    /**
     * If present, returns the string this {@link JSONElement} represents.
     *
     * @return The string this {@link JSONElement} represents.
     * @throws UnsupportedOperationException If this JSON element does not represent a string.
     */
    default String getString() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This JSON Element does not support the operation getString().");
    }

    /**
     * Returns the value of the {@link h12.json.JSONObject.JSONObjectEntry} that matches the given identifier.
     *
     * @param identifier The identifier to look for.
     * @return Returns the value of the {@link h12.json.JSONObject.JSONObjectEntry} that matches the given identifier.
     * @throws UnsupportedOperationException If this {@link JSONElement} does not represent an object.
     * @throws NoSuchElementException        If this {@link JSONElement} is a {@link JSONObject} but does not contain a {@link h12.json.JSONObject.JSONObjectEntry} that matches the given identifier.
     */
    default JSONElement getValueOf(String identifier) throws UnsupportedOperationException, NoSuchElementException {
        throw new UnsupportedOperationException("This JSON Element does not support the operation getValueOf(String).");
    }
}
