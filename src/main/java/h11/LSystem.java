package h11;

import java.util.stream.Stream;

/**
 * An L-System represents growth
 * as substitutions, called projections,
 * in a sequence of tokens.
 * In this case, a token can be an instance
 * of any class denoted by the type <code>T</code>.
 * <p>
 * The initial sequence is called axiom
 * and consists of a single token.
 * It can be queried via the method {@link #getAxiom()}.
 * <p>
 * The projection rules are implemented
 * in the method {@link #project(Object)}.
 * It has to be called on every token in the sequence
 * to project the whole sequence.
 * <p>
 * An example L-System looks like this:
 * <pre>
 *     A -> AB
 *     B -> A
 * </pre>
 * We call the left side of a projection "the source"
 * and the ride side "the destination".
 * The L-System has the following
 * projections first four (stating at the axiom):
 * <pre>
 *     A -> AB -> ABA -> ABAAB
 * </pre>
 *
 * @param <T> The type of the tokens.
 */
public interface LSystem<T> {

    /**
     * @return The initial token in this L-System.
     */
    T getAxiom();

    /**
     * Maps the given token to its corresponding destination sequence.
     *
     * @return A {@link Stream} representing the destination sequence for the given token.
     */
    Stream<T> project(T e);
}
