package h09.sequence;

import java.util.Iterator;

public class FibonacciSequence implements Sequence<Integer> {

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            // initialise variables with the starting values
            private int current = 0;
            private int next = 1;

            @Override
            public boolean hasNext() {
                // there is always a next element since we calculate it on the spot.
                return true;
            }

            @Override
            public Integer next() {
                // we calculate the next value with every call.
                // Drawing the numbers helps with understanding why this is most likely correct
                int result = next;
                next += current;
                current = result;
                return result;
            }
        };
    }
}
