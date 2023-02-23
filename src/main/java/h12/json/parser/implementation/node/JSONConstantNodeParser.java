package h12.json.parser.implementation.node;

import h12.exceptions.InvalidConstantException;
import h12.json.JSONConstant;
import h12.json.JSONConstants;
import h12.json.implementation.node.JSONConstantNode;

import java.io.IOException;
import java.util.Objects;

/**
 * A parser based on a node implementation that parses a {@link h12.json.JSONConstant}.
 * <p>
 * <p> Example:
 * <p> Input: false
 * <p> Output: {@code JSONConstant.FALSE}
 */
public class JSONConstantNodeParser implements JSONNodeParser {

    private final JSONElementNodeParser parser;

    /**
     * Creates a new {@link JSONConstantNodeParser}-Instance.
     *
     * @param parser The main {@link JSONElementNodeParser}.
     */
    public JSONConstantNodeParser(JSONElementNodeParser parser) {
        this.parser = parser;
    }

    /**
     * Parses a {@link JSONConstant}.
     *
     * @return The parsed {@link JSONConstantNode}.
     * @throws IOException              If an {@link IOException} occurs while reading the contents of the reader.
     * @throws InvalidConstantException If the read {@link JSONConstant} is not a valid constant as defined in {@link JSONConstants}.
     */
    @Override
    public JSONConstantNode parse() throws IOException, InvalidConstantException {
        // get the constant in question
        String possibleConstant = parser.readUntil(x -> !Character.isLetter(x));
        // iterate over all the constants
        for (JSONConstants constant : JSONConstants.values()) {
            // check for each if the spelling matches
            if (Objects.equals(constant.getSpelling(), possibleConstant)) {
                // if it does, return the constant
                return new JSONConstantNode(constant);
            }
        }
        // if this statement is reached, no constant spelling matches
        throw new InvalidConstantException(possibleConstant);
    }

}
