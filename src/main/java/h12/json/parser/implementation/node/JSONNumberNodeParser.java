package h12.json.parser.implementation.node;

import h12.exceptions.InvalidNumberException;
import h12.json.JSONNumber;
import h12.json.implementation.node.JSONNumberNode;

import java.io.IOException;

/**
 * A parser based on a node implementation that parses a {@link h12.json.JSONNumber}.
 *
 * <p> Example:
 * <p> Input: -69.420
 * <p> Output: {@code JSONNumber.of(-69.420)}
 */
public class JSONNumberNodeParser implements JSONNodeParser {

    private final JSONElementNodeParser parser;

    /**
     * Creates a new {@link JSONNumberNodeParser}-Instance.
     *
     * @param parser The main {@link JSONElementNodeParser}.
     */
    public JSONNumberNodeParser(JSONElementNodeParser parser) {
        this.parser = parser;
    }

    /**
     * Parses a {@link JSONNumber}.
     *
     * @return The parsed {@link JSONNumberNode}.
     * @throws IOException            If an {@link IOException} occurs while reading the contents of the reader.
     * @throws InvalidNumberException If the parsed {@link JSONNumber} is invalid.
     */
    @Override
    public JSONNumberNode parse() throws IOException, InvalidNumberException {
        // read the number
        String number = parser.readUntil(x -> !(Character.isDigit(x) || x == '+' || x == '-' || x == '.'));
        try {
            // if the number contains a '.', it is considered a double and treated accordingly
            if (number.contains(".")) {
                double doubleNumber = Double.parseDouble(number);
                return new JSONNumberNode(doubleNumber);
            } else {
                // otherwise it's an integer
                int integerNumber = Integer.parseInt(number);
                return new JSONNumberNode(integerNumber);
            }
            // catch the exception and throw a different one
        } catch (NumberFormatException exc) {
            throw new InvalidNumberException(number);
        }
    }

}
