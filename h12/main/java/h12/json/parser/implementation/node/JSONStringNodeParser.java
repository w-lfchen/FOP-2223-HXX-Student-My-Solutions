package h12.json.parser.implementation.node;

import h12.exceptions.JSONParseException;
import h12.json.implementation.node.JSONStringNode;

import java.io.IOException;

/**
 * A parser based on a node implementation that parses a {@link h12.json.JSONString}.
 *
 * <p> Example (The {@code '"'} characters are part of the input):
 * <p> Input: "Hello World!"
 * <p> Output: {@code JSONString.of("Hello World!")}
 */
public class JSONStringNodeParser implements JSONNodeParser {

    private final JSONElementNodeParser parser;

    /**
     * Creates a new {@link JSONStringNodeParser}-Instance.
     *
     * @param parser The main {@link JSONElementNodeParser}.
     */
    public JSONStringNodeParser(JSONElementNodeParser parser) {
        this.parser = parser;
    }

    /**
     * Parses a {@link h12.json.JSONString}.
     *
     * @return The parsed {@link JSONStringNode}.
     * @throws IOException        If an {@link IOException} occurs while reading the contents of the reader.
     * @throws JSONParseException If the parsed JSON file is invalid.
     */
    @Override
    public JSONStringNode parse() throws IOException, JSONParseException {
        parser.accept('"');
        String string = parser.readUntil(i -> i == '"');
        parser.accept('"');

        return new JSONStringNode(string);
    }

}
