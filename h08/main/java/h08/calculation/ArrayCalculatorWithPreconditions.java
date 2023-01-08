package h08.calculation;

import h08.preconditions.*;

public class ArrayCalculatorWithPreconditions implements ArrayCalculator {
    /**
     * Adds up all double values in an array of arrays of double values. The double
     * values may not be negative or bigger than max.
     *
     * @param theArray The primary array containing the secondary arrays with their
     *                 double values
     * @param max      The maximum value any double value contained in the arrays may
     *                 have
     * @return double The sum of all double values contained on all secondary
     * arrays.
     */
    @Override
    public double addUp(double[][] theArray, double max) throws ArrayIsNullException, AtIndexException, WrongNumberException, AtIndexPairException {
        Preconditions.checkPrimaryArrayNotNull(theArray);
        Preconditions.checkSecondaryArraysNotNull(theArray);
        Preconditions.checkNumberNotNegative(max);
        Preconditions.checkValuesInRange(theArray, max);
        double sum = 0;
        for (double[] doubles : theArray) {
            for (double aDouble : doubles) {
                sum += aDouble;
            }
        }
        return sum;
    } // these 5 points seem free. I hope I didn't miss anything.
}
