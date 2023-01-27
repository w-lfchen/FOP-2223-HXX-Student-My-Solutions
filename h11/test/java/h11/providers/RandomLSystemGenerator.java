package h11.providers;

import h11.AbstractRandom;
import h11.parse.Projection;
import org.tudalgo.algoutils.student.io.PropertyUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * To test the LSystemParser this
 * class can generate random {@link List}s
 * of {@link Projection}s representing an LSystem.
 */
public class RandomLSystemGenerator {

    /**
     * The maximum number of {@link Projection}
     * in a generated LSystem.
     */
    private static final int MAX_SYSTEM_SIZE = PropertyUtils.getIntProperty("h11/h11-generator.properties", "MAX_LSYSTEM_SIZE");

    /**
     * The maximum size of the destination
     * in a generated projection.
     */
    private static final int MAX_PROJECTION_DESTINATION_SIZE = 5;

    /**
     * {@link AbstractRandom} being used.
     */
    private final AbstractRandom random;

    /**
     * @param random {@link AbstractRandom} being used.
     */
    public RandomLSystemGenerator(AbstractRandom random) {
        this.random = random;
    }

    /**
     * Generate a random LSystem
     * represented as a {@link List} of {@link Projection}s.
     *
     * @return A {@link List} of random {@link Projection}s.
     */
    public List<Projection> generate() {
        // stream of random latin letters of length 1, filter out duplicate occurrences,
        // limit the length to a desired randomly generated number, then make new projections to be collected
        return Stream.generate(() -> random.latin(1)).distinct().limit(random.nextInt(1, MAX_SYSTEM_SIZE + 1)).map(this::makeProjection).toList();
    }

    /**
     * Generate a {@link Projection} with
     * the given source and a random destination.
     *
     * @param src The source of the {@link Projection}
     * @return A random {@link Projection} with the given source.
     */
    public Projection makeProjection(String src) {
        // lower bound inclusive, upper bound exclusive
        return new Projection(src, random.latin(random.nextInt(1, MAX_PROJECTION_DESTINATION_SIZE + 1)));
    }
}
