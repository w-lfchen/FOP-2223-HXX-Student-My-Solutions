package h12.json.implementation.node;

import h12.json.JSONNumber;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * A class representing a JSON number implemented as a node.
 */
public class JSONNumberNode extends JSONNode implements JSONNumber {

    private final Number number;

    /**
     * Creates a new {@link JSONNumberNode}-Instance.
     *
     * @param number The number this node represents.
     */
    public JSONNumberNode(Number number) {
        this.number = number;
    }

    /**
     * Writes the string representation of this {@link JSONNumber} to the given writer.
     *
     * @param writer      The writer used to write the string representation.
     * @param indentation The current indentation (not used by this class).
     * @throws IOException If writing to the writer causes an IOException.
     */
    @Override
    public void write(BufferedWriter writer, int indentation) throws IOException {
        // the indentation is not needed here, same goes for the String and constant nodes
        writer.write(String.valueOf(number));
    }

    /**
     * Returns the number this JSON element represents.
     *
     * @return The number this JSON element represents.
     */
    public Number getNumber() {
        return number;
    }


    /**
     * Returns the number this JSON element represents as an {@link Integer}.
     *
     * @return The number this JSON element represents as an {@link Integer}.
     */
    @Override
    public Integer getInteger() {
        return number.intValue();
    }

    /**
     * Returns the number this JSON element represents as an {@link Double}.
     *
     * @return The number this JSON element represents as an {@link Double}.
     */
    @Override
    public Double getDouble() {
        return number.doubleValue();
    }

    @Override
    public String toString() {
        return "JSONNumberNode{" +
            "number=" + number +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONNumberNode that = (JSONNumberNode) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
