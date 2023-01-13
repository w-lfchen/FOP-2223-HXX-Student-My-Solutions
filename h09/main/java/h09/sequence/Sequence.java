package h09.sequence;

import h09.basic.BasicFactory;
import h09.sequence.collect.SequenceCollector;

import java.util.Iterator;
import java.util.function.Function;

/**
 * A sequence of discrete values of {@link T}.
 */
@FunctionalInterface
public interface Sequence<T> {

    /**
     * Creates an iterator for this sequence.
     */
    Iterator<T> iterator();

    /**
     * Applies the given {@code sequenceMapper} to this sequence and returns the result.
     *
     * <p>
     * This can be used to chain operations on sequence instances which creates
     * an easier-to-read syntax.
     * </p>
     *
     * @param sequenceMapper the mapper to apply
     * @param <R>            the element type of the resulting sequence
     * @return the resulting sequence
     */
    default <R> Sequence<R> then(Function<Sequence<T>, Sequence<R>> sequenceMapper) {
        return sequenceMapper.apply(this);
    }

    /**
     * Collects this sequence into a result of type {@link R} using the given {@code collector}.
     *
     * @param collector the collector to use
     * @param <R>       the type of the result
     * @return the result of the collection
     */
    default <R> R collect(SequenceCollector<T, R> collector) {
        return collector.collect(this);
    }

    /**
     * Creates a sequence of the given {@code elements}.
     *
     * @param elements the elements to create a sequence of
     * @param <T>      the element type of the sequence
     * @return the sequence of the given elements
     */
    @SafeVarargs
    static <T> Sequence<T> of(T... elements) {
        // throw new RuntimeException("Not implemented yet");
        // Uncomment the following line when implemented:
        return new ArraySequence<>(elements);
    }

    /**
     * Creates a sequence of the given {@code elements}.
     *
     * @param iterable the elements to create a sequence of
     * @param <T>      the element type of the sequence
     * @return the sequence of the given elements
     */
    static <T> Sequence<T> of(Iterable<T> iterable) {
        return iterable::iterator;
    }

    /**
     * Creates a sequence using the provided {@code factory} to calculate each element.
     *
     * @param factory the factory to use
     * @param <T>     the element type of the sequence
     * @return the resulting sequence
     */
    static <T> Sequence<T> of(BasicFactory<T> factory) {
        // throw new RuntimeException("Not implemented yet");
        // Uncomment the following line when implemented:
        return new BasicFactorySequence<>(factory);
    }
}
