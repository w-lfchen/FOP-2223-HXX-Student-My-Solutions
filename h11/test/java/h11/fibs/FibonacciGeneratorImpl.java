package h11.fibs;

import java.util.List;
import java.util.stream.Stream;

/**
 * A basic implementation of the {@link FibonacciGenerator}
 * using {@link Stream} to compute the sequence.
 */
public class FibonacciGeneratorImpl implements FibonacciGenerator {

    @Override
    public List<Integer> generate(int numberOfFibs) {
        // start with the 1,2 pair defined given by a constructor without parameters, and use the next() method to iterate
        // then limit to the given amount, extract the wanted value and collect those
        return Stream.iterate(new FibonacciPair(), FibonacciPair::next).limit(numberOfFibs).map(FibonacciPair::a).toList();
    }
}
