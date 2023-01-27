package h11.parse;

import h11.LSystem;
import h11.providers.ProjectionsProvider;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test a parsed L-System.
 */
public class ParsedLSystemTest {

    /**
     * The {@link LSystem} to test
     * as implemented by {@link ParsedLSystem}.
     */
    private LSystem<Character> lSystem;

    /**
     * Test that all defined sources are
     * being projected according to the given projections.
     *
     * @param projections The projections to test with.
     */
    @ParameterizedTest
    @ArgumentsSource(ProjectionsProvider.class)
    void testThat_projectOfKnownSourceProjects(List<Projection> projections) {
        lSystem = new ParsedLSystem(projections);
        assertAll(projections.stream().map(this::testProjection));
    }

    /**
     * Test that the L-System behaves
     * as defined by the given {@link Projection}.
     *
     * @param projection The {@link Projection} to test with.
     * @return An {@link Executable} representing the test.
     */
    private Executable testProjection(Projection projection) {
        return () -> {
            var projected = lSystem.project(projection.source());
            var actual = projected
                .map(String::valueOf)
                .collect(Collectors.joining());
            assertEquals(projection.destination(), actual);
        };
    }
}
