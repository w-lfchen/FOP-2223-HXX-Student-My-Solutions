package h11;

import java.util.stream.Stream;

/**
 * Descendant from {{@link java.util.Random}
 * adding extra methods.
 */
public abstract class AbstractRandom extends java.util.Random {

    /**
     * Use a unique seed.
     */
    public AbstractRandom() {
    }

    /**
     * Use the given seed.
     */
    public AbstractRandom(long seed) {
        super(seed);
    }

    /**
     * Creates an infinite {@link Stream}
     * where each element is one of the given
     * values with equal probability.
     * <p>
     * For example the call <code>choices(1, 2, 3)</code>
     * might result in <code>[2, 2, 1, 3, 2, 1, 2, 3, 2, 2, ...]</code>
     *
     * @param values The pool of values to choose from.
     * @return An endless {@link Stream} of randomly chosen values.
     * @param <T> The type of values.
     */
    public abstract <T> Stream<T> choices(T... values);

    /**
     * Creates a String of the given length where each
     * character is a randomly chosen, uppercase letter
     * in the latin alphabet.
     *
     * @param length The length of the resulting string.
     * @return A random, latin String.
     */
    public abstract String latin(int length);
}
