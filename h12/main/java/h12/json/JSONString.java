package h12.json;

import h12.json.implementation.node.JSONStringNode;

/**
 * An interface for all JSON elements that represent a string.
 */
public interface JSONString extends JSONElement {

    /**
     * Returns the string this {@link JSONElement} represents.
     *
     * @return The string this {@link JSONElement} represents.
     */
    String getString();

    /**
     * Creates a new {@link JSONStringNode}-Instance containing the given {@link String} and returns it.
     *
     * @param string The {@link String} the created {@link JSONStringNode} should contain.
     * @return The created {@link JSONStringNode}.
     */
    static JSONStringNode of(String string) {
        return new JSONStringNode(string);
    }
}
