package h11;

import java.util.List;
import java.util.stream.Stream;

/**
 * Implementations of this interface
 * can be used to compute the individual
 * projections of L-System,
 * called its growth.
 *
 * @param <T> The type of tokens used in the underlying L-System.
 */
public interface LSystemGrower<T> {

    /**
     * Compute all the projections of the underlying L-System
     * starting at the axiom and never finishing.
     * <p>
     * Each individual projection is represented
     * by a {@link List} of tokens.
     *
     * @return An infinite {@link Stream} of sequences,
     * each being a projection of the underlying L-System.
     */
    Stream<List<T>> grow();
}
