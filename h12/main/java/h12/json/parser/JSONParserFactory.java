package h12.json.parser;

import h12.json.LookaheadReader;

/**
 * An abstract factory for creating a {@link JSONParser}.
 */
@FunctionalInterface
public interface JSONParserFactory {

    /**
     * Creates a new {@link JSONParser}.
     *
     * @param reader The reader containing the information to parse.
     * @return The created {@link JSONParser}.
     */
    JSONParser createParser(LookaheadReader reader);
}
