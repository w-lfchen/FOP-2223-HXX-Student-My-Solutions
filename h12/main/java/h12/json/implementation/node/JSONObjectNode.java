package h12.json.implementation.node;

import h12.json.JSONElement;
import h12.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * A class representing a JSON object implemented as a node.
 * <p> The object entries of the object are stored in a {@code Set&lt;JSONObjectEntry&gt;}.
 */
public class JSONObjectNode extends JSONNode implements JSONObject {

    private final Set<JSONObjectEntry> objectEntries;

    /**
     * Creates a new {@link JSONObjectNode}-Instance.
     *
     * @param set A {@link Set} containing all the object entries of the object.
     */
    public JSONObjectNode(Set<JSONObjectEntry> set) {
        this.objectEntries = set;
    }

    /**
     * Writes the string representation of this {@link JSONObject} to the given {@link BufferedWriter} using the current indentation.
     * <p> The formatting follows these rules:
     * <p> 1. Every {@link JSONObject.JSONObjectEntry} is written to the writer using their write methode.
     * <p> 2. The opening bracket ({@code '&#123'}) and every {@link JSONObjectEntry} (including the {@code ','}) is followed by a line break ({@code '\n'}).
     * <p> 3. Every line break ({@code '\n'}) is followed by an indentation which is created using {@link JSONNode#writeIndentation(BufferedWriter, int)}.
     * <p> 4. The indentation in front of an {@link JSONObjectEntry} is one higher than the current indentation. The indentation in front of the closing bracket ({@code  '&#125'}) is the current indentation.
     * <p> 5. The colon ({@code ':'}) in the middle of an object entry is followed by a single space character({@code ' '}).
     *
     * @param writer      The writer used to write the string representation.
     * @param indentation The current indentation.
     * @throws IOException If an {@link IOException} occurs while writing to the writer.
     */
    @Override
    public void write(BufferedWriter writer, int indentation) throws IOException {
        Iterator<JSONObjectEntry> setIterator = objectEntries.iterator();
        // opening bracket
        writer.write("{\n");
        while (setIterator.hasNext()) {
            // this is basically identical to the arrayNode
            writeIndentation(writer, indentation + 1);
            setIterator.next().write(writer, indentation + 1);
            if (setIterator.hasNext()) {
                writer.write(',');
            }
            writer.write('\n');
        }
        // now all elements have been written, therefore a closing bracket is needed
        writeIndentation(writer, indentation);
        writer.write('}');
    }

    /**
     * Returns a {@link Set} containing the object entries of this {@link JSONObjectNode}.
     *
     * @return a {@link Set} containing the object entries.
     */
    @Override
    public Set<JSONObjectEntry> getObjectEntries() {
        return Collections.unmodifiableSet(objectEntries);
    }

    @Override
    public String toString() {
        return "JSONObjectNode{" +
            "objectEntries=" + objectEntries +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONObjectNode that = (JSONObjectNode) o;
        return Objects.equals(objectEntries, that.objectEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectEntries);
    }

    /**
     * An inner class representing an object entry implemented as a node.
     */
    public static class JSONObjectEntryNode extends JSONNode implements JSONObjectEntry {

        private final JSONStringNode identifier;
        private final JSONElement value;

        /**
         * Creates a new {@link JSONObjectEntryNode}-Instance.
         *
         * @param identifier The identifier of this {@link JSONObjectEntry}.
         * @param value      The {@link JSONElement} associated with this {@link JSONObjectEntry}.
         */
        public JSONObjectEntryNode(JSONStringNode identifier, JSONElement value) {
            this.identifier = identifier;
            this.value = value;
        }

        /**
         * Writes the identifier and the value of this {@link JSONObjectEntry} separated by a colon({@code ':')}to the given {@link BufferedWriter} using the current indentation.
         * <p> The colon is followed by a single space.
         *
         * @param writer      The writer used to write the string representation.
         * @param indentation The current indentation (not used by this class).
         * @throws IOException If an {@link IOException} occurs while writing to the writer.
         */
        @Override
        public void write(BufferedWriter writer, int indentation) throws IOException {
            // the correct indentation is received from the object
            // the 0 is arbitrary, the value is simply not used
            identifier.write(writer, 0);
            writer.write(": ");
            // simply pass on the indentation, the respective logic of the value node will handle it properly
            value.write(writer, indentation);
        }

        /**
         * Returns the identifier of this {@link JSONObjectEntry}.
         *
         * @return The identifier of this {@link JSONObjectEntry}.
         */
        @Override
        public String getIdentifier() {
            return identifier.getString();
        }

        /**
         * Returns the value of this {@link JSONObjectEntry}.
         *
         * @return The value of this {@link JSONObjectEntry}.
         */
        @Override
        public JSONElement getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "JSONObjectEntryNode{" +
                "identifier=" + identifier +
                ", value=" + value +
                '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JSONObjectEntryNode that = (JSONObjectEntryNode) o;
            return Objects.equals(identifier, that.identifier);
        }

        @Override
        public int hashCode() {
            return Objects.hash(identifier);
        }
    }
}
