package h09.sequence.collect;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.BinaryOperator;

public class BinaryOpFoldCollector<T> implements SequenceCollector<T, T> {
    private final T initialValue;
    private final BinaryOperator<T> operation;

    public BinaryOpFoldCollector(T initialValue, BinaryOperator<T> operation) {
        this.initialValue = initialValue;
        this.operation = operation;
    }

    @Override
    public T collect(Sequence<? extends T> sequence) {
        T result = initialValue;
        Iterator<? extends T> iterator = sequence.iterator();
        // this is pretty much the same
        while (iterator.hasNext()) {
            result = operation.apply(result, iterator.next());
        }
        return result;
    }
}
/* voluntary question:
Depends on the situation, arithmetic would fit if we use numbers and such.
 */
