package h12.json.implementation.node;

import h12.json.JSONConstant;
import h12.json.JSONConstants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * A class representing a JSON constant implemented as a node.
 */
public class JSONConstantNode extends JSONNode implements JSONConstant {

    private final JSONConstants constant;

    /**
     * Creates a new {@link JSONConstantNode}-Instance.
     *
     * @param constant The constant this node represents.
     */
    public JSONConstantNode(JSONConstants constant) {
        this.constant = constant;
    }

    /**
     * Writes the string representation of this {@link JSONConstant} to the given writer.
     * <p> For the correct spelling of the constant see {@link JSONConstants#getSpelling()}.
     *
     * @param writer      The writer used to write the string representation.
     * @param indentation The current indentation (not used by this class).
     * @throws IOException If writing to the writer causes an IOException.
     */
    @Override
    public void write(BufferedWriter writer, int indentation) throws IOException {
        writer.write(constant.getSpelling());
    }

    /**
     * Returns the constant this JSON element represents.
     *
     * @return The constant this JSON element represents.
     */
    @Override
    public JSONConstants getConstant() {
        return constant;
    }

    @Override
    public String toString() {
        return "JSONConstantNode{" +
            "constant=" + constant +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONConstantNode that = (JSONConstantNode) o;
        return constant == that.constant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(constant);
    }
}
