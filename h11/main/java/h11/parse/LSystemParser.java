package h11.parse;

import java.util.List;
import java.util.stream.Stream;

/**
 * To read in an L-System from a textual representation,
 * instances of this interface can be used.
 * As a limitation the only valid token type is <code>char</code>.
 */
public interface LSystemParser {

    /**
     * Given a {@link Stream} of lines,
     * parse all {@link Projection}
     * and return them in a {@link List}.
     *
     * @param lines The lines to parse.
     * @return A {@link List} of the parsed {@link Projection}s.
     */
    List<Projection> parse(Stream<String> lines);
}
