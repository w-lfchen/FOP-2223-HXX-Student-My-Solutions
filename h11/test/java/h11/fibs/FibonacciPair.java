package h11.fibs;

/**
 * This class represents a pair of numbers
 * used to compute Fibonacci numbers.
 *
 * @param a The first number.
 * @param b The second number.
 */
public record FibonacciPair(int a, int b) {

    /**
     * Create a {@link FibonacciPair} with
     * the initial values <code>1, 2</code>.
     */
    FibonacciPair() {
        this(1, 2);
    }

    /**
     * Compute the next {@link FibonacciPair} in the sequence.
     *
     * @return The next {@link FibonacciPair}.
     */
    public FibonacciPair next() {
        return new FibonacciPair(b, a+b);
    }
}
