package h12.json.parser.implementation.node;

import h12.json.LookaheadReader;
import h12.json.parser.JSONParser;
import h12.json.parser.JSONParserFactory;

/**
 * A factory class for creating a {@link JSONParser} based on a {@link JSONElementNodeParser}.
 */
public class JSONNodeParserFactory implements JSONParserFactory {

    /**
     * Creates a new {@link JSONParser} based on a {@link JSONElementNodeParser}.
     *
     * @param reader The reader containing the information to parse.
     * @return The created {@link JSONParser}.
     */
    @Override
    public JSONParser createParser(LookaheadReader reader) {
        return new JSONParser(new JSONElementNodeParser(reader));
    }

}
