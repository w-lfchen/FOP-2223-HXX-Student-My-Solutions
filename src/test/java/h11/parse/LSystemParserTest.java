package h11.parse;

import h11.providers.ProjectionsWithLinesProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test a {@link LSystemParser} using testcases generated
 * by a {@link ProjectionsWithLinesProvider}.
 */
class LSystemParserTest {

    /**
     * The {@link LSystemParser} to test.
     */
    private final LSystemParser parser = new LSystemParserImpl();

    /**
     * Test that the parsed projections match the expected one.
     *
     * @param projections The expected projections.
     * @param lines The random lines representing the expected projections.
     */
    @ParameterizedTest
    @ArgumentsSource(ProjectionsWithLinesProvider.class)
    void testThat_projectionsMatch(List<Projection> projections, Stream<String> lines) {
        var actual = parser.parse(lines);
        assertIterableEquals(projections, actual);
    }
}
