package h11.fibs;

import h11.Algae;
import h11.LSystemGrowerImpl;

/**
 * The actual testcase based on the {@link AlgaeTest}
 * to be used by students.
 */
public class StudentAlgaeTest extends AlgaeTest {

    /**
     * Construct an {@link StudentAlgaeTest} passing a {@link FibonacciGeneratorImpl}
     * and an {@link AlgaeFibonacciGenerator} along to the superclass.
     */
    protected StudentAlgaeTest() {
        super(new FibonacciGeneratorImpl(), new AlgaeFibonacciGenerator(new LSystemGrowerImpl<>(new Algae())));
    }
}
