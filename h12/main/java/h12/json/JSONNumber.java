package h12.json;

import h12.json.implementation.node.JSONNumberNode;

/**
 * An interface for all JSON elements that represent a number.
 */
public interface JSONNumber extends JSONElement {

    /**
     * Returns the number this {@link JSONElement} represents.
     *
     * @return The number this {@link JSONElement} represents.
     */
    @Override
    Number getNumber();

    /**
     * Returns the number this {@link JSONElement} represents as an {@link Integer}.
     *
     * @return The number this {@link JSONElement} represents as an {@link Integer}.
     */
    @Override
    Integer getInteger();

    /**
     * Returns the number this {@link JSONElement} represents as a {@link Double}.
     *
     * @return The number this {@link JSONElement} represents as a {@link Double}.
     */
    @Override
    Double getDouble();

    /**
     * Creates a new {@link JSONNumberNode}-Instance containing the given {@link Number} and returns it.
     *
     * @param number The {@link Number} the created {@link JSONNumberNode} should contain.
     * @return The created {@link JSONNumberNode}.
     */
    static JSONNumberNode of(Number number) {
        return new JSONNumberNode(number);
    }

}
