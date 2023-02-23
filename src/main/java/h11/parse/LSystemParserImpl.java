package h11.parse;

import java.util.List;
import java.util.stream.Stream;

/**
 * An implementation of the {{@link LSystemParser}
 * implementing the following file format:
 * <pre>
 *     # Comments begin with a `#`
 *     # source of the first projection is the axiom
 *     A -> AB
 *     # More rules follow with each new line
 *     A -> AB
 * </pre>
 */
public class LSystemParserImpl implements LSystemParser {

    @Override
    public List<Projection> parse(Stream<String> lines) {
        // first, the input is split and only the first String is used since everything after it is a comment and therefore can be ignored
        // then, all whitespaces are removed and empty Strings are discarded.
        // all remaining strings are split using the arrow and tested , if they contain more than one arrow or more than one character before said arrow, an IllegalArgumentException is thrown
        // since the source and destination are valid, a new projection is created using them, all projections are then collected
        return lines.map(string -> string.split("#")[0]).map(a -> a.replaceAll("\\s", "")).filter(y -> !y.isBlank()).map(z -> {
            String[] parsedString = z.split("->");
            if (parsedString.length != 2 || parsedString[0].length() != 1) {
                throw new IllegalArgumentException();
            } else {
                return new Projection(parsedString[0], parsedString[1]);
            }
        }).toList();
    }
}
