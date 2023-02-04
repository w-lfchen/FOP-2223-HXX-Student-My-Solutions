package h12.json;

import h12.json.implementation.node.JSONConstantNode;

/**
 * An interface for all JSON elements that represent a constant.
 */
public interface JSONConstant extends JSONElement {

    JSONConstantNode TRUE = new JSONConstantNode(JSONConstants.TRUE);
    JSONConstantNode FALSE = new JSONConstantNode(JSONConstants.FALSE);
    JSONConstantNode NULL = new JSONConstantNode(JSONConstants.NULL);

    /**
     * Returns the constant this {@link JSONElement} represents.
     *
     * @return The constant this {@link JSONElement} represents.
     */
    @Override
    JSONConstants getConstant();
}
