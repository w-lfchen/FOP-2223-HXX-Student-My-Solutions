package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;

public class FlatteningTransformingSequence<T, R> implements Sequence<R> {
    // if it is structured like TransformingSequence, this should yield maximum compatibility if we do it the same way
    private final Sequence<? extends T> sequence;
    private final Function<? super T, Sequence<? extends R>> function;

    public FlatteningTransformingSequence(Sequence<? extends T> sequence, Function<? super T, Sequence<? extends R>> function) {
        this.sequence = sequence;
        this.function = function;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<>() {
            // initialise iterators
            private final Iterator<? extends T> iterator = sequence.iterator();
            private Iterator<? extends R> currentIterator = null;

            @Override
            public boolean hasNext() {
                // while currentIterator is null or doesn't have elements, we want to apply it to the next element of iterator
                // of course, this is only possible if iterator has a next element
                while ((currentIterator == null || !currentIterator.hasNext()) && iterator.hasNext()) {
                    // the function yields us a sequence we can iterate over, flattening our data structure in the process
                    currentIterator = function.apply(iterator.next()).iterator();
                }
                // if the while loop terminates, both of these should be true, otherwise we are out of elements
                return currentIterator != null && currentIterator.hasNext();
            }

            @Override
            public R next() {
                // this is what settles the flattening by returning a one-dimensional data structure
                return currentIterator.next();
            }
        };
    }

    public static <T, R> Function<Sequence<T>, Sequence<R>> of(Function<? super T, Sequence<? extends R>> function) {
        // it is important to note that we don't transform a sequence of T as the data structure could be anything like a String or an array.
        // However, we can expect function to return a sequence of type R, that we can iterate over using an iterator
        return sequence -> new FlatteningTransformingSequence<>(sequence, function);
    }
}
