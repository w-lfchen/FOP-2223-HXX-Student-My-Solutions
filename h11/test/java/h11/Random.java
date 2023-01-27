package h11;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Descendant from {@link java.util.Random}
 * adding extra methods.
 */
public class Random extends AbstractRandom {

    /**
     * Use a unique seed.
     */
    public Random() {
    }

    /**
     * Use the given seed.
     */
    public Random(long seed) {
        super(seed);
    }

    @Override
    @SafeVarargs
    public final <T> Stream<T> choices(T... values) {
        // generate a stream of random indices and then map those to the respective entry in the supplied array
        // the specific reference to this.ints() instead of ints() is not needed
        return this.ints(0, values.length).mapToObj(x -> values[x]);
    }

    @Override
    public String latin(int length) {
        // since the upper bound is exclusive, it is necessary to add 1. the resulting stream of single characters is then limited to the desired length
        // then the ints are turned into Strings of the length 1, which are the joined together to a singular resulting String
        return this.ints('A', 'Z' + 1).limit(length).mapToObj(Character::toString).collect(Collectors.joining());
    }
}
