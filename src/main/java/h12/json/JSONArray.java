package h12.json;

import h12.json.implementation.node.JSONArrayNode;

import java.util.List;

/**
 * An interface for all JSON elements that represent an array.
 */
public interface JSONArray extends JSONElement {

    /**
     * Returns the array this {@link JSONElement} represents.
     *
     * @return The array this {@link JSONElement} represents.
     **/
    @Override
    JSONElement[] getArray();

    /**
     * Creates a new {@link JSONArrayNode} containing all the given elements and returns it.
     *
     * @param elements The elements the created {@link JSONArrayNode} should contain.
     * @return The created {@link JSONArrayNode}.
     */
    static JSONArrayNode of(JSONElement... elements) {
        return new JSONArrayNode(List.of(elements));
    }
}
