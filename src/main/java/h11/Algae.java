package h11;

import java.util.stream.Stream;

/**
 * An implementation of an {@link LSystem}
 * called the {@link Algae} that can be unused
 * to compute the Fibonacci number.
 * <p>
 * There are two possible tokens,
 * called {@link Variable}s: <code>A, B</code>
 * and two projections
 * <pre>
 *     A -> AB,
 *     B -> A
 * </pre>
 * <code>A</code> is the axiom.
 */
public class Algae implements LSystem<Algae.Variable> {

    /**
     * The two variables used by this {@link LSystem}.
     */
    public enum Variable {A, B}

    @Override
    public Variable getAxiom() {
        return Variable.A;
    }

    @Override
    public Stream<Variable> project(Variable v) { // TODO: test
        // can only receive A or B, so using the ternary operator would be a different solution.
        // the switch would allow for greater compatibility, should the enum be expanded in the future
        // return v == Variable.A ? Stream.of(Variable.A, Variable.B) : Stream.of(Variable.A);
        return switch (v) {
            case A -> Stream.of(Variable.A, Variable.B);
            case B -> Stream.of(Variable.A);
        };
    }
}
