package h07.arrayoperators;

import java.util.function.DoublePredicate;


/**
 * Class implementing the interface DoubleArrayUnaryOperatorGivingArray,
 * such that the overwritten method applyAsDoubleArray returns an array
 * only containing elements of the given array for which the predicate
 * of the class returns true.
 */
public class ReduceDoubleArray implements DoubleArrayUnaryOperatorGivingArray {

    /**
     * The predicate.
     */
    private final DoublePredicate PREDICATE;

    /**
     * Constructor initializes the predicate for the filter.
     *
     * @param predicate The predicate.
     */
    public ReduceDoubleArray(DoublePredicate predicate) {
        // Assign given parameter to predicate
        this.PREDICATE = predicate;
    }

    /**
     * Returns an array containing all the elements of the given array
     * for which the predicate returns true.
     *
     * @param array The array.
     * @return The result of the filter.
     */
    @Override
    public double[] applyAsDoubleArray(double[] array) {
        if (array == null) {
            return null;
        } else {
            double[] result = new double[determineArrayLengthFromPredicate(array)];
            int counter = 0; // since the indices don't have to match we need this variable
            for (double v : array) {
                if (PREDICATE.test(v)) {
                    result[counter] = v;
                    counter++;
                }
            }
            return result;
        }
    }

    // method to determine array length
    public int determineArrayLengthFromPredicate(double[] array) {
        int length = 0;
        for (double v : array) {
            if (PREDICATE.test(v)) {
                length++;
            }
        }
        return length;
    }
}
