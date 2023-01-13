package h09.sequence;

import h09.sequence.collect.SequenceCollector;

import java.util.Iterator;

/**
 * A {@link SequenceCollector} that iterates through the sequence but does not do anything with the elements.
 */
final class VoidCollector<T> implements SequenceCollector<T, Void> {

    private static final VoidCollector<?> INSTANCE = new VoidCollector<>();

    @SuppressWarnings("unchecked")
    public static <T> VoidCollector<T> get() {
        // casting is ok, because nothing is ever done with the elements
        return (VoidCollector<T>) INSTANCE;
    }

    @Override
    public Void collect(Sequence<? extends T> sequence) {
        final Iterator<? extends T> iterator = sequence.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        return null;
    }
}
