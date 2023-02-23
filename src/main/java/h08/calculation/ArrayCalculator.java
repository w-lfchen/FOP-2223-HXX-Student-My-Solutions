package h08.calculation;

/**
 * A calculator to sum up all double values in an array of arrays of double values.
 */
public interface ArrayCalculator {
    /**
     * Adds up all double values in an array of arrays of double values. The double
     * values may not be negative or bigger than max.
     *
     * @param theArray The primary array containing the secondary arrays with their
     *                 double values
     * @param max      The maximum value any double value contained in the arrays may
     *                 have
     * @return double The sum of all double values contained on all secondary arrays
     * @throws Exception When a proper calculation is not possible using the given input
     */
    double addUp(double[][] theArray, double max) throws Exception;
}
