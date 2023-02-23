package h12.json.parser.implementation.node;

import h12.exceptions.JSONParseException;
import h12.json.JSONElement;
import h12.json.implementation.node.JSONNode;

import java.io.IOException;

/**
 * An interface for parsers based on a node implementation that parses a specific {@link JSONElement}.
 */
public interface JSONNodeParser {

    /**
     * Parses a specific {@link JSONElement} using the {@link JSONElementNodeParser} and returns the parsed {@link JSONNode}.
     *
     * @return the parsed {@link JSONNode}.
     * @throws IOException        If an {@link IOException} occurs while reading the contents of the {@link h12.json.LookaheadReader}.
     * @throws JSONParseException If the parsed JSON file is invalid.
     */
    JSONNode parse() throws IOException, JSONParseException;

}
