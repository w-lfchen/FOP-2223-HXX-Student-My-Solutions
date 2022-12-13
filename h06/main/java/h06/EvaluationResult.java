package h06;

/**
 * The result of the evaluation of a bracket expression.
 */
public record EvaluationResult(
    Type type,
    int nextIndex
) {

    /**
     * An enumeration of constants representing results of a bracket expression evaluation.
     */
    public enum Type {
        CORRECT,
        INVALID_CHARACTER,
        NO_OPENING_BRACKET,
        NO_CLOSING_BRACKET,
        INVALID_CLOSING_BRACKET
    }
}
