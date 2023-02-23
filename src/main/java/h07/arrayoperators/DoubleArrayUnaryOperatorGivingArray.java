package h07.arrayoperators;

/**
 * Functional interface for an array operation on one array, returning an array.
 * Goal of implementation should be to implement a filter function.
 */
public interface DoubleArrayUnaryOperatorGivingArray {

    /**
     * Applies an operation on a given double-array and returns the result.
     * Should be applied as a filter function.
     *
     * @param array     The array.
     * @return          The result of the filter.
     */
    double[] applyAsDoubleArray(double[] array);

}
