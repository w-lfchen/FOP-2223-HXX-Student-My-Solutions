package h11.providers;

import h11.AbstractRandom;
import h11.parse.Projection;
import org.tudalgo.algoutils.student.io.PropertyUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Convert a {@link List} of {@link Projection}s
 * into a textual representation
 * so that the resulting lines can be
 * parsed with an <code>LSystemParserImpl</code>
 */
public class LSystemToRandomLinesConverter {

    /**
     * Maximum number of comment lines before and after a projection.
     */
    private static final int MAX_LINE_NEXT_TO_PROJECTION =
        PropertyUtils.getIntProperty("h11/h11-generator.properties", "MAX_LINES_NEXT_TO_PROJECTION");

    /**
     * Maximum number of consecutive spaces.
     */
    private static final int MAX_SPACES_SIZE =
        PropertyUtils.getIntProperty("h11/h11-generator.properties", "MAX_SPACES_SIZE");

    /**
     * Maximum number of characters in a comment
     */
    private static final int MAX_COMMENT_SIZE =
        PropertyUtils.getIntProperty("h11/h11-generator.properties", "MAX_COMMENT_SIZE");

    /**
     * {@link AbstractRandom} to use.
     */
    private final AbstractRandom random;

    /**
     * @param random {@link AbstractRandom} to use.
     */
    public LSystemToRandomLinesConverter(AbstractRandom random) {
        this.random = random;
    }

    /**
     * Convert a {@link List} of {@link Projection}s
     * into a textual representation
     * so that the resulting lines can be
     * parsed with an <code>LSystemParserImpl</code>
     *
     * @param lSystem The L-System to convert.
     * @return A Stream of lines representing
     * the random, textual representation
     */
    public Stream<String> lSystemAsLines(List<Projection> lSystem) {
        // literally just the instructions
        return lSystem.stream().flatMap(this::projectionAsLines);
    }

    /**
     * Generate a chunk of lines form the given {@link Projection}.
     *
     * @param projection The {@link Projection}.
     * @return A {@link Stream} of lines.
     */
    private Stream<String> projectionAsLines(Projection projection) {
        return Stream.of(
            generateLinesNextToProjection(),
            projectionAsLine(projection),
            generateLinesNextToProjection()
        ).flatMap(Function.identity());
    }

    /**
     * Generate a random line from the given {@link Projection}.
     *
     * @param projection The projection.
     * @return A line representing the given projection.
     */
    private Stream<String> projectionAsLine(Projection projection) {
        var s = generateSpaces()
            + projection.source()
            + generateSpaces()
            + "->"
            + generateSpaces()
            + projection.destination()
            + generateComment();
        return Stream.of(s);
    }

    /**
     * Generate the lines before or after a projection.
     *
     * @return A {@link Stream} of the generated lines.
     */
    private Stream<String> generateLinesNextToProjection() {
        var size = random.nextInt(MAX_LINE_NEXT_TO_PROJECTION + 1);
        return Stream
            .generate(this::generateLineNextToProjection)
            .limit(size);
    }

    /**
     * Generate a line before or after a projection.
     *
     * @return The generated line.
     */
    private String generateLineNextToProjection() {
        return generateSpaces() + generateComment() + generateSpaces();
    }

    /**
     * Generate a random comment.
     *
     * @return The comment.
     */
    private String generateComment() {
        if (random.nextBoolean()) {
            return "";
        }

        var size = random.nextInt(MAX_COMMENT_SIZE + 1);
        return '#' + generateSpaces() + random.latin(size);
    }

    /**
     * Generate a random String of spaces.
     *
     * @return The generated String.
     */
    public String generateSpaces() {
        // create a stream of the whitespace characters, limit it, then turn those into singular Strings to be joined together
        return random.choices(' ', '\t').limit(random.nextInt(0, MAX_SPACES_SIZE + 1)).map(x -> Character.toString(x)).collect(Collectors.joining());
    }
}
