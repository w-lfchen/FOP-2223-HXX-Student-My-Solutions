package h11;

import java.util.List;
import java.util.stream.Stream;

/**
 * Strait forward implementation of an {@link LSystemGrower}
 * using an instance of {@link LSystem}.
 *
 * @param <T> The token type as used in {@link LSystemGrower}.
 */
public class LSystemGrowerImpl<T> implements LSystemGrower<T> {

    /**
     * The underlying {@link LSystem}.
     */
    private final LSystem<T> lSystem;

    /**
     * @param lSystem The underlying {@link LSystem}.
     */
    public LSystemGrowerImpl(LSystem<T> lSystem) {
        this.lSystem = lSystem;
    }

    @Override
    public Stream<List<T>> grow() {
        // the lambda expression first streams the elements and then collects them after they have been projected.
        // the flatMap method is needed since project returns a stream, so ít needs to be flattened it before collecting it.
        return Stream.iterate(List.of(lSystem.getAxiom()), list -> list.stream().flatMap(lSystem::project).toList());
    }
}
/* voluntary question:
As already stated, a stream twice can't be used twice, which is of course possible with a list,
since an infinite amount of iterators can be created from the same list
 */
