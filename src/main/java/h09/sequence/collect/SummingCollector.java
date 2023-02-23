package h09.sequence.collect;

import h09.basic.BasicBinaryOperations;
import h09.sequence.Sequence;

import java.util.Iterator;

public class SummingCollector<T> implements SequenceCollector<T, T> {
    private final T initialValue;
    private final BasicBinaryOperations<T, T> operation;

    public SummingCollector(T initialValue, BasicBinaryOperations<T, T> operation) {
        this.initialValue = initialValue;
        this.operation = operation;
    }

    @Override
    public T collect(Sequence<? extends T> sequence) {
        T result = initialValue;
        Iterator<? extends T> iterator = sequence.iterator();
        // sum everything up
        while (iterator.hasNext()) {
            result = operation.add(result, iterator.next());
        }
        return result;
    }
}
