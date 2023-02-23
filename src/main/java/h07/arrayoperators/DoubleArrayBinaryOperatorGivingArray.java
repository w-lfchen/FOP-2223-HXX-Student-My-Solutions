package h07.arrayoperators;

/**
 * Functional interface for an array operation on two arrays, returning an array.
 * Goal of implementations should be to implement a mapping function.
 */
public interface DoubleArrayBinaryOperatorGivingArray {

    /**
     * Applies an operation on two given double-arrays and returns the result.
     * Should be applied as a mapping function.
     *
     * @param left      The first array.
     * @param right     The second array.
     * @return          The result of the mapping.
     */
    double[] applyAsDoubleArray(double[] left, double[] right);
}
