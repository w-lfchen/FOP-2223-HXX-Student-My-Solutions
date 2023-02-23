package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;

public class TransformingSequence<T, R> implements Sequence<R> {
    // I hope this achieves maximum compatibility
    private final Sequence<? extends T> sequence;
    private final Function<? super T, ? extends R> function;

    public TransformingSequence(Sequence<? extends T> sequence, Function<? super T, ? extends R> function) {
        this.sequence = sequence;
        this.function = function;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<>() {
            private final Iterator<? extends T> iterator = sequence.iterator();

            @Override
            public boolean hasNext() {
                // we can only transform if we have things to transform
                return iterator.hasNext();
            }

            @Override
            public R next() {
                return function.apply(iterator.next());
            }
        };
    }

    public static <T, R> Function<Sequence<T>, Sequence<R>> of(Function<? super T, ? extends R> function) {
        return sequence -> new TransformingSequence<>(sequence, function);
    }
}
