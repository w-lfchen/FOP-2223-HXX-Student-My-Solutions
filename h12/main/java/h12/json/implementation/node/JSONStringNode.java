package h12.json.implementation.node;

import h12.json.JSONString;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * A class representing a JSON String implemented as a node.
 */
public class JSONStringNode extends JSONNode implements JSONString {

    private final String string;

    /**
     * Creates a new {@link JSONStringNode}-Instance.
     *
     * @param string The string this node represents.
     */
    public JSONStringNode(String string) {
        this.string = string;
    }

    /**
     * Writes the represented string surrounded by quotation marks ({@code '"'}) to the given writer.
     *
     * @param writer      The writer used to write the string representation.
     * @param indentation The current indentation (not used by this class).
     * @throws IOException If writing to the writer causes an IOException.
     */
    @Override
    public void write(BufferedWriter writer, int indentation) throws IOException {
        writer.write("\"" + string + "\"");
    }

    /**
     * Returns the string this JSON element represents.
     *
     * @return The string this JSON element represents.
     */
    @Override
    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "JSONStringNode{" +
            "string='" + string + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONStringNode that = (JSONStringNode) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}
