package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;

public class LimitSequence<T> implements Sequence<T> {

    private final Sequence<? extends T> sequence;
    private final int limit;

    public static <T> Function<Sequence<T>, Sequence<T>> of(int limit) {
        return sequence -> new LimitSequence<>(sequence, limit);
    }

    public LimitSequence(Sequence<? extends T> sequence, int limit) {
        this.sequence = sequence;
        this.limit = limit;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Iterator<? extends T> iterator = sequence.iterator();
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < limit && iterator.hasNext();
            }

            @Override
            public T next() {
                count++;
                return iterator.next();
            }
        };
    }
}
