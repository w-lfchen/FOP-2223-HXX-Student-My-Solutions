package h11.fibs;

import h11.Algae;
import h11.LSystemGrower;

import java.util.List;

/**
 * An implementation of the {@link FibonacciGenerator}
 * using the {@link Algae} L-System.
 */
public class AlgaeFibonacciGenerator implements FibonacciGenerator {

    /**
     * The {@link LSystemGrower} based on the Algae to use.
     */
    private final LSystemGrower<Algae.Variable> algaeGrower;

    /**
     * @param algaeGrower The {@link LSystemGrower} based on the Algae to use.
     */
    public AlgaeFibonacciGenerator(LSystemGrower<Algae.Variable> algaeGrower) {
        this.algaeGrower = algaeGrower;
    }

    @Override
    public List<Integer> generate(int numberOfFibs) {
        // this is just the instructions, applied step after step, not much to say here
        return algaeGrower.grow().limit(numberOfFibs).map(List::size).toList();
    }
}
