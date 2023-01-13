package h09.sequence;

import java.util.Iterator;

public class ArraySequence<T> implements Sequence<T> {
    private final T[] values;

    public ArraySequence(T[] values) {
        this.values = values;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                // simply check whether index is out of bounds
                return index < values.length;
            }

            @Override
            public T next() {
                // increment index by one after returning the current index, we shouldn't need to check whether index is within bounds.
                // this is due to has Next returning false if we are not within bounds,
                // and it would be incorrect usage to not check whether there even is a next element
                return values[index++];
            }
        };
    }
}
