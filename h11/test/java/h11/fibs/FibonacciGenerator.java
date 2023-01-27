package h11.fibs;

import java.util.List;

/**
 * Instances of this interface
 * can compute a finite number of Fibonacci numbers.
 * In this case the sequence of Fibonacci numbers starts with
 * <code>1, 2</code>.
 */
public interface FibonacciGenerator {

    /**
     * Computes the given amount of Fibonacci numbers.
     *
     * @param numberOfFibs The amount to compute.
     * @return A {@link List} of the first <code>n</code> Fibonacci numbers.
     */
    List<Integer> generate(int numberOfFibs);
}
