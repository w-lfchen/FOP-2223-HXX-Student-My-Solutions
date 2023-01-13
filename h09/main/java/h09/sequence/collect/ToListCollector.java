package h09.sequence.collect;

import h09.sequence.Sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToListCollector<T> implements SequenceCollector<T, List<T>> {
    @Override
    public List<T> collect(Sequence<? extends T> sequence) {
        // create resulting list
        List<T> result = new ArrayList<>();
        Iterator<? extends T> iterator = sequence.iterator();
        // iterate over list and add everything to the end, resulting in the correct order
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }
}
