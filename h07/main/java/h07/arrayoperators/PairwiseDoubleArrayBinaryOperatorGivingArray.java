package h07.arrayoperators;

import java.util.function.DoubleBinaryOperator;

/**
 * Class implementing the interface DoubleArrayBinaryOperatorGivingArray,
 * such that the overwritten method applyAsDoubleArray returns an array
 * only containing as many elements as the smaller array of the given
 * ones.
 * Applies the operator to the elements in the old arrays and fills the
 * new array with the results.
 */
public class PairwiseDoubleArrayBinaryOperatorGivingArray implements DoubleArrayBinaryOperatorGivingArray {

    /**
     * The operator.
     */
    private final DoubleBinaryOperator OPERATOR;

    /**
     * Constructor initializes the operator.
     *
     * @param operator The operator.
     */
    public PairwiseDoubleArrayBinaryOperatorGivingArray(DoubleBinaryOperator operator) {
        // Assign parameter to operator
        this.OPERATOR = operator;
    }

    /**
     * Returns an array containing as many elements as the smaller array of the given ones.
     * Each element is the result of the application of the operator on the elements of
     * the given arrays.
     *
     * @param left  The first array.
     * @param right The second array.
     * @return The result of the mapping.
     */
    @Override
    public double[] applyAsDoubleArray(double[] left, double[] right) {
        if (left == null || right == null) { // self-explanatory
            return null;
        } else {
            double[] result = new double[Math.min(left.length, right.length)];
            for (int i = 0; i < result.length; i++) {
                result[i] = OPERATOR.applyAsDouble(left[i], right[i]);
            }
            return result;
        } // to answer the voluntary question: whether this operation is commutative depends on the operator
    }
}
