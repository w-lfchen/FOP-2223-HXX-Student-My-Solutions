package h12.json;

import h12.json.implementation.node.JSONObjectNode;
import h12.json.implementation.node.JSONStringNode;

import java.util.*;

import static h12.json.implementation.node.JSONObjectNode.JSONObjectEntryNode;

/**
 * An interface for all JSON elements that represent an object.
 */
public interface JSONObject extends JSONElement {

    /**
     * Returns a {@link Set} containing the objectEntries of this {@link JSONObject}.
     *
     * @return A {@link Set} containing the objectEntries of this {@link JSONObject}.
     */
    Set<JSONObjectEntry> getObjectEntries();

    /**
     * Returns the {@link JSONElement} that is mapped to the given string.
     *
     * @param identifier The key to look for in the object entries.
     * @return the JSON element that is mapped to given string.
     * @throws NoSuchElementException If this {@link JSONObject} does not contain a mapping for the given key.
     */
    default JSONElement getValueOf(String identifier) throws NoSuchElementException {
        for (JSONObjectEntry entry : getObjectEntries()) {
            if (Objects.equals(entry.getIdentifier(), identifier)) {
                return entry.getValue();
            }
        }

        throw new NoSuchElementException("No entry associated with the key %s exists".formatted(identifier));
    }

    /**
     * Creates a new {@link JSONObjectNode} containing all the given object entries and returns it.
     *
     * @param entries The object entries the created {@link JSONObjectNode} should contain.
     * @return The created {@link JSONObjectNode}.
     */
    static JSONObjectNode of(JSONObjectEntry... entries) {
        Set<JSONObjectEntry> set = new HashSet<>();
        Collections.addAll(set, entries);
        return new JSONObjectNode(set);
    }

    /**
     * An inner interface for an object entry.
     */
    interface JSONObjectEntry extends JSONElement {

        /**
         * Returns the identifier of this {@link JSONObjectEntry}.
         *
         * @return The identifier of this {@link JSONObjectEntry}.
         */
        String getIdentifier();

        /**
         * Returns the value of this {@link JSONObjectEntry}.
         *
         * @return The value of this {@link JSONObjectEntry}.
         */
        JSONElement getValue();

        /**
         * Creates a new {@link JSONObjectEntryNode}-Instance containing the given information and returns it.
         *
         * @param identifier The identifier of the {@link JSONObjectEntry}.
         * @param value      The value of the {@link JSONObjectEntry}.
         * @return The created {@link JSONObjectEntryNode}.
         */
        static JSONObjectEntryNode of(String identifier, JSONElement value) {
            return new JSONObjectEntryNode(new JSONStringNode(identifier), value);
        }
    }

}
