package h07.arrayoperators;

/**
 * Functional interface for an array operation on two arrays, returning a scalar.
 * Goal of implementation should be to implement a folding function.
 */
public interface DoubleArrayBinaryOperatorGivingScalar {

    /**
     * Applies an operation on two given double-arrays and returns a double.
     * Should be applied as a folding function.
     *
     * @param left      The first array.
     * @param right     The second array.
     * @return          The result of the fold.
     */
    double applyAsDoubleArray(double[] left, double[] right);

}
