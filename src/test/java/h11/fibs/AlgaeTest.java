package h11.fibs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The basis of a testcase for the Algae
 * L-System based on Fibonacci numbers.
 */
public abstract class AlgaeTest {

    /**
     * The reference {@link FibonacciGenerator} to use.
     */
    private final FibonacciGenerator fibonacciGenerator;

    /**
     * The {@link FibonacciGenerator} to test.
     */
    private final FibonacciGenerator algaeFibonacciGenerator;

    /**
     * @param fibonacciGenerator The reference {@link FibonacciGenerator} to use.
     *                           The algaeFibonacciGenerator {@link FibonacciGenerator} to test.
     */
    protected AlgaeTest(FibonacciGenerator fibonacciGenerator, FibonacciGenerator algaeFibonacciGenerator) {
        this.fibonacciGenerator = fibonacciGenerator;
        this.algaeFibonacciGenerator = algaeFibonacciGenerator;
    }

    /**
     * Test that the algae generates Fibonacci
     * numbers according to the given <code>fibonacciGenerator</code>
     */
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 30})
    public void testAlgaeGeneratesFibs(int numberOfFibs) {
        // since it can be assumed that fibonacciGenerator works correctly, it is responsible for the expected value
        assertEquals(fibonacciGenerator.generate(numberOfFibs), algaeFibonacciGenerator.generate(numberOfFibs));
    }
}
