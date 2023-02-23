package h09.sequence;

import h09.basic.BasicFactory;

import java.util.Iterator;

public class BasicFactorySequence<T> implements Sequence<T> {
    private final BasicFactory<T> factory;

    public BasicFactorySequence(BasicFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                // this factory seems to have an unending supply of crafting materials
                return true;
            }

            @Override
            public T next() {
                // whenever you need an endless stream of T
                return factory.create();
            }
        };
    }
}
