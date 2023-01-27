package h11.parse;

import h11.LSystem;

import java.util.List;
import java.util.stream.Stream;

/**
 * One can utilize this class to
 * use the {@link List} of {@link Projection} parsed
 * by the {@link LSystemParser} to implement an {@link LSystem}.
 */
public class ParsedLSystem implements LSystem<Character> {

    /**
     * The projections of the L-System this instance represents.
     */
    private final List<Projection> projections;

    /**
     * @param projections The projections of the L-System this instance represents.
     */
    public ParsedLSystem(List<Projection> projections) {
        if (projections.isEmpty()) {
            throw new IllegalArgumentException("Need at least one projection rule");
        }

        this.projections = projections;
    }

    @Override
    public Character getAxiom() {
        // it can be assumed that the list is not empty since the constructor makes sure that it is not
        return projections.get(0).source();
    }

    @Override
    public Stream<Character> project(Character ch) {
        // return a stream containing only ch if no projection source is ch.
        // if that is not the case, the projection with the right source is filtered out, then the first element of that is picked
        // using a method that most likely can be improved on, the destination of this projection is then transformed to a stream of Characters
        return projections.stream().noneMatch(projection -> projection.source() == ch) ? Stream.of(ch) : projections.stream().filter(x -> x.source() == ch).toList().get(0).destination().chars().mapToObj(c -> (char) c);
    }
}
