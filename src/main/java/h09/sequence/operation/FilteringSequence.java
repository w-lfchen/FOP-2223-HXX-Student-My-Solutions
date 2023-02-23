package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class FilteringSequence<T> implements Sequence<T> {
    // this should offer maximum compatibility
    private final Sequence<? extends T> sequence;
    private final Predicate<? super T> predicate;

    public FilteringSequence(Sequence<? extends T> sequence, Predicate<? super T> predicate) {
        this.sequence = sequence;
        this.predicate = predicate;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Iterator<? extends T> iterator = sequence.iterator();
            private T next;

            @Override
            public boolean hasNext() {
                while (iterator.hasNext()) {
                    // if next is null, we try again with the next one
                    if (next == null) {
                        next = iterator.next();
                    } else if (predicate.test(next)) { // if it is not null, we check whether next is accepted
                        return true;
                    } else { // if it isn't accepted, we move on
                        next = iterator.next();
                    }
                }
                // now next is the last element since iterator.hasNext() was false
                // if it is null, return false immediately, otherwise check whether to return true or false
                return next != null && predicate.test(next);
            }

            @Override
            public T next() {
                // I don't think there is a different way to do this
                T temp = next;
                next = null;
                return temp;
            }
        };
    }

    public static <T> Function<Sequence<T>, Sequence<T>> of(Predicate<? super T> predicate) {
        // this one is very similar to OnEachSequence
        return sequence -> new FilteringSequence<>(sequence, predicate);
    }
}
